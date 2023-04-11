package com.tripMate.demo.mapper;


import com.tripMate.demo.dto.RegisterRequest;
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

    RegisterRequest  toUserCreateDTO(User user);
    User toUser(RegisterRequest request);
}
