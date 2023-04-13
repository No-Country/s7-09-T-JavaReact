package com.tripMate.demo.service.impl;
import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.entity.Image;
import com.tripMate.demo.entity.Image;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.ImageMapper;
import com.tripMate.demo.repository.ImageRepository;
import com.tripMate.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;


    @Override
    public List<ImageDTO> getAll() {
        return imageMapper.toImagesDTO(imageRepository.findAll());
    }

    @Override
    public ImageDTO getById(int id) throws ResourceNotFoundException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id " + id + " not found"));
        return imageMapper.toImageDTO(image);
    }

    @Override
    public ImageDTO post(Image image) {
        Image savedImage = imageRepository.save(image);
        return imageMapper.toImageDTO(savedImage);
    }

    @Override
    public ImageDTO patch(int id, Image image) throws ResourceNotFoundException {
        Image existingImage = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id " + id + " not found"));
        if (image.getUrl() != null) {
            existingImage.setUrl(image.getUrl());
        }
        if (image.getAlt() != null) {
            existingImage.setAlt(image.getAlt());
        }
        Image updatedImage = imageRepository.save(existingImage);
        return imageMapper.toImageDTO(updatedImage);
    }

    @Override
    public ImageDTO delete(int id) throws ResourceNotFoundException {
        Image imageToDelete = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id " + id + " not found"));
        imageRepository.delete(imageToDelete);
        return imageMapper.toImageDTO(imageToDelete);
    }

}