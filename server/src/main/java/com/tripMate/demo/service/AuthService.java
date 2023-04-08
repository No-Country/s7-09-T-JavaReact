package com.tripMate.demo.service;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    UserDTO register(UserCreateDTO request);
    UserDTO authenticate(UserCreateDTO request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
