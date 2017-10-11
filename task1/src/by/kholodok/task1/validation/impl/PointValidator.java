package by.kholodok.task1.validation.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.handler.impl.PointLineHandler;
import by.kholodok.task1.handler.impl.QuadrLineHandler;
import by.kholodok.task1.validation.Validator;
import by.kholodok.task1.exception.ParamCountException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointValidator implements Validator {

    private static Logger logger = LogManager.getLogger(PointValidator.class);

    @Override
    public boolean isValid(String str) {
        str = deleteWasteInfo(str);
        String[] coord = splitLine(str);
        try {
            validation(coord);
        } catch (ParamCountException | NumberFormatException e) {
            logger.log(Level.ERROR, coord.toString() + " is not valid point." + e);
            return false;
        }
        logger.log(Level.INFO, coord.toString() + " is valid point.");
        return true;
    }

    private String[] splitLine(String str) {
        return str.split(QuadrLineHandler.COMMA);
    }

    private void checkParamCount(String[] params) throws ParamCountException {
        if (params.length != 2)
            throw new ParamCountException("Param count - " + params.length + ".");
    }

    private void checkNumberFormat(String x, String y) throws NumberFormatException {
        double num = Double.parseDouble(x);
        num = Double.parseDouble(y);
    }

    private String deleteWasteInfo(String str) {
        return new PointLineHandler().deleteWasteInfo(str);
    }

    private void validation(String[] coord) throws ParamCountException, NumberFormatException {
        checkParamCount(coord);
        checkNumberFormat(coord[0], coord[1]);
    }

}
