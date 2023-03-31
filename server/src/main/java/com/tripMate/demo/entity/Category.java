package com.tripMate.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE", length = 350)
    private String title;

    @Column(name = "ICON", length = 350)
    private String icon;

    @OneToMany()
    @JoinColumn(name = "CATEGORY_ID")
    private Set<Tag> tags = new HashSet<>();


}
