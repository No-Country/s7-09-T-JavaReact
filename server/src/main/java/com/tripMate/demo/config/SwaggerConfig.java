package com.tripMate.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    /*
    private final String controllersBasePackage = "com.tripMate.demo.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllersBasePackage))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "tripmate API",
                "Version 1",
                "1.0",
                "Terms of service",
                new Contact("", "", ""),
                "", "", Collections.emptyList());
    }*/

}
