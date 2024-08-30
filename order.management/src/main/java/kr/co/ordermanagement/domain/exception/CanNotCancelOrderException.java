package kr.co.ordermanagement.domain.exception;

public class CanNotCancelOrderException extends RuntimeException {
    public CanNotCancelOrderException(String message) {
        super(message);
    }
}
