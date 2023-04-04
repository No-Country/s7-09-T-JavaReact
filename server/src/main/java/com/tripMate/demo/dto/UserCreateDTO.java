package com.tripMate.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateDTO {

    private String name;
    private String lastname;
    private String email;
    private String password;
}
