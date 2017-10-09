package by.kholodok.task1.validation.exception;

public class ParamCountException extends Exception {

    private static final long serialVersionUID = 1L;

    public ParamCountException(String message) {
        super(message);
    }

    public ParamCountException(String message, Exception e) {
        super(message, e);
    }

    public ParamCountException(Exception e) { super(e); }

}
