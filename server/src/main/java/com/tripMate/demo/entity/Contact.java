
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
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EMAIL",length = 2048, nullable = false)
    @NonNull
    private String email;

    @Column(name = "WHATSAPP",length = 2048, nullable = false)
    @NonNull
    private String whatsapp;

    @Column(name = "INSTAGRAM",length = 2048, nullable = false)
    @NonNull
    private String instagram;

    @Column(name = "PHONE",length = 350, nullable = false)
    @NonNull
    private String phone;

    @Column(name = "WEBSITE",length = 2048, nullable = false)
    @NonNull
    private String website;


}
