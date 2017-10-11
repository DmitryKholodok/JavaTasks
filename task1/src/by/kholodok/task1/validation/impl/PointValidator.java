package by.kholodok.task1.validation.impl;

import by.kholodok.task1.handler.impl.PointLineHandler;
import by.kholodok.task1.handler.impl.QuadrLineHandler;
import by.kholodok.task1.validation.Validator;
import by.kholodok.task1.exception.ParamCountException;

public class PointValidator implements Validator {

    @Override
    public boolean isValid(String str) {
        str = deleteWasteInfo(str);
        String[] coord = splitLine(str);
        try {
            validation(coord);
        } catch (ParamCountException | NumberFormatException e) {
            return false;
        }
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
