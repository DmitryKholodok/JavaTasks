package by.kholodok.task1.exception;

public class PointValidateException extends Exception {

    private static final long serialVersionUID = 3L;

    public PointValidateException(String message) {
        super(message);
    }

    public PointValidateException(String message, Exception e) {
        super(message, e);
    }

    public PointValidateException(Exception e) { super(e); }

}
