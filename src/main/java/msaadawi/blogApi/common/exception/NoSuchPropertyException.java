package msaadawi.blogApi.common.exception;

public class NoSuchPropertyException extends RuntimeException {

    public NoSuchPropertyException() {
        super();
    }

    public NoSuchPropertyException(String message) {
        super(message);
    }

    public NoSuchPropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
