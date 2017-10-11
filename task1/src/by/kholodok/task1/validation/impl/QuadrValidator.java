package by.kholodok.task1.validation.impl;

import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.handler.impl.QuadrLineHandler;
import by.kholodok.task1.validation.Validator;
import by.kholodok.task1.exception.ParamCountException;
import by.kholodok.task1.exception.PointValidateException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuadrValidator implements Validator {

    private static Logger logger = LogManager.getLogger(QuadrValidator.class);
    private final PointValidator pointValidator = new PointValidator();

    @Override
    public boolean isValid(String str) {
        logger.log(Level.DEBUG, str);
        str = deleteWasteInfo(str);
        String[] points = splitLine(str);
        try {
            validation(points);
        } catch (ParamCountException | PointValidateException e) {
            logger.log(Level.ERROR, points.toString() + " is not valid quadrilateral. " + e);
            return false;
        }
        logger.log(Level.INFO, points.toString() + " is valid quadrilateral. ");
        return true;
    }

    private String deleteWasteInfo(String str) {
        return new QuadrLineHandler().deleteWasteInfo(str);
    }

    private String[] splitLine(String str) {
        return str.split(QuadrLineHandler.SPACE);
    }

    private void checkParamCount(String[] points) throws ParamCountException {
        if (points.length != Quadrilateral.SIDES_COUNT)
            throw new ParamCountException("Param count - " + points.length + ".");
    }

    private void checkValid(String[] points) throws ParamCountException, PointValidateException {
        for(int i = 0; i < points.length; i++)
            if (!pointValidator.isValid(points[i]))
                throw new PointValidateException("Point " + points[i] + " is wrong");
    }

    private void validation(String[] points) throws ParamCountException, PointValidateException {
        checkParamCount(points);
        checkValid(points);
    }


}
