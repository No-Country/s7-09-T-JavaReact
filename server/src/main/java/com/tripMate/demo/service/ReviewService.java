package com.tripMate.demo.service;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    Page<ReviewDTO> getAllReviewsOfAnExperience(int experienceId,int page, int size);
    ReviewDTO getReviewByExperienceAndEmail(int experienceId, String email);
    ReviewDTO createReview(ReviewCreateDTO review, int experiencieId, String email) throws ResourceNotFoundException;
    ReviewDTO updateReview (ReviewCreateDTO review, String email, int experienceId);


}
