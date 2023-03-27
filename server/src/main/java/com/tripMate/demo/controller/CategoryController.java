package com.tripMate.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/hola")
    public String ggg(){
        return "hello";
    }
}
