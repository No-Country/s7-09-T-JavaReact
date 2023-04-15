package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ExperienceMapper;
import com.tripMate.demo.mapper.ReviewMapper;
import com.tripMate.demo.mapper.UserMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExperienceMapper expMapper;

    private Map<Long, List<Review>> cachedReviewsByExperienceId = new HashMap<>();



    /*
    @Override
    public Page<ReviewDTO> getAllReviewsOfAnExperience(int experienceId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return  reviewMapper.toListDto(reviewRepository.findeByExperienceId(experienceId, pageable));
    }*/



    @Override
    public ReviewDTO getReviewByExperienceAndEmail(int experienceId, String email) {
        return reviewMapper.toReviewDto(reviewRepository.findByExperienceIdAndUserEmail(experienceId, email));
    }

    @Override
    public ReviewDTO createReview(ReviewCreateDTO reviewDto, int experiencieId, String email) throws ResourceNotFoundException {

        Experience exp = getExperience(experiencieId);
        User user = getUser(email);

        Review review = new Review().builder()
                .review(reviewDto.getReview())
                .score(reviewDto.getScore())
                .experience(exp)
                .user(user)
                .build();

        return reviewMapper.toReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(ReviewCreateDTO review, String email, int experienceId) {

        return null;
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
