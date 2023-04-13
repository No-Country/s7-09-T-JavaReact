package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.ReviewCreateDTO;
import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );

    ReviewDTO toReviewDto (Review review);
    Review  toReview (ReviewDTO dto);
    List<ReviewDTO> toListDto(List<Review> reviews);
    List<ReviewDTO> toListReviews(List<ReviewDTO> reviews);


}
