package com.tripMate.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.tripMate.demo.entity.Category;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExperienceDTO {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private Set<ImageDTO> images = new HashSet<>();
    private CategoryDTO category;
    private CityDTO city;
    private String address;
    private float averageScore;
    private float latitude;
    private float longitude;
}
