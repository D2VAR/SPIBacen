package br.com.spi.exception;

public class InvalidStringException extends RuntimeException{
    public InvalidStringException(String msg){
        super(msg);
    }
}
