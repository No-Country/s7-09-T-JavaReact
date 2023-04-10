package com.tripMate.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateDTO {
// This is a DTO for the User entity creation
// Contains only the fields needed for the creation of a new user
    private String name;
    private String lastname;
    private String email;
    private String password;
}
