package com.tripMate.demo.controller;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping()
    public ResponseEntity<List<CityDTO>> getAllCities() {
       // ResponseEntity<List<CityDTO>> response = (ResponseEntity<List<CityDTO>>) cityService.getAllCities();
        //return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        try {
            List<CityDTO> response = cityService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable int id) throws ResourceNotFoundException {
        CityDTO response = cityService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<City>> getCityByName(@RequestParam String q) {
//       ResponseEntity<List<City>> response = (ResponseEntity<List<City>>) cityService.getCityBySearch(q);
//       //return new ResponseEntity<>(response.getBody(), response.getStatusCode());
//        if (response.getBody() != null) {
//            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<City> postCity(@RequestBody City city) {
//        ResponseEntity<?> response = cityService.postCity(city);
//        return new ResponseEntity<>(response.getStatusCode());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCityById(@PathVariable Long id) {
//        ResponseEntity<?> response = cityService.deleteCity(id);
//        return new ResponseEntity<>(response.getBody().toString(), response.getStatusCode());
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
//        ResponseEntity<?> response = cityService.patchCity(id, city);
//        return new ResponseEntity<>(response.getStatusCode());
//    }

}
