package com.trade.free.transfer.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class TransferNotFoundException extends TradeFreeMainException {

    private final static String MESSAGE = "Transfer with id: [%s] not found";

    public TransferNotFoundException(Integer id) {
        super(String.format(MESSAGE, id), HttpStatus.NOT_FOUND.value());
    }
}
