package com.tripMate.demo.controller;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "**")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/{experienceId}")
    ResponseEntity<Page<ReviewDTO>>getAllReviewsByExperience(@PathVariable int experienceId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){

        return new ResponseEntity<>(reviewService.getAllReviewsOfAnExperience(experienceId, page, size), HttpStatus.OK);

    }

    @GetMapping("/{experienceId}/{userId}")
    ResponseEntity<ReviewDTO>getAllReviewsByExperienceAndUser(@PathVariable int experienceId){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity(reviewService.getReviewByExperienceAndEmail(experienceId, userEmail), HttpStatus.OK);

    }

    @PostMapping("/{experienceId}/create")
    ResponseEntity<ReviewDTO>createReviewsOfAnExperience(@PathVariable int experienceId,
                                                         @RequestBody ReviewCreateDTO reviewCreateDTO) throws ResourceNotFoundException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity(reviewService.createReview(reviewCreateDTO, experienceId, userEmail), HttpStatus.CREATED);

    }

    @PutMapping("/{experienceId}")
    ResponseEntity<ReviewDTO>updateReview(@PathVariable int experienceId,
                                          @RequestBody ReviewCreateDTO reviewCreateDTO) throws ResourceNotFoundException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity(reviewService.updateReview(reviewCreateDTO, experienceId, userEmail), HttpStatus.NOT_MODIFIED);

    }

    @PostMapping("/{experienceId}/exists")
    ResponseEntity<Boolean>existUserReview(@PathVariable int experienceId) throws ResourceNotFoundException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity(reviewService.hasTheAlreadyReviewed(experienceId, userEmail), HttpStatus.OK);

    }




}
