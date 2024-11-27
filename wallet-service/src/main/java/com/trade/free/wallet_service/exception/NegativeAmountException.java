package com.trade.free.wallet_service.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class NegativeAmountException extends TradeFreeMainException {

    private final static String MESSAGE = "Amount must be greater than zero.";

    public NegativeAmountException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
