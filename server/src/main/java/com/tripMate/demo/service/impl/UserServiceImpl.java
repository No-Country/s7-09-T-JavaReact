package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.UserMapper;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.UserService;
import com.tripMate.demo.util.RoleEnum;
import com.tripMate.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO, String role) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        if(userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        User user = userMapper.toUser(userCreateDTO);
        user.setPassword(encoder.encode(user.getPassword()));

        try {
            user.setRole(RoleEnum.valueOf(role));
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Role not found");
        }
        User saved = userRepository.save(user);

        return userMapper.toUserDTO(saved);

    }

    @Override
    public UserDTO getUserById(int id) throws ResourceNotFoundException {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("the user with id "+id+ " has not been found"));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws ResourceNotFoundException {
        return userMapper.toUserDTO(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("the user with email "+email+ " has not been found")
        ));
    }


    @Override
    public UserDTO updateUser(int id, UserCreateDTO userDTO) throws ResourceNotFoundException {
        User inputUser = userMapper.toUser(userDTO);
        User newUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Util.mergeObjects(inputUser, newUser);
        return userMapper.toUserDTO(userRepository.save(newUser));
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional =  userRepository.findByEmail(username);
        if(optional.isPresent()) {
            User user = optional.get();
            String[] roles = {user.getRole().toString()};

            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(roles).build();
        }
        else throw new UsernameNotFoundException("User " + username + " not found");

    }
}
