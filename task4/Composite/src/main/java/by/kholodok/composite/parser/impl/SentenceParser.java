package by.kholodok.composite.parser.impl;

import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.ComponentType;
import by.kholodok.composite.entity.TextContainer;
import by.kholodok.composite.parser.AbstractTextParser;
import by.kholodok.composite.interpreter.impl.ExpressionInterpreter;
import by.kholodok.composite.converter.UnitExpression;
import by.kholodok.composite.converter.NotationConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class SentenceParser extends AbstractTextParser {

    private static final Logger LOGGER = LogManager.getLogger(SentenceParser.class);

    private static final String DIVIDER_BY_LEXEM = "\\s";

    private static final String TERMINAL_SUB_EXPRESSION =
            "([0-9a-zA-Z\\+\\-\\/\\*\\(\\) ]+)([\\-]{2}|[\\+]{2}|[ 0-9a-zA-Z]+|[\\)]+)";


    public SentenceParser(AbstractTextParser parser) {
        super(parser);
    }

    @Override
    public List<Component> parse(String str) {
        LOGGER.log(Level.DEBUG, "SentenceParser : started the work with '" + str + "'");
        List<Component> lexemComponentList = new ArrayList<>();
        for(String lexeme : str.split(DIVIDER_BY_LEXEM)) {
            if (!lexeme.isEmpty()) {
                LOGGER.log(Level.INFO, "SentenceParser : got a new lexeme '" + lexeme + "'");
                Component component = new TextContainer(ComponentType.LEXEME);
                nextParser.parse(lexeme)
                        .stream()
                        .forEach(leaf -> component.addComponent(leaf));
                lexemComponentList.add(component);
            }
        }
        calculateExpressions(lexemComponentList);
        LOGGER.log(Level.DEBUG, "SentenceParser : finished with '" + str + "'");
        return lexemComponentList;
    }

    private void calculateExpressions(List<Component> lexems) {
        Predicate<Component> isTerminalExpression = expression -> expression.toString().matches(TERMINAL_SUB_EXPRESSION);
        List<Component> expressionList = receiveExpressionLeafList(lexems);
        String lastSubExpression = "";
        for(Component expression : expressionList) {
            lastSubExpression += expression + " ";
            if (isBracketsCountTheSame(lastSubExpression) && isTerminalExpression.test(expression)) {
                String calculatedExpression = calculateExpression(lastSubExpression);
                expression.updateComponent(ComponentType.WORD, calculatedExpression);
                lastSubExpression = "";
            } else {
                lexems.remove(expression);
            }
        }
    }

    private List<Component> receiveExpressionLeafList(List<Component> componentList) {
        List<Component> expressionLeafList = new ArrayList<>();
        componentList
                .stream()
                .forEach(lexeme -> lexeme.receiveComponentList(ComponentType.EXPRESSION)
                        .stream()
                        .forEach(expressionLeaf -> expressionLeafList.add(expressionLeaf)));
        return expressionLeafList;
    }

    private boolean isBracketsCountTheSame(String expression) {
        int leftBracketCount = 0;
        int rightBracketCount = 0;
        for(int i = 0; i < expression.length(); i++) {
            Character ch = expression.charAt(i);
            if (ch == '(') {
                leftBracketCount++;
            } else if (ch == ')') {
                rightBracketCount++;
            }
        }
        return leftBracketCount == rightBracketCount;
    }

    private String calculateExpression(String expression) {
        List<UnitExpression> unitExpressionList = new NotationConverter().convertToPostfixNotation(expression);
        ExpressionInterpreter interpreter = new ExpressionInterpreter(unitExpressionList);
        Integer expressionValue = interpreter.calculate();
        return expressionValue.toString();
    }


}
