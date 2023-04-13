package com.tripMate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EXPERIENCES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE", length = 350, nullable = false)
    @NonNull
    @Builder.Default
    private String title="Default Title";

    @Column(name = "SUBTITLE", length = 350, nullable = false)
    @NonNull
    @Builder.Default
    private String subtitle = "Default Subtitle";


    @Column(name = "DESCRIPTION", columnDefinition="TEXT", nullable = false)
    @Lob
    private String description;


/*    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXPERIENCE_ID")
    private Set<Review> reviews = new HashSet<>();
*/

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXPERIENCE_ID")
    @Builder.Default
    private Set<Image> images = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @Builder.Default
    private Category category=new Category();

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    @Builder.Default
    private City city=new City();


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONTACT_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    @Builder.Default
    private Contact contact=new Contact();


    @Column(name = "ADDRESS", length = 350)
    private String address;

    @Column(name = "AVERAGE_SCORE")
    private float averageScore;

    @Column(name = "LATITUDE")
    private float latitude;
    @Column(name = "LONGITUDE")
    private float longitude;


}
