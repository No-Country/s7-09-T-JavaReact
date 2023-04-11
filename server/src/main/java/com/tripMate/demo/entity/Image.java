package com.tripMate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.dto.ExperienceDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@Table(name = "IMAGES")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "URL", length = 2048)
    private String url;

    @Column(name = "ALT", length = 1000)
    private String alt;

}
