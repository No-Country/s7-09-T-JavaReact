package com.tripMate.demo.service.impl;
import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
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
    private CityMapper mapper;

    public ResponseEntity<CityDTO> getCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toCityDTO(city));
    }


    @Override
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> citiesDTO = mapper.toCitiesDTO(cities);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(citiesDTO);

    }
//
//    @Override
//    public ResponseEntity<?> postCity(City city) {
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cityRepository.save(city));
//    }
//
//    @Override
//    public ResponseEntity<?> patchCity(Long id, City city) {
//        City cityToUpdate = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
//        cityToUpdate.setCity(city.getCity());
//        cityToUpdate.setProvince(city.getProvince());
//        cityToUpdate.setCountry(city.getCountry());
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cityRepository.save(cityToUpdate));
//    }
//
//    @Override
//    public ResponseEntity<?> deleteCity(Long id) {
//        City cityToDelete = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
//        cityRepository.delete(cityToDelete);
//        return ResponseEntity.status(HttpStatus.ACCEPTED)
//                .body("City deleted");
//    }
//
//    @Override
//    public ResponseEntity<?> getCityBySearch(String search) {
//        List<City> cities = cityRepository.findAllByCityOrProvinceOrCountryContaining (search);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cities);
//    }

}


