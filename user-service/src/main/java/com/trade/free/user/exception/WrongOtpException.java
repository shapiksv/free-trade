package com.trade.free.user.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class WrongOtpException extends TradeFreeMainException {

    private final static String MESSAGE = "OTP invalid";

    public WrongOtpException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
