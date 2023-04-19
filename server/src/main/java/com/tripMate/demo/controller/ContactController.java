
package com.tripMate.demo.controller;

import com.tripMate.demo.dto.ContactDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins="**")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping()
    public ResponseEntity<List<ContactDTO>> getAll() {
        try {
            List<ContactDTO> response = contactService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        ContactDTO response = contactService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

