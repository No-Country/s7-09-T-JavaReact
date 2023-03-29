package com.tripMate.demo.mapper;
import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.entity.City;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Mapper {

    private ModelMapper modelMapper;
    @Bean
    public ModelMapper getMapper() {
        var modelmapper = new ModelMapper();
        var config = modelmapper.getConfiguration();
        config.setPropertyCondition(Conditions.isNotNull());
        return modelmapper;

   }


}
