package com.tripMate.demo.controller;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins="**")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping()
    public ResponseEntity<List<CityDTO>> getAll() {
        try {
            List<CityDTO> response = cityService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        CityDTO response = cityService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CityDTO> postCity(@RequestBody City city) {
        CityDTO createdCity = cityService.post(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CityDTO> patchCity(@PathVariable int id, @RequestBody City city) throws ResourceNotFoundException {
        CityDTO updatedCity = cityService.patch(id, city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) throws ResourceNotFoundException {
        cityService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("City deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<List<CityDTO>> getBySearch(@RequestParam String search) {
        List<CityDTO> cities = cityService.getBySearch(search);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/name")
    public ResponseEntity<List<CityDTO>> getCityByCity(@RequestParam String city) {
        List<CityDTO> cities = cityService.findByCityContaining(city);
        return ResponseEntity.ok(cities);
    }


}

