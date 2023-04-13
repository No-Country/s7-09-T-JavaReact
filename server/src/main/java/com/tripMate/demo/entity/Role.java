package com.tripMate.demo.entity;

import com.tripMate.demo.util.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

//@Entity(name = "ROLES")
/*@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter*/
public class Role {

    /*

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="NAME")
    private RoleEnum role;

    /*
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();*/
}
