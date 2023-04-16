package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.*;
import com.tripMate.demo.exception.BadRequestException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ReviewMapper;
import com.tripMate.demo.repository.ExperienceRepository;
import com.tripMate.demo.repository.ReviewRepository;
import com.tripMate.demo.repository.UserRepository;
import com.tripMate.demo.service.ReviewService;
import com.tripMate.demo.util.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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

        reviewList.add(new Review(1, 4, "Muy bueno", LocalDate.of(2000, 6, 9), userList.get(0), experience1));
        reviewList.add(new Review(2, 5, "Excelente", LocalDate.of(2000, 6, 9), userList.get(1), experience1));
        reviewList.add(new Review(3, 3, "Bueno", LocalDate.of(2000, 6, 9), userList.get(2), experience1));
    }

    @Test
    void shouldGetAllReviewsOfAnExperience() throws ResourceNotFoundException {
        Page<Review> experiences = new PageImpl<>(reviewList);
        when(reviewRepository.findByExperienceId(eq(1), any(Pageable.class))).thenReturn(experiences);
        when(experienceRepository.findById(1)).thenReturn(Optional.of(experienceList.get(0)));
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
            assertEquals(review.getDate(), reviewDTO.getDate());

        });
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenGetAllReviewsOfAnExperience() {
        when(reviewRepository.findByExperienceId(eq(404), any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(experienceRepository.findById(404)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> reviewService.getAllReviewsOfAnExperience(404, 0, 10));
    }


    @Test
    void shouldGetReviewByExperienceAndEmail() {
        //given
        User user = userList.get(0);
        Experience experience = experienceList.get(0);
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail()))
                .thenReturn(
                        reviewList.stream().filter(review -> review.getExperience().getId() == experience.getId() && review.getUser().getEmail().equals(user.getEmail())).findFirst().orElse(null)
                );
        //when
        ReviewDTO reviewDTO = reviewService.getReviewByExperienceAndEmail(experience.getId(), user.getEmail());
        //then
        assertNotNull(reviewDTO);
        assertEquals(reviewList.get(0).getReview(), reviewDTO.getReview());
        assertEquals(reviewList.get(0).getScore(), reviewDTO.getScore());
        assertEquals(reviewList.get(0).getUser().getId(), reviewDTO.getProfile().getId());
        assertEquals(reviewList.get(0).getUser().getName(), reviewDTO.getProfile().getName());
        assertEquals(reviewList.get(0).getUser().getLastname(), reviewDTO.getProfile().getLastname());
        assertEquals(reviewList.get(0).getExperience().getId(), reviewDTO.getExperienceId());
        assertEquals(reviewList.get(0).getDate(), reviewDTO.getDate());
    }

    @Test
    void shouldCreateReview() throws ResourceNotFoundException, BadRequestException {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        //when
        ReviewDTO reviewDTO = reviewService.createReview(reviewCreate, experience.getId(), user.getEmail());
        //then
        assertNotNull(reviewDTO);
        assertEquals(reviewCreate.getReview(), reviewDTO.getReview());
        assertEquals(reviewCreate.getScore(), reviewDTO.getScore());
        assertEquals(user.getId(), reviewDTO.getProfile().getId());
        assertEquals(user.getName(), reviewDTO.getProfile().getName());
        assertEquals(user.getLastname(), reviewDTO.getProfile().getLastname());
        assertEquals(experience.getId(), reviewDTO.getExperienceId());
        assertEquals(LocalDate.now(), reviewDTO.getDate());
    }



    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundInCreateReview() {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ResourceNotFoundException.class, () -> reviewService.createReview(reviewCreate, experience.getId(), user.getEmail()));
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenExperienceNotFoundInCreateReview() {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        //when and then
        assertThrows(ResourceNotFoundException.class, () -> reviewService.createReview(reviewCreate, experience.getId(), user.getEmail()));
    }

    @Test
    void shouldThrowExceptionWhenScoreIsHigherThan5InCreateReview() {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(6, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        //when and then
        assertThrows(BadRequestException.class, () -> reviewService.createReview(reviewCreate, experience.getId(), user.getEmail()));
    }



    @Test
    void shouldThrowExceptionWhenScoreIsLowerThan0InCreateReview() {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(-1, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        //when and then
        assertThrows(BadRequestException.class, () -> reviewService.createReview(reviewCreate, experience.getId(), user.getEmail()));
    }



    @Test
    void shouldUpdateReview() throws ResourceNotFoundException, BadRequestException {
        //given
        Review review = reviewList.get(0);
        Experience experience = review.getExperience();
        User user = review.getUser();
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            return invocation.<Review>getArgument(0);
        });
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail())).thenReturn(review);
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        //when
        ReviewDTO reviewDTO = reviewService.updateReview(reviewCreate, experience.getId(), user.getEmail());
        //then
        assertNotNull(reviewDTO);
        assertEquals(reviewCreate.getReview(), reviewDTO.getReview());
        assertEquals(reviewCreate.getScore(), reviewDTO.getScore());
        assertEquals(user.getId(), reviewDTO.getProfile().getId());
        assertEquals(user.getName(), reviewDTO.getProfile().getName());
        assertEquals(user.getLastname(), reviewDTO.getProfile().getLastname());
        assertEquals(experience.getId(), reviewDTO.getExperienceId());
        assertEquals(LocalDate.now(), reviewDTO.getDate());

    }



    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotFoundInUpdateReview() {
        //given
        Review review = reviewList.get(0);
        Experience experience = review.getExperience();
        User user = review.getUser();
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail())).thenReturn(null);
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ResourceNotFoundException.class, () -> reviewService.updateReview(reviewCreate, experience.getId(), user.getEmail()));
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenExperienceNotFoundInUpdateReview() {
        //given
        Review review = reviewList.get(0);
        Experience experience = review.getExperience();
        User user = review.getUser();
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(5, "Muy bueno");
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail())).thenReturn(null);
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        //when and then
        assertThrows(ResourceNotFoundException.class, () -> reviewService.updateReview(reviewCreate, experience.getId(), user.getEmail()));
    }

    @Test
    void shouldThrowExceptionWhenScoreIsHigherThan5InUpdateReview() throws ResourceNotFoundException {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(6, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail())).thenReturn(reviewList.get(0));
        //when and then
        assertThrows(BadRequestException.class, () -> reviewService.updateReview(reviewCreate, experience.getId(), user.getEmail()));
    }

    @Test
    void shouldThrowExceptionWhenScoreIsLowerThan0InUpdateReview() {
        //given
        Experience experience = experienceList.get(0);
        User user = userList.get(0);
        ReviewCreateDTO reviewCreate = new ReviewCreateDTO(-1, "Muy bueno");
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            review.setId(5);
            return review;
        });
        when(experienceRepository.findById(experience.getId())).thenReturn(Optional.of(experience));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(reviewRepository.findByExperienceIdAndUserEmail(experience.getId(), user.getEmail())).thenReturn(reviewList.get(0));
        //when and then
        assertThrows(BadRequestException.class, () -> reviewService.updateReview(reviewCreate, experience.getId(), user.getEmail()));
    }

}