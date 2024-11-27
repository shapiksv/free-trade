package com.trade.free.transfer.services;


import com.trade.free.transfer.dto.CreateTransferDto;
import com.trade.free.transfer.dto.TransferDto;
import com.trade.free.transfer.dto.UpdateTransferDto;
import com.trade.free.transfer.enums.TransferStatus;

import java.util.List;

public interface TransferService {

    TransferDto create(CreateTransferDto createTransferDto);

    void update(Integer transferId, UpdateTransferDto updateTransferDto);

    void updateStatus(Integer transferId, TransferStatus status);

    List<TransferDto> findAllByUserId(Integer userId);

    TransferDto getById(Integer transferId);
}
