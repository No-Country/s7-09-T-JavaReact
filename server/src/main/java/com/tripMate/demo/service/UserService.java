package com.tripMate.demo.service;



import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO getUserById(int id) throws ResourceNotFoundException;
    List<UserDTO> getAll();
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(int id);
}
