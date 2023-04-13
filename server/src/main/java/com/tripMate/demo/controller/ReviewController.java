package com.tripMate.demo.controller;

import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{experienceId}")
    ResponseEntity<ReviewDTO>getAllReviewsByExperience(@PathVariable int experienceId){
        return null;

    }

    /*
    @PostMapping("/{experienceId}")
    ResponseEntity<ReviewDTO>createReviewsOfAnExperience(@PathVariable int experienceId){
        return null;

    }*/



}
