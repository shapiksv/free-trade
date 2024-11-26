package com.trade.free.item.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class WrongStatusException extends TradeFreeMainException {

    private final static String MESSAGE = "item has wrong status for action";
    public WrongStatusException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
