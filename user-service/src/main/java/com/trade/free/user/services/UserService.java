package com.trade.free.user.services;

import com.trade.free.user.dto.UserDto;
import com.trade.free.user.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User create(UserDto userDto);

    UserDto getById(Integer id);

    void delete(Integer userId);

    User getByUsername(String username);

    UserDetailsService userDetailsService();
}
