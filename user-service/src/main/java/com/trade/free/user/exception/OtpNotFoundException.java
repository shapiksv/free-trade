package com.trade.free.user.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class OtpNotFoundException extends TradeFreeMainException {

    private final static String MESSAGE = "OTP not found";

    public OtpNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND.value());
    }
}
