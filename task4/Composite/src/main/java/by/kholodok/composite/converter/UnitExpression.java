package by.kholodok.composite.converter;

import by.kholodok.composite.parser.VarsCounter;

/**
 * Created by dmitrykholodok on 11/20/17
 */

public class UnitExpression {

    private String expressionValue;
    private UnitExpressionType varOperationType;

    public UnitExpression(String expressionValue, UnitExpressionType varOperationType) {
        this.expressionValue = expressionValue;
        this.varOperationType = varOperationType;
    }

    public String getValue() {
        String result;
        switch (varOperationType) {
            case POST_PLUS:
                result = String.valueOf(VarsCounter.getValue(expressionValue));
                VarsCounter.incVar(expressionValue);
                break;
            case POST_MINUS:
                result = String.valueOf(VarsCounter.getValue(expressionValue));
                VarsCounter.decVar(expressionValue);
                break;
            case PRE_PLUS:
                VarsCounter.incVar(expressionValue);
                result = String.valueOf(VarsCounter.getValue(expressionValue));
                break;
            case PRE_MINUS:
                VarsCounter.decVar(expressionValue);
                result = String.valueOf(VarsCounter.getValue(expressionValue));
                break;
            case NONE:
                result = String.valueOf(VarsCounter.getValue(expressionValue));
                break;
            case NUMBER:
                result = String.valueOf(expressionValue);
                break;
            default:
                result = expressionValue;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitExpression that = (UnitExpression) o;

        if (expressionValue != null ? !expressionValue.equals(that.expressionValue) : that.expressionValue != null)
            return false;
        return varOperationType == that.varOperationType;
    }

    @Override
    public int hashCode() {
        int result = expressionValue != null ? expressionValue.hashCode() : 0;
        result = 31 * result + (varOperationType != null ? varOperationType.hashCode() : 0);
        return result;
    }
}
