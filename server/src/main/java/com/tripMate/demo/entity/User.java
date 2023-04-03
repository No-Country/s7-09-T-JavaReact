package com.tripMate.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="NAME", nullable = false, length = 350)
    private String name;

    @Column(name="LASTNAME", length = 350)
    private String lastname;

    @Column(name="EMAIL", nullable = false, length = 2048, unique = true)
    @NotEmpty
    @Email
    private String email;

    @Column(name="PASSWORD", nullable = false, length = 100)
    @NotEmpty
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonProperty(access =  JsonProperty.Access.WRITE_ONLY)
    private Role role;
}
