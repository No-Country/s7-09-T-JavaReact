package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ExperienceMapper;
import com.tripMate.demo.repository.ExperienceRepository;
import com.tripMate.demo.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    public List<ExperienceDTO> getAll() {
        return experienceMapper.toExperiencesDTO(experienceRepository.findAll());
    }

    @Override
    public ExperienceDTO getById(int id) throws ResourceNotFoundException {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("experience with id " + id + " not found"));
        return experienceMapper.toExperienceDTO(experience);
    }

    @Override
    public ExperienceDTO post(Experience experience) {

        Experience savedExperience = experienceRepository.save(experience);
        return experienceMapper.toExperienceDTO(savedExperience);
    }

    @Override
    public ExperienceDTO patch(int id, Experience experience) throws ResourceNotFoundException {
        Experience existingExperience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experience with id " + id + " not found"));
        if (experience.getTitle() != null) {
            existingExperience.setTitle(experience.getTitle());
        }
        if (experience.getSubtitle() != null) {
            existingExperience.setSubtitle(experience.getSubtitle());
        }
        existingExperience.setDescription(experience.getDescription());
        existingExperience.setAverageScore(experience.getAverageScore());
        existingExperience.setLatitude(experience.getLatitude());
        existingExperience.setLongitude(experience.getLongitude());
        existingExperience.setPrice(experience.getPrice());
        existingExperience.setAddress(experience.getAddress());
        existingExperience.setImages(experience.getImages());
        if (experience.getCity() != null) {
            existingExperience.setCity(experience.getCity());
        }
        if (experience.getCategory() != null) {
            existingExperience.setCategory(experience.getCategory());
        }
        if (experience.getContact() != null) {
            existingExperience.setContact(experience.getContact());
        }
        Experience updatedExperience = experienceRepository.save(existingExperience);
        return experienceMapper.toExperienceDTO(updatedExperience);
    }

    @Override
    public ExperienceDTO delete(int id) throws ResourceNotFoundException {
        Experience experienceToDelete = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experience with id " + id + " not found"));
        experienceRepository.delete(experienceToDelete);
        return experienceMapper.toExperienceDTO(experienceToDelete);
    }

    @Override
    public List<ExperienceDTO> getByCategory(int id) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByCategory_id(id));
    }

    @Override
    public List<ExperienceDTO> getByCity(int id) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByCity_id(id));
    }

    @Override
    public List<ExperienceDTO> findByTitleContaining(String title) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByTitleContaining(title));
    }

    @Override
    public List<ExperienceDTO> findByLatitudeLongitudeAndDistance(float latitude, float longitude, float distance) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByLatitudeLongitudeAndDistance(latitude, longitude, distance));
    }

    @Override
    public List<ExperienceDTO> findByLatitudeLongitudeDistanceAndCategory(float latitude, float longitude, float distance, int categoryId) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByLatitudeLongitudeDistanceAndCategory(latitude, longitude, distance, categoryId));
    }

    @Override
    public List<ExperienceDTO> findByCategoryIdAndCityId(int categoryId, int cityId) {
        return experienceMapper.toExperiencesDTO(experienceRepository.findByCategoryIdAndCityId(categoryId, cityId));
    }
}
