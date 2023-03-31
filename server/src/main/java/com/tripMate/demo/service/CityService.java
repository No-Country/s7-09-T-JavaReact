package com.tripMate.demo.service;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {

    List<CityDTO> getAll();
    CityDTO getById(int id) throws ResourceNotFoundException;


/*    ResponseEntity<?> postCity(City city);

    ResponseEntity<?> patchCity(int id, City city);

    ResponseEntity<?> deleteCity(int id);


    ResponseEntity<?> getCityBySearch(String search);
    ResponseEntity<?> findByCityContaining(String city);
*/}
