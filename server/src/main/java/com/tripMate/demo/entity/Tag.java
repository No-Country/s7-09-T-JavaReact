package com.tripMate.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tags")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", length = 350)
    private String title;

    @Column(name = "icon", length = 350)
    private String icon;



}
