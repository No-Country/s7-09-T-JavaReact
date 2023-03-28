package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.entity.Category;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.mapper.CategoryMapper;
import com.tripMate.demo.repository.CategoryRepository;
import com.tripMate.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAll() {
         return categoryMapper.toCategoriesDTO(categoryRepository.findAll());
    }
    @Override
    public CategoryDTO getById(int id) throws ResourceNotFoundException {
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category with id "+id+ " not found"));
        return categoryMapper.toCategoryDTO(category);
    }
}
