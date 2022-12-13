package br.com.spi.exception;

public class ChavePixNaoEncontradaException extends RuntimeException{

    public ChavePixNaoEncontradaException() {
        super("Chave pix não localizada.");
    }

    public ChavePixNaoEncontradaException(String message) {
        super(message);
    }

    public ChavePixNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}

