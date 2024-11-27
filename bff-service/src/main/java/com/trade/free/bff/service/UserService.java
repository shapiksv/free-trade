package com.trade.free.bff.service;

import com.trade.free.dto.rest.user.UserExternalRespDto;

public interface UserService {

    UserExternalRespDto getUser(String token);
}
