package com.tripMate.demo.dto;

import com.tripMate.demo.dto.ReviewDTO;
import lombok.*;
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