package com.trade.free.wallet_service.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class SameWalletException extends TradeFreeMainException {

    private final static String MESSAGE = "Sender and recipient wallets must be different.";

    public SameWalletException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST.value());
    }
}
