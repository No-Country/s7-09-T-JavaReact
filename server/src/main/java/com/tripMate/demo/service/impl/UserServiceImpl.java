package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.UserMapper;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(int id) throws ResourceNotFoundException {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("the user with id "+id+ " has not been found"));
        return userMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        Pageable userPages=  userRepository.findAll();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
