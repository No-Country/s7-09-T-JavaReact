package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.*;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ReviewMapper;
import com.tripMate.demo.repository.ExperienceRepository;
import com.tripMate.demo.repository.ReviewRepository;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.ReviewService;
import com.tripMate.demo.service.UserService;
import com.tripMate.demo.util.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewServiceImplTest {
    @Autowired
    private ReviewService reviewService;
    @MockBean
    private ReviewRepository reviewRepository;
    @MockBean
    private ExperienceRepository experienceRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    List<User> userList;
    List<Experience> experienceList;
    List<Review> reviewList;


    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        experienceList = new ArrayList<>();
        reviewList = new ArrayList<>();

        userList.add(new User(1, "Jorge", "Perez", "jroge@email.com", "123456", RoleEnum.USER));
        userList.add(new User(2, "Robert", "Suarez", "roberto@email.com", "123456", RoleEnum.USER));
        userList.add(new User(3, "Maria", "Gomez", "maria@email.com", "123456", RoleEnum.USER));

        Category category = new Category(1, "Cultural", "icon-cultural", new HashSet<>());
        Experience experience1 = new Experience(1, "Museo de Arte", "Museo de arte de la ciudad", "",null, category, new City(1, "", "", ""), reviewList,"", 4f, 1f, 1f);
        experienceList.add(experience1);

        reviewList.add(new Review(1, 4, "Muy bueno", userList.get(0), experience1));
        reviewList.add(new Review(2, 5, "Excelente", userList.get(1), experience1));
        reviewList.add(new Review(3, 3, "Bueno", userList.get(2), experience1));
    }

    @Test
    void shouldGetAllReviewsOfAnExperience() {
        Page<Review> experiences = new PageImpl<>(reviewList);
        when(reviewRepository.findByExperienceId(eq(1), any(Pageable.class))).thenReturn(experiences);
        Page<ReviewDTO> reviews = reviewService.getAllReviewsOfAnExperience(1, 0, 10);
        assertEquals(3, reviews.getSize());
        reviews.forEach(reviewDTO -> {
            Review review = reviewList.stream().filter(r -> r.getId() == reviewDTO.getId()).findFirst().orElseGet(() -> {
                fail();
                return null;
            });
            assertEquals(review.getReview(), reviewDTO.getReview());
            assertEquals(review.getScore(), reviewDTO.getScore());
            assertEquals(review.getUser().getId(), reviewDTO.getProfile().getId());
            assertEquals(review.getUser().getName(), reviewDTO.getProfile().getName());
            assertEquals(review.getUser().getLastname(), reviewDTO.getProfile().getLastname());
            assertEquals(review.getExperience().getId(), reviewDTO.getExperienceId());

        });
    }
}