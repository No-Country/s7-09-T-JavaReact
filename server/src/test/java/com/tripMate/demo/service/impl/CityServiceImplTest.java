package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.CityMapper;
import com.tripMate.demo.repository.CityRepository;
import com.tripMate.demo.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        City city1 = new City(1, "New York", "New York", "USA");
        City city2 = new City(2, "Los Angeles", "California", "USA");
        List<City> cities = Arrays.asList(city1, city2);
        CityDTO cityDTO1 = new CityDTO(1, "New York", "New York", "USA");
        CityDTO cityDTO2 = new CityDTO(2, "Los Angeles", "California", "USA");
        List<CityDTO> cityDTOS = Arrays.asList(cityDTO1, cityDTO2);
        when(cityRepository.findAll()).thenReturn(cities);
        when(cityMapper.toCitiesDTO(cities)).thenReturn(cityDTOS);

        List<CityDTO> result = cityService.getAll();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("New York", result.get(0).getCity());
        Assertions.assertEquals("New York", result.get(0).getProvince());
        Assertions.assertEquals("USA", result.get(0).getCountry());
        Assertions.assertEquals("Los Angeles", result.get(1).getCity());
        Assertions.assertEquals("California", result.get(1).getProvince());
        Assertions.assertEquals("USA", result.get(1).getCountry());

        verify(cityRepository, times(1)).findAll();
        verify(cityMapper, times(1)).toCitiesDTO(cities);
    }

    @Test
    void testGetById() throws ResourceNotFoundException {
        City city = new City(1, "New York", "New York", "USA");
        CityDTO cityDTO = new CityDTO(1, "New York", "New York", "USA");
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));
        when(cityMapper.toCityDTO(city)).thenReturn(cityDTO);

        CityDTO result = cityService.getById(1);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("New York", result.getCity());
        Assertions.assertEquals("New York", result.getProvince());
        Assertions.assertEquals("USA", result.getCountry());

        verify(cityRepository, times(1)).findById(1);
        verify(cityMapper, times(1)).toCityDTO(city);
    }

    @Test
    void testGetByIdThrowsResourceNotFoundException() {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> cityService.getById(1));

        verify(cityRepository, times(1)).findById(1);
        verify(cityMapper, times(0)).toCityDTO(any(City.class));
    }
}
