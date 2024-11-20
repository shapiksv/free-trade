package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.ErrorResponseDto;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionControllerHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDto> handleServiceException(FeignException ex, HttpServletRequest request) {
        log.error("Handle service exception:", ex);

        var code = ex.status();
        HttpStatus status = HttpStatus.valueOf(code);
        ErrorResponseDto response = new ErrorResponseDto(code, status.getReasonPhrase());

        return new ResponseEntity<>(response, status);
    }
}
