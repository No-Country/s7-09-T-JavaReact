package com.tripMate.demo.dto;


import com.tripMate.demo.entity.Role;
import com.tripMate.demo.util.RoleEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String lastname;
    private String email;
    private RoleEnum role;
    private String token;
}
