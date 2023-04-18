package com.tripMate.demo.dto;


import com.tripMate.demo.util.RoleEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
// This is a DTO for the User entity class with the same fields except for the password
// contains sensitive data
    private int id;
    private String name;
    private String lastname;
    private String email;
    private RoleEnum role;
}
