package com.tripMate.demo.dto;

import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    private String city;

    private String province;
    private String country;

}
