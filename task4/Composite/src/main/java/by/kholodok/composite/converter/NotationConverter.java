package by.kholodok.composite.converter;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitrykholodok on 11/19/17
 */

public class NotationConverter {

    private final static String REGEX_TO_FIND_INC_DEC_OPERATIONS =
            "([\\-]{2}[a-zA-z]+|[\\+]{2}[a-zA-Z]+|[a-zA-z]+[\\-]{2}|[a-zA-z]+[\\+]{2})";

    private final static String DIVIDER_ON_OPERATION_AND_VAR_NAME =
            "([\\-]{2}|[\\+]{2}|[a-zA-Z]+)([\\-]{2}|[\\+]{2}|[a-zA-Z]+)";

    private final static String REGEX_TO_FIND_NEG_NUMBERS = "([^0-9\\)\\s]+)([-]{1}[0-9]+)";

    private int expressionIndex;

    public List<UnitExpression> convertToPostfixNotation(String infixExpression) {
        final int DEC_INT_GROUP_INDEX = 0;
        final int NEG_NUMBER_GROUP_INDEX = 2;
        List<UnitExpression> unitExpressionList = new ArrayList<>();
        Deque<Character> signDeque = new ArrayDeque<>();
        Deque<Integer> decIncIndexes =
                receiveRegexIndexesInExpression(infixExpression, REGEX_TO_FIND_INC_DEC_OPERATIONS, DEC_INT_GROUP_INDEX);
        Deque<Integer> minusNumberIndexes =
                receiveRegexIndexesInExpression(infixExpression, REGEX_TO_FIND_NEG_NUMBERS, NEG_NUMBER_GROUP_INDEX);

        for(expressionIndex = 0; expressionIndex < infixExpression.length(); expressionIndex++) {
            if (!decIncIndexes.isEmpty() && decIncIndexes.getFirst() == expressionIndex) {
                decIncIndexes.removeFirst();
                incDecHandle(infixExpression, unitExpressionList);
            } else if (!minusNumberIndexes.isEmpty() && minusNumberIndexes.getFirst() == expressionIndex) {
                    minusNumberIndexes.removeFirst();
                    expressionIndex++;
                    String number = receiveSubStringFromExpression(infixExpression, Character::isDigit);
                    unitExpressionList.add(new UnitExpression("-" + number, UnitExpressionType.NUMBER));

            } else {
                Character ch = infixExpression.charAt(expressionIndex);
                if (ch == ' ') {
                    continue;
                } else if (Character.isLetter(ch)) { // VAR
                    String currVarName = receiveSubStringFromExpression(infixExpression, Character::isLetter);
                    unitExpressionList.add(new UnitExpression(currVarName, UnitExpressionType.NONE));
                } else if (ch == '(') {
                    signDeque.addLast(ch);
                } else if (ch == ')') {
                    pushOutSigns(signDeque, unitExpressionList);
                } else if (Character.isDigit(ch)) {
                    String number = receiveSubStringFromExpression(infixExpression, Character::isDigit);
                    unitExpressionList.add(new UnitExpression(number, UnitExpressionType.NUMBER));
                } else if (ch == '*' || ch == '/' || ch == '-' || ch == '+') {
                    insertSignInSignDeque(signDeque, unitExpressionList, ch);
                }
            }
        }
        while(!signDeque.isEmpty()) {
            unitExpressionList.add((
                    new UnitExpression("" + signDeque.pollLast(), UnitExpressionType.SIGN)));
        }
        return unitExpressionList;
    }


    private void incDecHandle(String expression, List<UnitExpression> unitExpressionList) {
        Pattern pattern = Pattern.compile(DIVIDER_ON_OPERATION_AND_VAR_NAME);
        String subExpression = expression.substring(expressionIndex);
        Matcher matcher = pattern.matcher(subExpression);
        matcher.find();
        String firstVarPart = matcher.group(1);
        String secondVarPart = matcher.group(2);
        unitExpressionList.add(defineVarUnitExpression(firstVarPart, secondVarPart));
        expressionIndex += firstVarPart.length() + secondVarPart.length() - 1;
    }

    private UnitExpression defineVarUnitExpression(String firstVarPart, String secondVarPart) {
        UnitExpression unitVarExpression;
        if ("--".equals(firstVarPart)) {
            unitVarExpression = new UnitExpression(secondVarPart, UnitExpressionType.PRE_MINUS);
        } else if ("++".equals(firstVarPart)) {
            unitVarExpression = new UnitExpression(secondVarPart, UnitExpressionType.PRE_PLUS);
        } else if ("--".equals(secondVarPart)) {
            unitVarExpression = new UnitExpression(firstVarPart, UnitExpressionType.POST_MINUS);
        } else {
            unitVarExpression = new UnitExpression(firstVarPart, UnitExpressionType.POST_PLUS);
        }
        return unitVarExpression;
    }

    private Deque<Integer> receiveRegexIndexesInExpression(String expression, String regex, int groupIndex) {
        Deque<Integer> expressionIndexes = new ArrayDeque<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        int expressionIndex = 0;
        while(matcher.find()) {
            String str = matcher.group(groupIndex);
            expressionIndex = expression.indexOf(str, expressionIndex);
            expressionIndexes.addLast(expressionIndex);
        }
        return expressionIndexes;
    }

    private void insertSignInSignDeque(Deque<Character> signDeque,
                                       List<UnitExpression> unitExpressionList, Character insertedChar) {
        if (signDeque.isEmpty()) {
            signDeque.addLast(insertedChar);
        } else {
            Character oldSign = signDeque.getLast();
            while (receivePriority(oldSign) >= receivePriority(insertedChar) && !signDeque.isEmpty()) {
                unitExpressionList.add(
                        new UnitExpression("" + signDeque.pollLast(), UnitExpressionType.SIGN));
                oldSign = signDeque.getLast();
            }
            signDeque.addLast(insertedChar);
        }
    }

    private void pushOutSigns(Deque<Character> signDeque, List<UnitExpression> unitExpressionList) {
        Character sign = signDeque.pollLast();
        while(sign != '(') {
            unitExpressionList.add(new UnitExpression("" + sign, UnitExpressionType.SIGN));
            sign = signDeque.pollLast();
        }
    }

    private int receivePriority(Character sign) {
        switch (sign) {
            case '-' :
            case '+' :
                return 1;
            case '*' :
            case '/' :
                return 2;
            default:
                return 0;
        }
    }

    private String receiveSubStringFromExpression(String expression, Predicate<Character> isGoodChar) {
        String numberString = "";
        Character ch = expression.charAt(expressionIndex);
        while(isGoodChar.test(ch)) {
            numberString += ch;
            expressionIndex++;
            if (expressionIndex == expression.length()) {
                break;
            }
            ch = expression.charAt(expressionIndex);
        }
        expressionIndex--;
        return numberString;
    }

}
