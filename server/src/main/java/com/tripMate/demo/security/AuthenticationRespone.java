package com.tripMate.demo.security;

import com.tripMate.demo.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationRespone {
    private String jwt;
}
