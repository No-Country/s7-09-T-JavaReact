package com.tripMate.demo.mapper;


import com.tripMate.demo.dto.Profile;
import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUsersDTO(List<User> users);
    List<User> toUsers(List<UserDTO> users);

    UserCreateDTO toUserCreateDTO(User user);
    User toUser(UserCreateDTO userDTO);
    Profile toProfile(User user);
    List<Profile> toProfiles(List<User> users);
    User toUser(Profile profile);
    List<User> profileToUsers(List<Profile> profiles);
}
