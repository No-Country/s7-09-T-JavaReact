package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> toCategoriesDTO(List<Category> categories);
    List<Category> toCategories(List<CategoryDTO> categories);

}
