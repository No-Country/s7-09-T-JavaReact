package com.tripMate.demo.service.impl;
import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.CityMapper;
import com.tripMate.demo.repository.CityRepository;
import com.tripMate.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;


    @Override
    public List<CityDTO> getAll() {
        return cityMapper.toCitiesDTO(cityRepository.findAll());
    }
    @Override
    public CityDTO getById(int id) throws ResourceNotFoundException {
        City city =  cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id "+id+ " not found"));
        return cityMapper.toCityDTO(city);
    }
/*
    @Override
    public ResponseEntity<?> postCity(City city) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cityRepository.save(city));
    }

/*    @Override
    public ResponseEntity<?> patchCity(int id, City city) {
         City cityToUpdate = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
       cityToUpdate.setCity(city.getCity());
        cityToUpdate.setProvince(city.getProvince());
        cityToUpdate.setCountry(city.getCountry());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cityRepository.save(cityToUpdate));

    }
*/
/*
        @Override
    public ResponseEntity<?> deleteCity(int id) {
        City cityToDelete = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        cityRepository.delete(cityToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("City deleted");
    }
*/
/*    @Override
    public ResponseEntity<?> getCityBySearch(String search) {
        List<City> cities = cityRepository.findAllByCityOrProvinceOrCountryContaining(search);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cities);
    }

    @Override
    public ResponseEntity<?> findByCityContaining(String city) {
        List<City> cities = cityRepository.findByCityContaining(city);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cities);
    }

*/
}

/*

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.patchCity(id, city);
    }

    @PostMapping("/")
    public ResponseEntity<?> postCity(@RequestBody City city) {
        return cityService.postCity(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }

    @GetMapping("/search{search}")
    public ResponseEntity<?> getCityBySearch(@RequestParam String search) {
        return cityService.getCityBySearch(search);
    }
    @GetMapping("/name{city}")
    public ResponseEntity<?> getCityByCity(@PathVariable String city) {
        return cityService.findByCityContaining(city);
    }
*/
