package com.trade.free.user.services;

public interface ConfirmationService {

    void createOtp(String email);

    boolean checkOtp(String otp, String email);
}
