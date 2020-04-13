package lab2.exeptions;

public class NotEnoughElementsInTheStackExceptions extends Exception{
    public NotEnoughElementsInTheStackExceptions() {
        super();
    }

    public NotEnoughElementsInTheStackExceptions(String message) {
        super(message);
    }

    public NotEnoughElementsInTheStackExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughElementsInTheStackExceptions(Throwable cause) {
        super(cause);
    }

    protected NotEnoughElementsInTheStackExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
