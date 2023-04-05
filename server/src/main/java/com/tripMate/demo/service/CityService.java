package com.tripMate.demo.service;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {

    List<CityDTO> getAll();
    CityDTO getById(int id) throws ResourceNotFoundException;


    CityDTO post(City city);

    CityDTO patch(int id, City city) throws ResourceNotFoundException;

    CityDTO delete(int id) throws ResourceNotFoundException;


    List<CityDTO> getBySearch(String search);
    List<CityDTO> findByCityContaining(String city);
}
