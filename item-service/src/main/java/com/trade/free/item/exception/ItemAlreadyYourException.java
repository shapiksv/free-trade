package com.trade.free.item.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class ItemAlreadyYourException extends TradeFreeMainException {

    private final static String MESSAGE = "item with id: [%s] already your";
    public ItemAlreadyYourException(Integer id) {
        super(String.format(MESSAGE, id), HttpStatus.BAD_REQUEST.value());
    }
}
