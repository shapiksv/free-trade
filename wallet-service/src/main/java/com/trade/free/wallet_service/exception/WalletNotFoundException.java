package com.trade.free.wallet_service.exception;

import com.trade.free.dto.exception.TradeFreeMainException;
import org.springframework.http.HttpStatus;

public class WalletNotFoundException extends TradeFreeMainException {

    private final static String MESSAGE = "Wallet not found";

    public WalletNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND.value());
    }
}
