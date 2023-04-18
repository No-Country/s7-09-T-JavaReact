package com.tripMate.demo.controller;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.dto.ReviewResponseDTO;
import com.tripMate.demo.exception.BadRequestException;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "**")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/experiences/{experienceId}/reviews")
    ResponseEntity<ReviewResponseDTO>getAllReviewsByExperience(@PathVariable int experienceId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) throws ResourceNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(
                reviewService.getAllReviewsOfAnExperience(experienceId, page, size),
                reviewService.getReviewByExperienceAndEmail(experienceId, email));
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);

    }

    @PostMapping("/experiences/{experienceId}/reviews")
    ResponseEntity<ReviewDTO>createReviewsOfAnExperience(@PathVariable int experienceId,
                                                         @RequestBody ReviewCreateDTO reviewCreateDTO) throws ResourceNotFoundException, BadRequestException, ResourceAlreadyExistsException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(reviewService.createReview(reviewCreateDTO, experienceId, userEmail), HttpStatus.CREATED);

    }

    @PutMapping("/experiences/{experienceId}/reviews")
    ResponseEntity<ReviewDTO>updateReview(@PathVariable int experienceId,
                                          @RequestBody ReviewCreateDTO reviewCreateDTO) throws ResourceNotFoundException, BadRequestException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(reviewService.updateReview(reviewCreateDTO, experienceId, userEmail), HttpStatus.OK);

    }

    @DeleteMapping("/experiences/{experienceId}/reviews")
    ResponseEntity<?> deleteUserReview(@PathVariable int experienceId) throws ResourceNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        reviewService.deleteReview(experienceId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
