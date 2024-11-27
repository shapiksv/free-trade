package com.trade.free.item.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import com.trade.free.item.enums.ItemStatus;
import org.springframework.http.HttpStatus;

public class ItemNotAvailableException extends TradeFreeMainException {

    private final static String MESSAGE = "item with id: [%s] has the status of [%s] but must have [%s]";

    public ItemNotAvailableException(Integer id, ItemStatus currentStatus, ItemStatus expectStatus) {
        super(String.format(MESSAGE, id, currentStatus, expectStatus), HttpStatus.BAD_REQUEST.value());
    }
}
