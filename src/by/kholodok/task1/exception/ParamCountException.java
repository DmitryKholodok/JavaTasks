package by.kholodok.task1.exception;

public class ParamCountException extends Exception {

    private static final long serialVersionUID = 2L;

    public ParamCountException(String message) {
        super(message);
    }

    public ParamCountException(String message, Exception e) {
        super(message, e);
    }

    public ParamCountException(Exception e) { super(e); }

}
