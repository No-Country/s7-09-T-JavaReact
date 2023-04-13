package com.tripMate.demo.dto;

import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ReviewDTO {
    private int id;
    private int score;
    private String review;
    private Profile profile;
    private int experienceId;
}
