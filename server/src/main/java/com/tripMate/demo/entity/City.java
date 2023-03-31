package com.tripMate.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@Table(name = "CITIES")
public class City {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CITY", nullable = false)
    @NonNull
    private String city;

    @Column(name = "PROVINCE", nullable = false)
    private String province;


    @Column(name = "COUNTRY", nullable = false)
    @NonNull
    private String country;



}
