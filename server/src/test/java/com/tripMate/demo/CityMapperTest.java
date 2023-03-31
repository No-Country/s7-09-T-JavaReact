package com.tripMate.demo;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.mapper.CityMapper;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityMapperTest {

     CityMapper mapper = CityMapper.INSTANCE;

    @Test
    public void shouldMapCityToCityDTO() {
        //given
        City city = City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
        //when
        CityDTO cityDTO = mapper.toCityDTO(city);

        //then
        assertEquals(city.getId(), cityDTO.getId());
        assertEquals(city.getCity(), cityDTO.getCity());
        assertEquals(cityDTO.getProvince(), cityDTO.getProvince());
        assertEquals(cityDTO.getCountry(), cityDTO.getCountry());


    }

    @Test
    public void shouldMapCityDTOToCity() {
            //given


            CityDTO cityDTO = CityDTO.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();


        //when
            City city = mapper.toCity(cityDTO);
            //then
        assertEquals(cityDTO.getId(), city.getId());
        assertEquals(cityDTO.getCity(), city.getCity());
        assertEquals(cityDTO.getProvince(), city.getProvince());
        assertEquals(cityDTO.getCountry(), city.getCountry());
    }

    @Test
    public void shouldMapCitiesToCitiesDTO() {

        //given
        City city1 = City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
        City city2 = City.builder().id(2).city("Resistencia").province("Chaco").country("Argentina").build();

        List<City> cities = List.of(city1, city2);

        //when
        List<CityDTO> citiesDTO = mapper.toCitiesDTO(cities);

        //then
        assertEquals(cities.size(), citiesDTO.size());
        for (int i = 0; i < cities.size(); i++) {
            assertEquals(cities.get(i).getId(), citiesDTO.get(i).getId());
            assertEquals(cities.get(i).getCity(), citiesDTO.get(i).getCity());
            assertEquals(cities.get(i).getProvince(), citiesDTO.get(i).getProvince());
            assertEquals(cities.get(i).getCountry(), citiesDTO.get(i).getCountry());

        }

       /* assertTrue((BooleanSupplier) cities.stream()
                .filter(two -> citiesDTO.stream()
                        .anyMatch(one -> one.getCity().equals(two.getCity())
                                && one.getProvince().equals(two.getProvince())
                                && one.getCountry().equals(two.getCountry()) )));

*/
    }

    @Test
    public void shouldMapCitiesDTOToCities() {

        //given
        CityDTO cityDTO1 = CityDTO.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
        CityDTO cityDTO2 = CityDTO.builder().id(2).city("Resistencia").province("Chaco").country("Argentina").build();


        List<CityDTO> citiesDTO = List.of(cityDTO1, cityDTO2);

        //when
        List<City> cities = mapper.toCities(citiesDTO);


        //then
        assertEquals(citiesDTO.size(), cities.size());
        for (int i = 0; i < citiesDTO.size(); i++) {
            assertEquals(citiesDTO.get(i).getId(), cities.get(i).getId());
            assertEquals(citiesDTO.get(i).getCity(), cities.get(i).getCity());
            assertEquals(citiesDTO.get(i).getProvince(), cities.get(i).getProvince());
            assertEquals(citiesDTO.get(i).getCountry(), cities.get(i).getCountry());

        }

    }

}
