import by.kholodok.composite.parser.VarsCounter;
import by.kholodok.composite.interpreter.impl.ExpressionInterpreter;
import by.kholodok.composite.converter.NotationConverter;
import by.kholodok.composite.converter.UnitExpression;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by dmitrykholodok on 11/20/17
 */
public class ExpressionInterpreterTest {

    private NotationConverter converter;

    @BeforeClass
    public void init() {
        VarsCounter.addVar("i", 5);
        VarsCounter.addVar("j", 3);
        converter = new NotationConverter();
    }

    @Test
    public void calculateTest() {
        String infixExpression = "13+ i--";
        List<UnitExpression> unitExpressionList = converter.convertToPostfixNotation(infixExpression);
        ExpressionInterpreter interpreter = new ExpressionInterpreter(unitExpressionList);
        int expressionResult = interpreter.calculate();
        Assert.assertEquals(expressionResult, 18);
        Assert.assertEquals(VarsCounter.getValue("i"), 4);
    }

    @Test
    public void calculateTest1() {
        String infixExpression = "6+9*(3-4)";
        List<UnitExpression> unitExpressionList = converter.convertToPostfixNotation(infixExpression);
        ExpressionInterpreter interpreter = new ExpressionInterpreter(unitExpressionList);
        int expressionResult = interpreter.calculate();
        Assert.assertEquals(expressionResult, -3);
    }

    @Test
    public void calculateTest2() {
        String infixExpression = "5*(1*2*(3*(4*(5- --j + 4)-3)-2)-1)";
        List<UnitExpression> unitExpressionList = converter.convertToPostfixNotation(infixExpression);
        ExpressionInterpreter interpreter = new ExpressionInterpreter(unitExpressionList);
        int expressionResult = interpreter.calculate();
        Assert.assertEquals(expressionResult, 725);
        Assert.assertEquals(VarsCounter.getValue("j"), 2);
    }


}
