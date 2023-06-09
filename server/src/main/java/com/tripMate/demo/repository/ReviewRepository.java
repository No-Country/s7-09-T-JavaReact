package com.tripMate.demo.repository;

import com.tripMate.demo.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findByExperienceIdAndUserEmail(Integer experienceId, String userEmail);

    boolean existsByExperienceIdAndUserEmail(Integer experienceId, String Email);

    Page<Review> findByExperienceId(Integer experienceId, Pageable pageable);

}
