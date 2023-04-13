package com.tripMate.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private int score;
    private String review;
    private Profile profile;
    private int experienceId;
}
