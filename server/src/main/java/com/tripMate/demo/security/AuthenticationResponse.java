package com.tripMate.demo.security;

import com.tripMate.demo.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private UserDTO user;
}
