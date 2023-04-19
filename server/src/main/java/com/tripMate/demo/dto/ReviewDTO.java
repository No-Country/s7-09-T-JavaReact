package com.tripMate.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private int score;
    private String review;
    private LocalDate date;
    private Profile profile;
    private int experienceId;
    private float experienceAverageScore;
}
