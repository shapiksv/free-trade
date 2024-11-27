package com.trade.free.user.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.enums.EventNotificationType;
import com.trade.free.dto.enums.EventUserType;
import com.trade.free.user.dto.UserDto;
import com.trade.free.user.entities.User;
import com.trade.free.user.exception.UserAlreadyExistException;
import com.trade.free.user.exception.UserNotFoundException;
import com.trade.free.user.mapper.UserMapper;
import com.trade.free.user.repository.UserRepository;
import com.trade.free.user.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.trade.free.dto.enums.EventNotificationType.REGISTERED_USER;
import static com.trade.free.dto.enums.EventUserType.CREATE;
import static com.trade.free.dto.enums.EventUserType.DELETE;

@Service
public class UserServiceImpl implements UserService {

    private final String userTopic;
    private final String notificationTopic;
    private final UserRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;

    public UserServiceImpl(@Value("${topic.user}") String userTopic,
                           @Value("${topic.notification}") String notificationTopic,
                           UserRepository repository,
                           KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper,
                           UserMapper userMapper) {
        this.userTopic = userTopic;
        this.notificationTopic = notificationTopic;
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.userMapper = userMapper;
    }

    @Override
    public User create(UserDto userDto) {

        if (repository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistException();
        }

        if (repository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistException();
        }
        var user = repository.save(User.create(userDto));
        sendEvent(user, CREATE, REGISTERED_USER);
        return user;
    }

    @Override
    public UserDto getById(Integer id) {
        return userMapper.toDto(repository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void delete(Integer userId) {
        var user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        repository.save(user.markAsDelete());
        sendEvent(user, DELETE, null);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @SneakyThrows
    private void sendEvent(User user, EventUserType type, EventNotificationType notificationType) {
        kafkaTemplate.send(userTopic, objectMapper.writeValueAsString(userMapper.toUserEvent(user, type)));
        if (notificationType != null) {
            kafkaTemplate.send(notificationTopic,
                    objectMapper.writeValueAsString(userMapper.toNotificationEvent(user, notificationType)));
        }
    }
}
