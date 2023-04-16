package com.tripMate.demo.service;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Page<ReviewDTO> getAllReviewsOfAnExperience(int experienceId,int page, int size) throws ResourceNotFoundException;
    ReviewDTO getReviewByExperienceAndEmail(int experienceId, String email);
    ReviewDTO createReview(ReviewCreateDTO review, int experiencieId, String email) throws ResourceNotFoundException;
    ReviewDTO updateReview (ReviewCreateDTO review, int experienceId, String email) throws ResourceNotFoundException;

    Boolean hasTheAlreadyReviewed (int experienceId, String email);


}
