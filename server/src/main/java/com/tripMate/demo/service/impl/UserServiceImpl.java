package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.UserMapper;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO, String role) {



        return null;
    }

    @Override
    public UserDTO getUserById(int id) throws ResourceNotFoundException {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("the user with id "+id+ " has not been found"));
        return userMapper.toUserDTO(user);
    }


    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        return userMapper.toUserDTO(userRepository.save(user));
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
