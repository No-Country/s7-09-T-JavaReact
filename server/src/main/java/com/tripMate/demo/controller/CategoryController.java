package com.tripMate.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @GetMapping("/hola")
    public ResponseEntity<String> ggg(){
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }
}
