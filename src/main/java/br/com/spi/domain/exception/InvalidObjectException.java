package br.com.spi.domain.exception;

public class InvalidObjectException extends RuntimeException{
    public InvalidObjectException(String msg){
        super(msg);
    }
}
