package by.kholodok.task1.validation;

import by.kholodok.task1.validation.exception.ParamCountException;

public interface Validator {
    boolean isValid(String str);
}
