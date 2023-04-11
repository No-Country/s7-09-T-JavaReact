package com.tripMate.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
