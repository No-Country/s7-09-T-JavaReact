package com.tripMate.demo.service;

import org.springframework.http.ResponseEntity;

public interface CityService {

    ResponseEntity<?> getAllCities();

    ResponseEntity<?> getCity(Long id);


//    ResponseEntity<?> postCity(City city);
//
//    ResponseEntity<?> patchCity(Long id, City city);
//
//    ResponseEntity<?> deleteCity(Long id);
//
//
//    ResponseEntity<?> getCityBySearch(String search);
}