package com.tripMate.demo.controller;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.entity.Experience;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins="**")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping()
    public ResponseEntity<List<ExperienceDTO>> getAll() {
        try {
            List<ExperienceDTO> response = experienceService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        ExperienceDTO response = experienceService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ExperienceDTO> post(@RequestBody Experience experience) {
        ExperienceDTO createdExperience = experienceService.post(experience);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExperience);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExperienceDTO> patch(@PathVariable int id, @RequestBody Experience experience) throws ResourceNotFoundException {
        ExperienceDTO updatedExperience = experienceService.patch(id, experience);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws ResourceNotFoundException {
        experienceService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Experience deleted");
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<List<ExperienceDTO>> getByCity(@PathVariable int id) throws ResourceNotFoundException {
        try {
            List<ExperienceDTO> response = experienceService.getByCity(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<ExperienceDTO>> getByCategory(@PathVariable int id) throws ResourceNotFoundException {
        try {
            List<ExperienceDTO> response = experienceService.getByCategory(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/title")
    public ResponseEntity<List<ExperienceDTO>> findByTitleContaining(@RequestParam String title) {
        try {
            List<ExperienceDTO> response = experienceService.findByTitleContaining(title);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<List<ExperienceDTO>> findByLatitudeLongitudeAndDistance(@RequestParam float latitude, @RequestParam float longitude, @RequestParam float distance) {
        try {
            List<ExperienceDTO> response = experienceService.findByLatitudeLongitudeAndDistance(latitude, longitude, distance);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/find/category")
    public ResponseEntity<List<ExperienceDTO>> findByLatitudeLongitudeDistanceAndCategory(@RequestParam float latitude, @RequestParam float longitude, @RequestParam float distance, @RequestParam int categoryId) {
        try {
            List<ExperienceDTO> response = experienceService.findByLatitudeLongitudeDistanceAndCategory(latitude, longitude, distance, categoryId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/find/city")
    public ResponseEntity<List<ExperienceDTO>> findByCategoryIdAndCityId(@RequestParam int categoryId, @RequestParam int cityId) {
        try {
            List<ExperienceDTO> response = experienceService.findByCategoryIdAndCityId(categoryId, cityId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

