package com.trade.free.user.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends TradeFreeMainException {

    private final static String MESSAGE = "User with email or username already exist";

    public UserAlreadyExistException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
