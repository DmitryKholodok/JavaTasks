package by.kholodok.task1.action.exception;

public class NotQuadrException extends Exception {

    private static final long serialVersionUID = 2L;

    public NotQuadrException(String message) {
        super(message);
    }

    public NotQuadrException(String message, Exception e) {
        super(message, e);
    }

    public NotQuadrException(Exception e) { super(e); }

}
