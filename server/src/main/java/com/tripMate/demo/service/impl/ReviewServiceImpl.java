package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.dto.ReviewResponseDTO;
import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.entity.Review;
import com.tripMate.demo.entity.User;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ExperienceMapper;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

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
    public ReviewResponseDTO getAllReviewsPage(int experienceId, int page, int size, String email) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        ReviewDTO userReview = getReviewByExperienceAndEmail(experienceId, email);

        Page<ReviewDTO> pageReviews  =  reviewMapper.toDTOPage(reviewRepository.findByExperienceIdOrderByIdDesc(experienceId, pageable));
        new ReviewResponseDTO();
        return ReviewResponseDTO.builder()
                .reviews(pageReviews)
                .userReview(userReview)
                .build();
    }



    @Override
    public ReviewDTO getReviewByExperienceAndEmail(int experienceId, String email) {
        Review userReview = reviewRepository.findByExperienceIdAndUserEmail(experienceId, email);
        userReview.setDate(calculateDaysFromTheReviewWasDone.apply(userReview.getDate()));
        return reviewMapper.toReviewDto(userReview);
    }

    @Override
    public ReviewDTO createReview(ReviewCreateDTO reviewDto, int experiencieId, String email) throws ResourceNotFoundException {

        Experience exp = getExperience(experiencieId);
        User user = getUser(email);

        new Review();
        Review review = Review.builder()
                .review(reviewDto.getReview())
                .score(reviewDto.getScore())
                .experience(exp)
                .user(user)
                .build();

        return reviewMapper.toReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(ReviewCreateDTO review, int experienceId, String email) throws ResourceNotFoundException {

        Review updateReview = reviewRepository.findByExperienceIdAndUserEmail(experienceId, email);
        updateReview.setReview(review.getReview());
        updateReview.setScore(review.getScore());
        return reviewMapper.toReviewDto(reviewRepository.save(updateReview));

    }

    @Override
    public void deleteUserReview(int experienceId, String email) throws ResourceNotFoundException {

    }

    @Override
    public Boolean hasTheAlreadyReviewed(int experienceId, String email) {
        return reviewRepository.existsByExperienceIdAndUserEmail(experienceId, email);
    }

    private Experience getExperience(int experienceId) throws ResourceNotFoundException {
        return experienceRepository.findById(experienceId)
                .orElseThrow(()-> new ResourceNotFoundException("experience not found"));

    }

    private User getUser(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));

    }

    private static Function<String, String> calculateDaysFromTheReviewWasDone = date -> {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate formattedDate = LocalDate.parse(date, pattern);
        long noOfDaysBetween = ChronoUnit.DAYS.between(formattedDate, today);

        return (noOfDaysBetween == 0) ? "Hoy"
                : (noOfDaysBetween <= 30) ? "Hace " + noOfDaysBetween + " días"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 1) ? "Hace 1 mes"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 2) ? "Hace 1 mes"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 3) ? "Hace 2 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 4) ? "Hace 3 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 5) ? "Hace 4 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 6) ? "Hace 5 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 7) ? "Hace 6 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 8) ? "Hace 7 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 9) ? "Hace 8 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 10) ? "Hace 9 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 11) ? "Hace 10 meses"
                : (noOfDaysBetween <= Math.ceil(365 / 12) * 12) ? "Hace 11 meses"
                : (noOfDaysBetween <= 365) ? "Hace 1 año"
                : (noOfDaysBetween <= 365 * 2) ? "Hace 1 año"
                : (noOfDaysBetween <= 365 * 3) ? "Hace 2 años"
                : (noOfDaysBetween <= 365 * 4) ? "Hace 3 años"
                : (noOfDaysBetween <= 365 * 5) ? "Hace 4 años"
                : (noOfDaysBetween <= 365 * 6) ? "Hace 5 años"
                : "Hace mas de 5 años";
    };
}
