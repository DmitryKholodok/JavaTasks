package tests.validation;

import by.kholodok.task1.exception.ParamCountException;
import by.kholodok.task1.validator.impl.QuadrValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuadrValidatorTest {

    private final QuadrValidator quadrValidator = new QuadrValidator();

    @Test
    public void testGoodCase() throws ParamCountException {
        String testString = "[(1.0, 1.0) (1.0, 3.0) (3.0, 3.0) (3.0, 1.0)]";
        Assert.assertEquals(quadrValidator.isValid(testString), true);
    }

    @Test
    public void testParamCountException() throws ParamCountException {
        String testString = "[(1.0, 1.0) (1.0, 3.0) (3.0, 1.0)]";
        Assert.assertEquals(quadrValidator.isValid(testString), false);
    }

    @Test
    public void testPointValidateException() throws ParamCountException {
        String testString = "[(1.0, 1.0) (1.0, 3.0) (3z.0, 3.0) (3.0, 1.0)]";
        Assert.assertEquals(quadrValidator.isValid(testString), false);
    }

    @Test
    public void testWasteIncorrectWords() throws ParamCountException {
        String testString = "[(1.0, 1.0)  (1.0, 3.0) (3.0, 3.0) (3.0, 1.0)]";
        Assert.assertEquals(quadrValidator.isValid(testString), false);
    }

    @Test
    public void testWasteCorrectChars() throws ParamCountException {
        String testString = "[[[(1.0, 1.0) (1.0, 3.0) (3.0, 3.0) (3.0, 1.0)]]";
        Assert.assertEquals(quadrValidator.isValid(testString), true);
    }

}
