package com.trade.free.wallet_service.controllers;

import com.trade.free.dto.exception.TradeFreeMainException;
import com.trade.free.dto.rest.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionControllerHandler {

    @ExceptionHandler(TradeFreeMainException.class)
    public ResponseEntity<ErrorResponseDto> handleServiceException(TradeFreeMainException ex, HttpServletRequest request) {
        log.error("Handle service exception:", ex);

        Integer code = ex.getCode();
        HttpStatus status = HttpStatus.valueOf(code);
        ErrorResponseDto response = new ErrorResponseDto(code, ex.getErrorMessage());

        return new ResponseEntity<>(response, status);
    }
}
