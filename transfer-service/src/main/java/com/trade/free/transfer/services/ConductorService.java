package com.trade.free.transfer.services;

import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.transfer.dto.TransferDto;

public interface ConductorService {

    TransferDto purchaseOfItem(Integer userId, Integer itemId);

    void process(EventConductorDto eventDto);
}
