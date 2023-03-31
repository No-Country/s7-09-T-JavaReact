package com.tripMate.demo.service;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
    CategoryDTO getById(int id) throws ResourceNotFoundException;

}
