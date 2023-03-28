package com.tripMate.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", length = 350)
    private String title;

    @Column(name = "icon", length = 350)
    private String icon;

    @OneToMany()
    @JoinColumn(name = "category_id")
    private Set<Tag> tags = new HashSet<>();


}
