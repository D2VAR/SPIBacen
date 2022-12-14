package br.com.spi.adapter.exception;

public class ChaveValidatorException extends RuntimeException{

    public ChaveValidatorException() {
        super();
    }

    public ChaveValidatorException(String message) {
        super(message);
    }

    public ChaveValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}