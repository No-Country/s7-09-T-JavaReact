package com.tripMate.demo.service.impl;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.entity.Category;
import com.tripMate.demo.entity.Tag;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryService;

    List<Category> categories;

    @BeforeEach
    public void setUp() {
        Set<Tag> tags1 = Set.of(
                Tag.builder().id(1).title("tag1").icon("tagIcon1").build(),
                Tag.builder().id(2).title("tag2").icon("tagIcon2").build()
        );
        Set<Tag> tags2 = Set.of(
                Tag.builder().id(3).title("tag3").icon("tagIcon3").build(),
                Tag.builder().id(4).title("tag4").icon("tagIcon4").build()
        );
        Set<Tag> tags3 = Set.of(
                Tag.builder().id(5).title("tag5").icon("tagIcon5").build(),
                Tag.builder().id(6).title("tag6").icon("tagIcon6").build(),
                Tag.builder().id(7).title("tag7").icon("tagIcon7").build()
        );
       categories = List.of(
               Category.builder().id(1).title("category1").icon("icon1").tags(tags1).build(),
               Category.builder().id(2).title("category2").icon("icon2").tags(tags2).build(),
               Category.builder().id(3).title("category3").icon("icon3").tags(tags3).build()
       );
    }

    @Test
    public void shouldGetAllCategories() {

        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDTOS = categoryService.getAll();

        categoryDTOS.forEach(categoryDTO -> {
            Category category = categories.stream()
                    .filter(c -> c.getId() == categoryDTO.getId())
                    .findFirst()
                    .orElse(null);
            assertNotNull(category);
            assertEquals(category.getTitle(), categoryDTO.getTitle());
            assertEquals(category.getIcon(), categoryDTO.getIcon());
            assertEquals(category.getTags().size(), categoryDTO.getTags().size());
        });

    }

    @Test
    public void shouldGetCategoryById() {

        //Happy path
        Category category = categories.get(0);
        when(categoryRepository.findById(category.getId())).thenReturn(java.util.Optional.of(category));
        CategoryDTO categoryDTO = null;
        try {
            categoryDTO = categoryService.getById(category.getId());
        } catch (ResourceNotFoundException e) {
            fail();
        }
        assertNotNull(categoryDTO);
        assertEquals(category.getTitle(), categoryDTO.getTitle());
        assertEquals(category.getIcon(), categoryDTO.getIcon());
        assertEquals(category.getTags().size(), categoryDTO.getTags().size());

        //Sad path
        when(categoryRepository.findById(100)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> categoryService.getById(100));

    }




}