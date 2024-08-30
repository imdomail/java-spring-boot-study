package kr.co.ordermanagement.domain.exception;

public class LackOfProductAmountException extends RuntimeException {
    public LackOfProductAmountException(String message) {
        super(message);
    }
}
