package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.dto.TagDTO;
import com.tripMate.demo.entity.Category;
import com.tripMate.demo.entity.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    public void shouldMapCategoryToCategoryDTO() {

        //given
        Set<Tag> tags = new HashSet<>();
        tags.add(Tag.builder().id(1).title("tag1").icon("icon1").build());
        tags.add(Tag.builder().id(2).title("tag2").icon("icon2").build());
        Category category = Category.builder().id(1).title("name").icon("icon3").tags(tags).build();

        //when
        CategoryDTO categoryDTO = mapper.toCategoryDTO(category);

        //then
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getTitle(), categoryDTO.getTitle());
        assertEquals(category.getIcon(), categoryDTO.getIcon());
        assertEquals(category.getTags().size(), categoryDTO.getTags().size());
        category.getTags().forEach(tag -> {
            assertTrue(categoryDTO.getTags().stream().anyMatch(tagDTO -> tagDTO.getId() == tag.getId()));
        });
    }

    @Test
    public void shouldMapCategoryDTOToCategory() {

            //given
            Set<TagDTO> tags = new HashSet<>();
            tags.add(TagDTO.builder().id(1).title("tag1").icon("icon1").build());
            tags.add(TagDTO.builder().id(2).title("tag2").icon("icon2").build());
            CategoryDTO categoryDTO = CategoryDTO.builder().id(1).title("name").icon("icon3").tags(tags).build();

            //when
            Category category = mapper.toCategory(categoryDTO);

            //then
            assertEquals(categoryDTO.getId(), category.getId());
            assertEquals(categoryDTO.getTitle(), category.getTitle());
            assertEquals(categoryDTO.getIcon(), category.getIcon());
            assertEquals(categoryDTO.getTags().size(), category.getTags().size());
            categoryDTO.getTags().forEach(tag -> {
                assertTrue(category.getTags().stream().anyMatch(tagDTO -> tagDTO.getId() == tag.getId()));
            });
    }

    @Test
    public void shouldMapCategoriesToCategoriesDTO() {

        //given
        Set<Tag> tags1 = new HashSet<>();
        tags1.add(Tag.builder().id(1).title("tag1").icon("icon1").build());
        tags1.add(Tag.builder().id(2).title("tag2").icon("icon2").build());
        Set<Tag> tags2 = new HashSet<>();
        tags2.add(Tag.builder().id(3).title("tag3").icon("icon3").build());
        tags2.add(Tag.builder().id(4).title("tag4").icon("icon4").build());
        Category category1 = Category.builder().id(1).title("name1").icon("icon5").tags(tags1).build();
        Category category2 = Category.builder().id(2).title("name2").icon("icon6").tags(tags2).build();
        List<Category> categories = List.of(category1, category2);

        //when
        List<CategoryDTO> categoriesDTO = mapper.toCategoriesDTO(categories);

        //then
        assertEquals(categories.size(), categoriesDTO.size());
        for (int i = 0; i < categories.size(); i++) {
            assertEquals(categories.get(i).getId(), categoriesDTO.get(i).getId());
            assertEquals(categories.get(i).getTitle(), categoriesDTO.get(i).getTitle());
            assertEquals(categories.get(i).getIcon(), categoriesDTO.get(i).getIcon());
            assertEquals(categories.get(i).getTags().size(), categoriesDTO.get(i).getTags().size());
            int id = i;
            categories.get(id).getTags().forEach(tag -> {
                assertTrue(categoriesDTO.get(id).getTags().stream().anyMatch(tagDTO -> tagDTO.getId() == tag.getId()));
            });
        }
    }

    @Test
    public void shouldMapCategoriesDTOToCategories() {

        //given
        Set<TagDTO> tags1 = new HashSet<>();
        tags1.add(TagDTO.builder().id(1).title("tag1").icon("icon1").build());
        tags1.add(TagDTO.builder().id(2).title("tag2").icon("icon2").build());
        Set<TagDTO> tags2 = new HashSet<>();
        tags2.add(TagDTO.builder().id(3).title("tag3").icon("icon3").build());
        tags2.add(TagDTO.builder().id(4).title("tag4").icon("icon4").build());
        CategoryDTO categoryDTO1 = CategoryDTO.builder().id(1).title("name1").icon("icon5").tags(tags1).build();
        CategoryDTO categoryDTO2 = CategoryDTO.builder().id(2).title("name2").icon("icon6").tags(tags2).build();
        List<CategoryDTO> categoriesDTO = List.of(categoryDTO1, categoryDTO2);

        //when
        List<Category> categories = mapper.toCategories(categoriesDTO);

        //then
        assertEquals(categoriesDTO.size(), categories.size());
        for (int i = 0; i < categoriesDTO.size(); i++) {
            assertEquals(categoriesDTO.get(i).getId(), categories.get(i).getId());
            assertEquals(categoriesDTO.get(i).getTitle(), categories.get(i).getTitle());
            assertEquals(categoriesDTO.get(i).getIcon(), categories.get(i).getIcon());
            assertEquals(categoriesDTO.get(i).getTags().size(), categories.get(i).getTags().size());
            int id = i;
            categoriesDTO.get(id).getTags().forEach(tag -> {
                assertTrue(categories.get(id).getTags().stream().anyMatch(tagDTO -> tagDTO.getId() == tag.getId()));
            });
        }
    }

}