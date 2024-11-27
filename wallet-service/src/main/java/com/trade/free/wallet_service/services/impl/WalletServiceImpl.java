package com.trade.free.wallet_service.services.impl;


import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.wallet_service.dto.WalletDto;
import com.trade.free.wallet_service.entities.Wallet;
import com.trade.free.wallet_service.exception.NegativeAmountException;
import com.trade.free.wallet_service.exception.NotEnoughMoneyException;
import com.trade.free.wallet_service.exception.SameWalletException;
import com.trade.free.wallet_service.exception.WalletNotFoundException;
import com.trade.free.wallet_service.mapper.WalletMapper;
import com.trade.free.wallet_service.repository.WalletRepository;
import com.trade.free.wallet_service.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final static Integer LENGTH = 6;
    private final static Boolean USE_LETTERS = false;
    private final static Boolean USE_NUMBERS = true;

    private final WalletRepository repository;
    private final WalletMapper mapper;

    @Override
    public Wallet create(Integer userId) {
        Optional<Wallet> walletOptional = repository.findByUserId(userId);
        if (walletOptional.isPresent()) {
            return walletOptional.get();
        }
        String serial = RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);
        while (repository.existsBySerial(serial)) {
            serial = RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);
        }
        return repository.save(Wallet.create(userId, serial, "groshyk"));
    }

    @Override
    public WalletDto getByUserId(Integer userId) {
        return mapper.mapToDto(repository.findByUserId(userId).orElse(create(userId)));
    }

    @Override
    public void transferAmount(EventConductorDto eventDto) {

        var senderWallet = repository.findByUserId(eventDto.getSenderUserId())
                .orElseThrow(WalletNotFoundException::new);
        var receiverWallet = repository.findByUserId(eventDto.getReceiverUserId())
                .orElseThrow(WalletNotFoundException::new);

        validateByWalletNumber(senderWallet,
                receiverWallet,
                eventDto.getAmount());

        transferAmount(senderWallet, receiverWallet, eventDto.getAmount());
    }

    private void transferAmount(Wallet senderWallet, Wallet receiverWallet, BigDecimal amount) {
        senderWallet.pushAmount(amount.multiply(BigDecimal.valueOf(-1)));
        repository.save(senderWallet);
        receiverWallet.pushAmount(amount);
        repository.save(receiverWallet);
    }

    private void validateByWalletNumber(Wallet senderWallet,
                                        Wallet receiverWallet,
                                        BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException();
        }
        if (senderWallet.equals(receiverWallet)) {
            throw new SameWalletException();
        }
        if (!senderWallet.checkAvailableAmount(amount)) {
            throw new NotEnoughMoneyException();
        }
    }
}
