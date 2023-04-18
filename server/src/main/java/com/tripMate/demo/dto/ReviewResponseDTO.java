package com.tripMate.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReviewResponseDTO {
    private Page<ReviewDTO> reviews;
    private ReviewDTO userReview;
}