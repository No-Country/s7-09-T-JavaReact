package com.tripMate.demo.service;

import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.entity.Image;
import com.tripMate.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface ImageService {

    List<ImageDTO> getAll();
    ImageDTO getById(int id) throws ResourceNotFoundException;

}
