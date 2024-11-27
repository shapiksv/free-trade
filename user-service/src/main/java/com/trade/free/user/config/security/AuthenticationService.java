package com.trade.free.user.config.security;

import com.trade.free.dto.rest.user.JwtAuthenticationRespDto;
import com.trade.free.dto.rest.user.SignInReqDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import com.trade.free.user.dto.UserDto;
import com.trade.free.user.enums.Role;
import com.trade.free.user.exception.WrongOtpException;
import com.trade.free.user.mapper.UserMapper;
import com.trade.free.user.services.ConfirmationService;
import com.trade.free.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final ConfirmationService confirmationService;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationRespDto signUp(SignUpReqDto request) {

        if (!confirmationService.checkOtp(request.getOtp(), request.getEmail())) {
            throw new WrongOtpException();
        }
        UserDto userDto = mapper.toDto(request);
        userDto.setPassword(passwordEncoder.encode(request.getPassword()));
        userDto.setRole(Role.ROLE_USER);

        var user = userService.create(userDto);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationRespDto(jwt);
    }

    public JwtAuthenticationRespDto signIn(SignInReqDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationRespDto(jwt);
    }
}
