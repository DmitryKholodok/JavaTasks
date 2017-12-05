package by.kholodok.composite.interpreter.impl;

import by.kholodok.composite.interpreter.AbstractMathExpression;
import by.kholodok.composite.interpreter.Context;
import by.kholodok.composite.converter.UnitExpression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/20/17
 */
public class ExpressionInterpreter {

    private List<AbstractMathExpression>  expressionList;

    public ExpressionInterpreter(List<UnitExpression> unitExpressionList) {
        expressionList = new ArrayList<>();
        parse(unitExpressionList);
    }

    private void parse(List<UnitExpression> unitExpressionList) {
        Iterator<UnitExpression> expressionIterator = unitExpressionList.iterator();
        while(expressionIterator.hasNext()) {
            UnitExpression unitExpression = expressionIterator.next();
            AbstractMathExpression<Context> expression;
            String expressionValue = unitExpression.getValue();
            switch (expressionValue) {
                case "+":
                    expression = context -> context.push(context.pop() + context.pop());
                    break;
                case "-":
                    expression = context -> context.push( - context.pop() + context.pop());
                    break;
                case "*":
                    expression = context -> context.push(context.pop() * context.pop());
                    break;
                case "/":
                    expression = context -> context.push(1 / context.pop() * context.pop());
                    break;
                default:
                    expression = context -> context.push(Integer.valueOf(expressionValue));
            }
            expressionList.add(expression);
        }
    }

    public Integer calculate() {
        Context context = new Context();
        for(AbstractMathExpression expression : expressionList) {
            expression.interpret(context);
        }
        return context.pop();
    }

}
