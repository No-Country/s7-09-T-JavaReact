package com.tripMate.demo.mapper;
import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, CategoryMapper.class, CityMapper.class,TagMapper.class, ContactMapper.class})
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);
    ExperienceDTO toExperienceDTO(Experience experience);

    Experience toExperience(ExperienceDTO DTO);

    List<ExperienceDTO> toExperiencesDTO(List<Experience> experiences);

    List<Experience> toExperiences(List<ExperienceDTO> ExperiencesDTO);

}
