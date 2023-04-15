package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.ReviewDTO;
import com.tripMate.demo.entity.Review;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ExperienceMapper.class})
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );

    @Mapping(target = "profile", source = "user")
    @Mapping(target = "experienceId", source = "experience.id")
    ReviewDTO toReviewDto (Review review);

    @InheritInverseConfiguration
    Review  toReview (ReviewDTO dto);

    default Page<ReviewDTO> toDTOPage(Page<Review> reviewPage) {
        return reviewPage.map(this::toReviewDto);
    }

}
