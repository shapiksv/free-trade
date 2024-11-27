package com.trade.free.wallet_service.services;

import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.wallet_service.dto.WalletDto;
import com.trade.free.wallet_service.entities.Wallet;

public interface WalletService {

    Wallet create(Integer userId);

    WalletDto getByUserId(Integer userId);

    void transferAmount(EventConductorDto eventDto);

}
