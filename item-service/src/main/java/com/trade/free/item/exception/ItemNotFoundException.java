package com.trade.free.item.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends TradeFreeMainException {

    private final static String MESSAGE = "item with id: [%s] not found";
    public ItemNotFoundException(Integer id) {
        super(String.format(MESSAGE, id), HttpStatus.NOT_FOUND.value());
    }
}
