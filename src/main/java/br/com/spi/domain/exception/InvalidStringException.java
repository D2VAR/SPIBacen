package br.com.spi.domain.exception;

public class InvalidStringException extends RuntimeException{
    public InvalidStringException(String msg){
        super(msg);
    }
}
