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
import java.util.stream.Collectors;

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
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
        return cityMapper.toCityDTO(city);
    }


    @Override
    public CityDTO post(City city) {
        City savedCity = cityRepository.save(city);
        return cityMapper.toCityDTO(savedCity);
    }

    @Override
    public CityDTO patch(int id, City city) throws ResourceNotFoundException {
        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
        if (city.getCity() != null) {
            existingCity.setCity(city.getCity());
        }
        if (city.getProvince() != null) {
            existingCity.setProvince(city.getProvince());
        }
        if (city.getCountry() != null) {
            existingCity.setCountry(city.getCountry());
        }
        City updatedCity = cityRepository.save(existingCity);
        return cityMapper.toCityDTO(updatedCity);
    }

    @Override
    public CityDTO delete(int id) throws ResourceNotFoundException {
        City cityToDelete = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
        cityRepository.delete(cityToDelete);
        return cityMapper.toCityDTO(cityToDelete);
    }

    @Override
    public List<CityDTO> getBySearch(String search) {
        List<City> cities = cityRepository.findAllByCityOrProvinceOrCountryContaining(search);
        return cities.stream()
                .map(city -> cityMapper.toCityDTO(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDTO> findByCityContaining(String city) {
        List<City> cities = cityRepository.findByCityContaining(city);
        return cities.stream()
                .map(c -> cityMapper.toCityDTO(c))
                .collect(Collectors.toList());
    }


}