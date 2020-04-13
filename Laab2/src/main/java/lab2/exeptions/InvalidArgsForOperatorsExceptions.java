package lab2.exeptions;

public class InvalidArgsForOperatorsExceptions extends Exception{
    public InvalidArgsForOperatorsExceptions() {
        super();
    }

    public InvalidArgsForOperatorsExceptions(String message) {
        super(message);
    }

    public InvalidArgsForOperatorsExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgsForOperatorsExceptions(Throwable cause) {
        super(cause);
    }

    protected InvalidArgsForOperatorsExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
