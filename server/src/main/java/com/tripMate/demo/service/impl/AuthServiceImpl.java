package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.security.TokenUtil;
import com.tripMate.demo.service.AuthService;
import com.tripMate.demo.util.RoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired

    private AuthenticationManager authenticationManager;
    @Override
    public UserDTO register(UserCreateDTO request) {
        var user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = tokenUtil.createToken(user);

        return UserDTO.builder().token(jwtToken)
                .build();
    }

    @Override
    public UserDTO authenticate(UserCreateDTO request) {
        return null;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
