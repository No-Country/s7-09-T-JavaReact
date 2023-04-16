package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.*;
import com.tripMate.demo.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReviewMapperTest {
    private final ReviewMapper mapper;
    private final UserMapper userMapper;
    private final ExperienceMapper experienceMapper;
    private final CityMapper cityMapper;
    private final CategoryMapper categoryMapper;
    List<Review> reviews = new ArrayList<>();
    List<ReviewDTO> reviewDTOS = new ArrayList<>();
    List<ReviewCreateDTO> reviewCreateDTOS = new ArrayList<>();

    @Autowired
    ReviewMapperTest(ReviewMapper mapper, UserMapper userMapper, ExperienceMapper experienceMapper, CityMapper cityMapper, CategoryMapper categoryMapper) {
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.experienceMapper = experienceMapper;
        this.cityMapper = cityMapper;
        this.categoryMapper = categoryMapper;
    }

    @BeforeEach
    void setUp() {
        City city = new City(1, "Rosario", "Santa Fe", "Argentina");
        CityDTO cityDTO = cityMapper.toCityDTO(city);
        Category category = new Category(1, "name", "icon3", null);
        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
        User user = User.builder()
                .id(1)
                .email("username")
                .password("password")
                .email("email")
                .build();
        Profile profile = userMapper.toProfile(user);
        Experience experience = Experience.builder()
                .id(1)
                .title("title1")
                .subtitle("subtitle")
                .description("description1")
                .latitude(50f)
                .city(city)
                .category(category)
                .longitude(40f)
                .averageScore(2.00f)
                .build();
        reviews.add(
                new Review(1, 5,"asdopñfgopsdg", LocalDate.of(2000,9,6), user, experience));
        reviewDTOS.add(
                new ReviewDTO(1, 5,"asdopñfgopsdg", LocalDate.of(2000,9,6), profile, experience.getId()));

    }

    @Test
    void toReviewDTO() {
        reviews.forEach(review -> {
            ReviewDTO reviewDTO = mapper.toReviewDto(review);
            assertEquals(review.getReview(), reviewDTO.getReview() );
            assertEquals(review.getScore(), reviewDTO.getScore() );
            assertEquals(review.getId(), reviewDTO.getId() );
            assertEquals(review.getUser().getId(), reviewDTO.getProfile().getId() );
            assertEquals(review.getUser().getName(), reviewDTO.getProfile().getName());
            assertEquals(review.getUser().getLastname(), reviewDTO.getProfile().getLastname());
            assertEquals(review.getExperience().getId(), reviewDTO.getExperienceId() );
            assertEquals(review.getDate(), reviewDTO.getDate() );
        });
    }

    @Test
    void toReview() {
        reviewDTOS.forEach(reviewDTO -> {
            Review review = mapper.toReview(reviewDTO);
            assertEquals(reviewDTO.getReview(), review.getReview() );
            assertEquals(reviewDTO.getScore(), review.getScore() );
            assertEquals(reviewDTO.getId(), review.getId() );
            assertEquals(reviewDTO.getProfile().getId(), review.getUser().getId() );
            assertEquals(reviewDTO.getProfile().getName(), review.getUser().getName());
            assertEquals(reviewDTO.getProfile().getLastname(), review.getUser().getLastname());
            assertEquals(reviewDTO.getExperienceId(), review.getExperience().getId() );
            assertEquals(reviewDTO.getDate(), review.getDate() );
        });
    }
}