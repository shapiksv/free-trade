package com.trade.free.bff.service.impl;

import com.trade.free.bff.client.ItemClient;
import com.trade.free.bff.client.UserClient;
import com.trade.free.bff.client.WalletClient;
import com.trade.free.bff.mapper.BffMapper;
import com.trade.free.bff.service.UserService;
import com.trade.free.dto.rest.user.UserExternalRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;
    private final ItemClient itemClient;
    private final WalletClient walletClient;
    private final BffMapper bffMapper;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public UserExternalRespDto getUser(String token) {
        var user = createCircuitBreaker().run(() -> userClient.get(token)).getBody();
        var wallet = createCircuitBreaker().run(() -> walletClient.get(token)).getBody();
        var items = createCircuitBreaker().run(() -> itemClient.getAll(token)).getBody();

        return bffMapper.toExternal(user, wallet, items);
    }

    private CircuitBreaker createCircuitBreaker() {
        return circuitBreakerFactory.create("circuitbreaker");
    }
}
