package br.com.spi.adapter.out.dynamo.exception;

public class ChavePixDuplicateException extends RuntimeException{
    public ChavePixDuplicateException(String msg){
        super(msg);
    }
}
