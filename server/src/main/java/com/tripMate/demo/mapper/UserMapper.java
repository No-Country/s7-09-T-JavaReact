package com.tripMate.demo.mapper;


import com.tripMate.demo.dto.UserDTO;
import com.tripMate.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO toUserDTO(User user);
    User toUse(UserDTO userDTO);
    List<UserDTO> toUserDTO(List<User> users);
    List<User> toUsers(List<UserDTO> users);
}
