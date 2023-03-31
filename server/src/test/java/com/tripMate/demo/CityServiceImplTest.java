package com.tripMate.demo;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.repository.CityRepository;
import com.tripMate.demo.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CityServiceImplTest {

    @MockBean
    private CityRepository cityRepository;
    @Autowired
    private CityServiceImpl cityService;

    List<City> cities;

    @BeforeEach
    public void setUp() {
       cities = List.of(

            City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build(),
                City.builder().id(2).city("Resistencia").province("Chaco").country("Argentina").build(),
                City.builder().id(3).city("La Plata").province("Bs As").country("Arg").build());
    }

    @Test
    public void shouldGetAllCities() {

        when(cityRepository.findAll()).thenReturn(cities);
        List<CityDTO> cityDTOS = cityService.getAll();

        cityDTOS.forEach(cityDTO -> {
            City city = cities.stream()
                    .filter(c -> c.getId() == cityDTO.getId())
                    .findFirst()
                    .orElse(null);
            assertNotNull(city);
            assertEquals(city.getCity(), cityDTO.getCity());
            assertEquals(city.getProvince(), cityDTO.getProvince());
            assertEquals(city.getCountry(), cityDTO.getCountry());
        });

    }

    @Test
    public void shouldGetCityById() {

        //Happy path
        City city = cities.get(0);
        when(cityRepository.findById(city.getId())).thenReturn(java.util.Optional.of(city));
        CityDTO cityDTO = null;
        try {
            cityDTO = cityService.getById(city.getId());
        } catch (ResourceNotFoundException e) {
            fail();
        }
        assertNotNull(cityDTO);
        assertEquals(city.getCity(), cityDTO.getCity());
        assertEquals(city.getProvince(), cityDTO.getProvince());
        assertEquals(city.getCountry(), cityDTO.getCountry());

        //Sad path
        when(cityRepository.findById(100)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> cityService.getById(100));

    }




}