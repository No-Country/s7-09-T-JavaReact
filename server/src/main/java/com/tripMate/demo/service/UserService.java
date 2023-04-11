package com.tripMate.demo.service;

import com.tripMate.demo.dto.RegisterRequest;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDTO createUser (RegisterRequest request, String role) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    UserDTO getUserById(int id) throws ResourceNotFoundException;
    UserDTO getUserByEmail(String email) throws ResourceNotFoundException;

    Boolean existUsername(String email);



    UserDTO updateUser(int id, RegisterRequest request) throws ResourceNotFoundException;
    void deleteUser(int id) throws ResourceNotFoundException;
}
