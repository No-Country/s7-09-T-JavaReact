package com.tripMate.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAGS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE", length = 350)
    private String title;

    @Column(name = "ICON", length = 350)
    private String icon;



}
