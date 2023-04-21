package com.tripMate.demo.service;

import com.tripMate.demo.dto.ContactDTO;
import com.tripMate.demo.entity.Contact;
import com.tripMate.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAll();
    ContactDTO getById(int id) throws ResourceNotFoundException;
    ContactDTO post(Contact contact);

    ContactDTO patch(int id, Contact constact) throws ResourceNotFoundException;

    ContactDTO delete(int id) throws ResourceNotFoundException;


}
