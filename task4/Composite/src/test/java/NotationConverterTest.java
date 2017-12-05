import by.kholodok.composite.converter.NotationConverter;
import by.kholodok.composite.converter.UnitExpression;
import by.kholodok.composite.converter.UnitExpressionType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/20/17
 */
public class NotationConverterTest {

    @Test
    public void convertToPostfixNotationTest() {
        List<UnitExpression> expectedUnitExpressionList = new ArrayList<>();
        expectedUnitExpressionList.add(new UnitExpression("1", UnitExpressionType.NUMBER));
        expectedUnitExpressionList.add(new UnitExpression("3", UnitExpressionType.NUMBER));
        expectedUnitExpressionList.add(new UnitExpression("+", UnitExpressionType.SIGN));
        expectedUnitExpressionList.add(new UnitExpression("3", UnitExpressionType.NUMBER));
        expectedUnitExpressionList.add(new UnitExpression("*", UnitExpressionType.SIGN));

        String infixExpression = "(1 + 3) *3";
        NotationConverter converter = new NotationConverter();
        List<UnitExpression> unitExpressionList = converter.convertToPostfixNotation(infixExpression);
        Assert.assertEquals(unitExpressionList, expectedUnitExpressionList);
    }


}
