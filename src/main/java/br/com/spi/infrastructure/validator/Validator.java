package br.com.spi.infrastructure.validator;

import br.com.spi.exception.InvalidObjectException;
import br.com.spi.exception.InvalidStringException;

import java.util.Objects;

public class Validator{

    private Validator(){}
    public static void validateObject(Object obj){
        if (Objects.isNull(obj))
            throw new InvalidObjectException("Null parameter!");
    }

    public static void validateString(String str){
        validateObject(str);
        if (str.isBlank())
            throw new InvalidStringException("Blank parameter!");
    }
}
