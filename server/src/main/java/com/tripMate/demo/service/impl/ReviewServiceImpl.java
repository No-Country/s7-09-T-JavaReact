package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.BadRequestException;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ReviewMapper;
import com.tripMate.demo.repository.ExperienceRepository;
import com.tripMate.demo.repository.ReviewRepository;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewMapper reviewMapper;


    @Override
    public Page<ReviewDTO> getAllReviewsOfAnExperience(int experienceId, int page, int size) throws ResourceNotFoundException {
        getExperience(experienceId);
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return  reviewRepository.findByExperienceId(experienceId, pageable).map(reviewMapper::toReviewDto);
    }



    @Override
    public ReviewDTO getReviewByExperienceAndEmail(int experienceId, String email) {
        return reviewMapper.toReviewDto(reviewRepository.findByExperienceIdAndUserEmail(experienceId, email));
    }

    @Override
    public ReviewDTO createReview(ReviewCreateDTO reviewDto, int experiencieId, String email) throws ResourceNotFoundException, BadRequestException, ResourceAlreadyExistsException {

        if (reviewDto.getScore() < 0 || reviewDto.getScore() > 5) {
            throw new BadRequestException("Score must be between 0 and 5");
        }

        Experience experience = getExperience(experiencieId);
        User user = getUser(email);
        if(getReviewByExperienceAndEmail(experiencieId, email) != null) throw new ResourceAlreadyExistsException("You have already reviewed this experience");

        new Review();
        Review review = Review.builder()
                .review(reviewDto.getReview())
                .score(reviewDto.getScore())
                .experience(experience)
                .user(user)
                .date(LocalDate.now())
                .build();
        experience.setTotalReviews(experience.getTotalReviews() + 1);
        experience.setTotalScore(experience.getTotalScore() + review.getScore());
        experienceRepository.save(experience);
        return reviewMapper.toReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(ReviewCreateDTO review, int experienceId, String email) throws ResourceNotFoundException, BadRequestException {

        if (review.getScore() < 0 || review.getScore() > 5) {
            throw new BadRequestException("Score must be between 0 and 5");
        }

        Review updateReview = reviewRepository.findByExperienceIdAndUserEmail(experienceId, email);
        if (updateReview == null) throw new ResourceNotFoundException("Review not found");
        int previousScore = updateReview.getScore();

        updateReview.setReview(review.getReview());
        updateReview.setScore(review.getScore());
        updateReview.setDate(LocalDate.now());

        Experience experience = updateReview.getExperience();
        experience.setTotalScore(experience.getTotalScore() - previousScore + review.getScore());
        experienceRepository.save(experience);
        return reviewMapper.toReviewDto(reviewRepository.save(updateReview));

    }

    @Override
    public void deleteReview(int experienceId, String email) throws ResourceNotFoundException {
        Review review = reviewRepository.findByExperienceIdAndUserEmail(experienceId, email);
        if (review == null) throw new ResourceNotFoundException("Review not found");
        Experience experience = review.getExperience();
        experience.setTotalReviews(experience.getTotalReviews() - 1);
        experience.setTotalScore(experience.getTotalScore() - review.getScore());
        experienceRepository.save(experience);
        reviewRepository.delete(review);
    }

    private Experience getExperience(int experienceId) throws ResourceNotFoundException {
        return experienceRepository.findById(experienceId)
                .orElseThrow(()-> new ResourceNotFoundException("experience not found"));

    }

    private User getUser(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));

    }
}
