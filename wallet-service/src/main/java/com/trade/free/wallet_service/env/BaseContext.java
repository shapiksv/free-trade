package com.trade.free.wallet_service.env;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Component
@RequestScope
@AllArgsConstructor
public class BaseContext {

    public static final String JWT_TOKEN_KEY = "JWT_TOKEN_KEY";
    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String USER_ID_KEY = "USER_ID_KEY";

    private final Map<String, Object> properties;

    public void addProperty(String name, Object value) {
        properties.put(name, value);
    }


    public String getJwtToken() {
        return (String) properties.get(JWT_TOKEN_KEY);
    }

    public String getUsername() {
        return (String) properties.get(USERNAME_KEY);
    }

    public Integer getUserId() {
        return (Integer) properties.get(USER_ID_KEY);
    }


}
