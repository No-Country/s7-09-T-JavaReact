package com.tripMate.demo.repository;

import com.tripMate.demo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer > {


    @Query(value = "SELECT * FROM cities  WHERE cities.city LIKE :search OR  cities.province LIKE :search OR  cities.country LIKE :search", nativeQuery = true)
    List<City> findAllByCityOrProvinceOrCountryContaining(@Param("search") String search);

    List<City> findByCityContaining(String city);

}

