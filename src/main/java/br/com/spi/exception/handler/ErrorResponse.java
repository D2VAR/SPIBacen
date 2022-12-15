package br.com.spi.exception.handler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
}
