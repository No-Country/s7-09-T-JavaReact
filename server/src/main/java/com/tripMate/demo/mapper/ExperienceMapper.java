package com.tripMate.demo.mapper;
import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, CategoryMapper.class, CityMapper.class,TagMapper.class, ContactMapper.class})
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);
    @Mapping(target = "averageScore", expression = "java(" +
            "experience.getTotalReviews() > 0 ? experience.getTotalScore()/experience.getTotalReviews(): 0)")
    ExperienceDTO toExperienceDTO(Experience experience);

    @Mapping(target = "totalScore", ignore = true)
    @Mapping(target = "totalReviews", ignore = true)
    Experience toExperience(ExperienceDTO DTO);

    List<ExperienceDTO> toExperiencesDTO(List<Experience> experiences);

    List<Experience> toExperiences(List<ExperienceDTO> ExperiencesDTO);

}
