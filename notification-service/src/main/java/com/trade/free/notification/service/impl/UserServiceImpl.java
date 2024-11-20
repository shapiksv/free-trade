package com.trade.free.notification.service.impl;

import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.notification.entities.User;
import com.trade.free.notification.repository.UserRepository;
import com.trade.free.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void create(EventNotificationDto event) {
        repository.save(User.create(event.getUserId(), event.getFirstName(), event.getSecondName(), event.getContact()));
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return repository.findById(id);
    }
}
