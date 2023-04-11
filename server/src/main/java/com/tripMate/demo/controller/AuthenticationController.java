package com.tripMate.demo.controller;

import com.tripMate.demo.dto.AuthenticationRequest;
import com.tripMate.demo.dto.RegisterRequest;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.service.AuthenticationService;
import com.tripMate.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(
      @RequestBody RegisterRequest request
  ) throws ResourceAlreadyExistsException {
    if(userService.existUsername(request.getEmail())){
      throw new ResourceAlreadyExistsException("user already exists");

    }
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<UserDTO> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
