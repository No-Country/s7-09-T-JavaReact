package com.tripMate.demo.service;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDTO createUser (UserCreateDTO userCreateDTO, String role);

    UserDTO getUserById(int id) throws ResourceNotFoundException;

    UserDTO updateUser(int id, UserDTO userDTO);
    void deleteUser(int id);
}
