package com.tripMate.demo.service;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Page<ReviewDTO> getAllReviews(int ExperienceId, int page, int size);
    ReviewDTO getReviewByExperienceAndEmail(int ExperienceId, String email);
    ReviewDTO createReview(ReviewCreateDTO review, int experiencieId, String email);
    ReviewDTO updateReview (ReviewCreateDTO review, String email, int experienceId);


}
