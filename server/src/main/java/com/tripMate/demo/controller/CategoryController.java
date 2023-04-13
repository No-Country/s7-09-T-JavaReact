package com.tripMate.demo.controller;


import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins="**")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll(){
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getByID(@PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }
}
