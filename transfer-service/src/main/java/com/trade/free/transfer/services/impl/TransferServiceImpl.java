package com.trade.free.transfer.services.impl;

import com.trade.free.transfer.dto.CreateTransferDto;
import com.trade.free.transfer.dto.TransferDto;
import com.trade.free.transfer.dto.UpdateTransferDto;
import com.trade.free.transfer.entities.Transfer;
import com.trade.free.transfer.enums.TransferStatus;
import com.trade.free.transfer.exception.TransferNotFoundException;
import com.trade.free.transfer.mapper.TransferMapper;
import com.trade.free.transfer.repository.TransferRepository;
import com.trade.free.transfer.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;
    private final TransferMapper mapper;

    @Override
    public TransferDto create(CreateTransferDto createTransferDto) {
        return mapper.mapToDto(repository.save(Transfer.create(createTransferDto)));
    }

    @Override
    public void update(Integer transferId, UpdateTransferDto updateTransferDto) {
        repository.findById(transferId).ifPresent(transfer -> repository.save(transfer.update(updateTransferDto)));
    }

    @Override
    public void updateStatus(Integer transferId, TransferStatus status) {
        repository.findById(transferId).ifPresent(transfer -> repository.save(transfer.updateStatus(status)));
    }

    @Override
    public List<TransferDto> findAllByUserId(Integer userId) {
        return repository.findAllBySenderUserIdOrReceiverUserId(userId, userId).stream().map(mapper::mapToDto).toList();
    }

    @Override
    public TransferDto getById(Integer transferId) {
        return mapper.mapToDto(repository.findById(transferId).orElseThrow(() -> new TransferNotFoundException(transferId)));
    }
}
