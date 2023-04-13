package com.tripMate.demo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Profile {
// This is a DTO for the User entity class with the same fields except for the password, email and role
// does not contain sensitive data and is used for the public profile
    private int id;
    private String name;
    private String lastname;
}
