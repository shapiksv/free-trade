package com.trade.free.user.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends TradeFreeMainException {

    private final static String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND.value());
    }
}
