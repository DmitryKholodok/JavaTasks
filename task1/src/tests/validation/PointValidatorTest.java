package tests.validation;

import by.kholodok.task1.exception.ParamCountException;
import by.kholodok.task1.validation.impl.PointValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointValidatorTest {

    private final PointValidator pointValidator = new PointValidator();

    @Test
    public void testGoodCase() throws ParamCountException {
        String testString = "(1.0, 1.0)";
        Assert.assertEquals(pointValidator.isValid(testString), true);
    }

    @Test
    public void testParamCountException() throws ParamCountException {
        String testString = "(1.0)";
        Assert.assertEquals(pointValidator.isValid(testString), false);
    }

    @Test
    public void testNumberFormatException() throws ParamCountException {
        String testString = "(1z.0, 1.0)";
        Assert.assertEquals(pointValidator.isValid(testString), false);
    }

    @Test
    public void testWasteIncorrectWords() throws ParamCountException {
        String testString = "(1.0, /1.0)";
        Assert.assertEquals(pointValidator.isValid(testString), false);
    }

    @Test
    public void testWasteCorrectChars() throws ParamCountException {
        String testString = "((1.0, 1.0)";
        Assert.assertEquals(pointValidator.isValid(testString), true);
    }

}

