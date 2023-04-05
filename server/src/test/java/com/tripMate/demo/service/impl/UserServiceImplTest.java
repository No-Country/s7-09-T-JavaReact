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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserService userService;

    List<User> users;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        users.add(User.builder()
                .id(1)
                .email("jroge@email.com")
                .name("Jorge").lastname("Perez")
                .password("123456")
                .role(RoleEnum.USER)
                .build());
        users.add(User.builder()
                .id(2)
                .email("robert@email.com")
                .name("Robert")
                .lastname("Suarez")
                .role(RoleEnum.USER)
                .password("123456")
                .build());
    }

    private void compareUserWithUserDTO(User user, UserDTO userDTO){
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getLastname(), user.getLastname());
        assertEquals(userDTO.getRole(), user.getRole());
    }

    @Test
    void shouldGetUserByEmail() throws ResourceNotFoundException {
        //given
        User user = users.get(0);
        String email = user.getEmail();
        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDTO userFound = userService.getUserByEmail(email);

        //then
        compareUserWithUserDTO(user, userFound);

    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundByEmail() {
        when(userRepository.findByEmail("wrongEmail@email.com")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByEmail("wrongEmail@email.com"));
    }

    @Test
    void shouldGetUserById() throws ResourceNotFoundException {
        //given
        User user = users.get(0);
        int id = user.getId();
        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO userFound = userService.getUserById(id);

        //then
        compareUserWithUserDTO(user, userFound);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundById() {
        when(userRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(3));
    }

    @Test
    void shouldCreateUser() throws ResourceNotFoundException, ResourceAlreadyExistsException {
        //given
        User user = users.get(0);
        UserCreateDTO userCreateDTO = UserCreateDTO.builder().
                name(user.getName()).
                lastname(user.getLastname()).
                email(user.getEmail()).
                password(user.getPassword()).
                build();

        //when
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User invoquedUser = invocation.getArgument(0);
                    return User.builder()
                            .id(user.getId()) // ID generada automáticamente
                            .name(invoquedUser.getName())
                            .lastname(invoquedUser.getLastname())
                            .email(invoquedUser.getEmail())
                            .password(invoquedUser.getPassword())
                            .role(RoleEnum.USER)
                            .build();
                });
        when(encoder.encode(user.getPassword())).thenReturn(user.getPassword());


        UserDTO userCreated = userService.createUser(userCreateDTO, "USER");

        //then
        compareUserWithUserDTO(user, userCreated);
    }

    @Test
    void shouldThrowResourceAlreadyExistsExceptionWhenUserAlreadyExists() {
        //given
        User user = users.get(0);
        UserCreateDTO userCreateDTO = UserCreateDTO.builder().
                name(user.getName()).
                lastname(user.getLastname()).
                email(user.getEmail()).
                password(user.getPassword()).
                build();

        //when
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        when(encoder.encode(user.getPassword())).thenReturn(user.getPassword());

        //then
        assertThrows(ResourceAlreadyExistsException.class, () -> userService.createUser(userCreateDTO, "USER"));
    }

    @Test
    void shouldUpdateUser() throws ResourceNotFoundException {
        // given
        User originalUser = users.get(0);
        UserCreateDTO userDTO = userMapper.toUserCreateDTO(originalUser);
        userDTO.setName("Jorge Updated");
        userDTO.setLastname("Perez Updated");

        // when
        when(userRepository.findById(originalUser.getId())).thenReturn(Optional.of(originalUser));
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User updatedUser = invocation.getArgument(0);
                    return User.builder()
                            .id(updatedUser.getId())
                            .name(updatedUser.getName())
                            .lastname(updatedUser.getLastname())
                            .email(updatedUser.getEmail())
                            .password(updatedUser.getPassword())
                            .role(RoleEnum.USER)
                            .build();
                });

        UserDTO userUpdated = userService.updateUser(originalUser.getId(), userDTO);

        // then
        compareUserWithUserDTO(originalUser, userUpdated);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundToUpdate() {
        // given
        User originalUser = users.get(0);
        UserCreateDTO userDTO = userMapper.toUserCreateDTO(originalUser);
        userDTO.setName("Jorge Updated");
        userDTO.setLastname("Perez Updated");

        // when
        when(userRepository.findById(originalUser.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(originalUser.getId(), userDTO));
    }

    @Test
    void shouldDeleteUser() throws ResourceNotFoundException {
        // given
        User user = users.get(0);
        int id = user.getId();

        // when
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deleteUser(id);

        // then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundToDelete() {
        // given
        User user = users.get(0);
        int id = user.getId();

        // when
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(id));
        verify(userRepository, times(0)).deleteById(id);
    }

    @Test
    void shouldLoadUserByUsername() {
        // given
        User user = users.get(0);
        String email = user.getEmail();

        // when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(email);

        // then
        assertEquals(userDetails.getUsername(), user.getEmail());
        assertEquals(userDetails.getPassword(), user.getPassword());
        assertEquals(userDetails.getAuthorities().size(), 1);
        assertEquals(userDetails.getAuthorities().iterator().next().getAuthority(), "ROLE_" + user.getRole().name());
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenUserNotFound() {
        // given
        String email = "wrongEmail";

        // when
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // then
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(email));
    }






}