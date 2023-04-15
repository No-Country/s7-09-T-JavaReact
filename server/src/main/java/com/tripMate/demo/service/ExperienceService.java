package com.tripMate.demo.service;

import com.tripMate.demo.dto.ExperienceDTO;

import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> getAll();
    ExperienceDTO getById(int id) throws ResourceNotFoundException;

    ExperienceDTO post(Experience experience);

    ExperienceDTO patch(int id, Experience experience) throws ResourceNotFoundException;

    ExperienceDTO delete(int id) throws ResourceNotFoundException;

    List<ExperienceDTO> getByCategory(int id);
    List<ExperienceDTO> getByCity(int id);
    List<ExperienceDTO> findByTitleContaining(String title);
    List<ExperienceDTO> findByLatitudeLongitudeAndDistance( float latitude,float longitude,  float distance);

}
