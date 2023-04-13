
package com.tripMate.demo.controller;

import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping()
    public ResponseEntity<List<ImageDTO>> getAll() {
        try {
            List<ImageDTO> response = imageService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        ImageDTO response = imageService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

