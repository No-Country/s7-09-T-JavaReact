package com.tripMate.demo.controller;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.UserService;
import com.tripMate.demo.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser() throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(getUserEmail()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO user) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.createUser(user, String.valueOf(RoleEnum.USER)), HttpStatus.CREATED);
    }

    private String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private Boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }
}
