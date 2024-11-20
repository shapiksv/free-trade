package com.trade.free.wallet_service.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class NotEnoughMoneyException extends TradeFreeMainException {

    private final static String MESSAGE = "Not enough money in the wallet.";

    public NotEnoughMoneyException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
