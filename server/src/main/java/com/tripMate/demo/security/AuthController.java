package com.tripMate.demo.security;

import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="**")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final JwtEncoder jwtEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRespone> login(@RequestBody AuthenticationRequest request) throws ResourceNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String jwt = createToken(authentication);
        return ResponseEntity.ok(new AuthenticationRespone(jwt, userService.getUserByEmail(request.getEmail())));
    }

    @GetMapping("/test")
    public String test() {
    	return "test";
    }

    private String createToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*60*24*7))
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }


}
