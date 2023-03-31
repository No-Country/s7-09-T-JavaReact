package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper( CityMapper.class );

    CityDTO toCityDTO(City City);
    City toCity(CityDTO DTO);
    List<CityDTO> toCitiesDTO(List<City> cities);
    List<City> toCities(List<CityDTO> citiesDTO);

}