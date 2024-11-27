package com.trade.free.dto.exception;

public class TradeFreeMainException extends RuntimeException {

    private final String errorMessage;
    private Integer code;

    public TradeFreeMainException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TradeFreeMainException(String errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getCode() {
        return code;
    }
}
