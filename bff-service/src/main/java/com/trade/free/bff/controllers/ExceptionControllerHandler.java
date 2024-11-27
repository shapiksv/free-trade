package com.trade.free.bff.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.rest.ErrorResponseDto;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class ExceptionControllerHandler {

    private final ObjectMapper mapper;

    @SneakyThrows
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDto> handleServiceException(FeignException ex, HttpServletRequest request) {
        log.error("Handle service exception:", ex);

        var code = ex.status();
        HttpStatus status = HttpStatus.valueOf(code);
        var message = ex.getMessage();
        ErrorResponseDto response = null;
        if (message != null && message.contains("errorMessage")) {
            Pattern pattern = Pattern.compile("\\{\"code(.)*\"}");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                var stringMessage = matcher.group();
                response = mapper.readValue(stringMessage, ErrorResponseDto.class);
            }
        }
        return new ResponseEntity<>(response != null ? response : new ErrorResponseDto(code, status.getReasonPhrase()),
                status);
    }
}
