package by.kholodok.task1.exception;

public class ShapeException extends Exception {

    private static final long serialVersionUID = 1L;

    public ShapeException(String message) {
        super(message);
    }

    public ShapeException(String message, Exception e) {
        super(message, e);
    }

    public ShapeException(Exception e) { super(e); }

}
