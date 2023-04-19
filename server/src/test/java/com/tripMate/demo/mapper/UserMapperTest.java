package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.Profile;
import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.util.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper mapper = UserMapper.INSTANCE;
    List<User> users = new ArrayList<>();
    List<UserDTO> userDTOS = new ArrayList<>();
    List<UserCreateDTO> userCreateDTOS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        users.add(User.builder().id(1).email("email1").name("name1").lastname("lastname1").password("password1").role(RoleEnum.USER).build());
        users.add(User.builder().id(2).email("email2").name("name2").lastname("lastname2").password("password2").role(RoleEnum.ADMIN).build());
        users.add(User.builder().id(3).email("email3").name("name3").lastname("lastname3").password("password3").role(RoleEnum.USER).build());

        userDTOS.add(UserDTO.builder().id(1).email("email1").name("name1").lastname("lastname1").role(RoleEnum.USER).build());
        userDTOS.add(UserDTO.builder().id(2).email("email2").name("name2").lastname("lastname2").role(RoleEnum.USER).build());
        userDTOS.add(UserDTO.builder().id(3).email("email3").name("name3").lastname("lastname3").role(RoleEnum.USER).build());

        userCreateDTOS.add(UserCreateDTO.builder().email("email1").name("name1").lastname("lastname1").password("password1").build());
        userCreateDTOS.add(UserCreateDTO.builder().email("email2").name("name2").lastname("lastname2").password("password2").build());
        userCreateDTOS.add(UserCreateDTO.builder().email("email3").name("name3").lastname("lastname3").password("password3").build());
    }

    @Test
    void userToUserDTO() {
        users.forEach(user -> {
            UserDTO userDTO = mapper.toUserDTO(user);
            assertEquals(user.getId(), userDTO.getId());
            assertEquals(user.getEmail(), userDTO.getEmail());
            assertEquals(user.getName(), userDTO.getName());
            assertEquals(user.getLastname(), userDTO.getLastname());
            assertEquals(user.getRole(), userDTO.getRole());
        });
    }

    @Test
    void usersToUserDTOs() {
        List<UserDTO> userDTOS = mapper.toUsersDTO(users);
        assertEquals(users.size(), userDTOS.size());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDTO userDTO = userDTOS.get(i);
            assertEquals(user.getId(), userDTO.getId());
            assertEquals(user.getEmail(), userDTO.getEmail());
            assertEquals(user.getName(), userDTO.getName());
            assertEquals(user.getLastname(), userDTO.getLastname());
            assertEquals(user.getRole(), userDTO.getRole());
        }
    }

    @Test
    void userCreateDTOToUser() {
        userCreateDTOS.forEach(userCreateDTO -> {
            User user = mapper.toUser(userCreateDTO);
            assertEquals(userCreateDTO.getEmail(), user.getEmail());
            assertEquals(userCreateDTO.getName(), user.getName());
            assertEquals(userCreateDTO.getLastname(), user.getLastname());
            assertEquals(userCreateDTO.getPassword(), user.getPassword());
        });
    }

    @Test
    void userCreateDTOToUserWithId() {
        userCreateDTOS.forEach(userCreateDTO -> {
            User user = mapper.toUser(userCreateDTO);
            assertEquals(0, user.getId());
            assertEquals(userCreateDTO.getEmail(), user.getEmail());
            assertEquals(userCreateDTO.getName(), user.getName());
            assertEquals(userCreateDTO.getLastname(), user.getLastname());
            assertEquals(userCreateDTO.getPassword(), user.getPassword());
        });
    }

    @Test
    void userDTOToUser() {
        userDTOS.forEach(userDTO -> {
            User user = mapper.toUser(userDTO);
            assertEquals(userDTO.getId(), user.getId());
            assertEquals(userDTO.getEmail(), user.getEmail());
            assertEquals(userDTO.getName(), user.getName());
            assertEquals(userDTO.getLastname(), user.getLastname());
            assertEquals(userDTO.getRole(), user.getRole());
        });
    }

    @Test
    void userToProfile() {
        users.forEach(user -> {
            Profile profile = mapper.toProfile(user);
            assertEquals(user.getId(), profile.getId());
            assertEquals(user.getName(), profile.getName());
            assertEquals(user.getLastname(), profile.getLastname());
        });
    }

    @Test
    void profileToUser() {
        users.forEach(users -> {
            Profile profile = Profile.builder().id(users.getId()).name(users.getName()).lastname(users.getLastname()).build();
            User user = mapper.toUser(profile);
            assertEquals(profile.getId(), user.getId());
            assertEquals(profile.getName(), user.getName());
            assertEquals(profile.getLastname(), user.getLastname());
            assertNull(user.getEmail());
            assertNull(user.getPassword());
            assertNull(user.getRole());
        });
    }

}