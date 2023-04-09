package com.tripMate.demo;

import com.tripMate.demo.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CityModelTests {

    @Test
    void crearCityTest(){

        City testCity = City.builder()
                .city("Rosario")
                .country("Argentina")
                .province("Santa Fe")
                .id(1)
                .build();
        assertTrue(testCity.getId()==1);
        assertTrue(testCity.getCity().equals("Rosario"));
        assertTrue(testCity.getProvince().equals("Santa Fe"));
        assertTrue(testCity.getCountry().equals("Argentina"));
    }
}
