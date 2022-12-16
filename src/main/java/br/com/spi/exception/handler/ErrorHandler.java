package br.com.spi.exception.handler;

import br.com.spi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ChavePixNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(Exception ex){
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChavePixDuplicateException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(Exception ex){
        return buildResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ChavePixValidationException.class, InvalidObjectException.class, InvalidStringException.class})
    public ResponseEntity<ErrorResponse> handlerBadRequestException(Exception ex){
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(Exception exception, HttpStatus status){
        var errorResponse = buildErrorResponse(exception, status);
        return ResponseEntity.status(status).body(errorResponse);
    }
    private ErrorResponse buildErrorResponse(Exception exception, HttpStatus status){
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(exception.getMessage())
                .build();

    }
}
