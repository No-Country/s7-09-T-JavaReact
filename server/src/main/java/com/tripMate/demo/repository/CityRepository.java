package com.tripMate.demo.repository;

import com.tripMate.demo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM cities  WHERE city LIKE '%:search%' OR  province LIKE '%:search%' OR  country LIKE '%:search%'   ", nativeQuery = true)
    List<City> findAllByCityOrProvinceOrCountryContaining(@Param("search") String text);

}

