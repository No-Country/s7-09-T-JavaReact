package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.CategoryDTO;
import com.tripMate.demo.dto.CityDTO;
import com.tripMate.demo.dto.ExperienceDTO;
import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.entity.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExperienceMapperTest {

//    private final ImageMapper imageMapper = mock(ImageMapper.class);
//    private final ExperienceMapper mapper = Mappers.getMapper(ExperienceMapper.class);
//    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);
//
//    @Test
//    void toExperienceDTO() {
//
//        City city = City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
//        //when
//
//        CityDTO cityDTO = cityMapper.toCityDTO(city);
//
//        Set<Tag> tags = new HashSet<>();
//        tags.add(Tag.builder().id(1).title("tag1").icon("icon1").build());
//        tags.add(Tag.builder().id(2).title("tag2").icon("icon2").build());
//        Category category = Category.builder().id(1).title("name").icon("icon3").tags(tags).build();
//
//        Set<Image> images = new HashSet<>();
//        images.add(Image.builder().id(1).url ("http://example.com/image.jpg").alt("image 1").build());
//        images.add(Image.builder().id(2).url ("http://example.com/image2.jpg").alt("image 2").build());
//        Experience experience = Experience.builder().id(1)
//                .title("title1")
//                .subtitle("subtitle")
//                .description("description1")
//                .latitude(50L)
//                .city(city)
//                .category(category)
//                .longitude(40L)
//                .averageScore(2L)
//                .images(images).build();
//        //when
//        ExperienceDTO experienceDTO = mapper.toExperienceDTO(experience);
//
//
//        //then
//        assertEquals(experience.getId(), experienceDTO.getId());
//        assertEquals(experience.getTitle(), experienceDTO.getTitle());
//        assertEquals(experience.getSubtitle(), experienceDTO.getSubtitle());
//        assertEquals(experience.getDescription(), experienceDTO.getDescription());
//        assertEquals(experience.getAverageScore(), experienceDTO.getAverageScore());
//        assertEquals(experience.getLongitude(), experienceDTO.getLongitude());
//        assertEquals(experience.getLatitude(), experienceDTO.getLatitude());
//        assertEquals(experience.getImages().size(), experienceDTO.getImages().size());
//
//        experience.getImages().forEach(img -> {
//            assertTrue(experienceDTO.getImages().stream().anyMatch(imgDTO -> imgDTO.getId() == img.getId()));
//        });
//
//    }
//
///*    @Test
//    void toExperience() {
//        ImageDTO imageDTO = new ImageDTO("http://example.com/image.jpg");
//        when(imageMapper.toImage(imageDTO)).thenReturn(new Image());
//
//        ExperienceDTO dto = new ExperienceDTO();
//        dto.setId(1);
//        dto.setTitle("Test Experience");
//        dto.setSubtitle("Test Subtitle");
//        dto.setDescription("Test Description");
//        dto.setImages(Collections.singletonList(imageDTO));
//
//        Experience experience = mapper.toExperience(dto);
//
//        assertEquals(1, experience.getId());
//        assertEquals("Test Experience", experience.getTitle());
//        assertEquals("Test Subtitle", experience.getSubtitle());
//        assertEquals("Test Description", experience.getDescription());
//        assertEquals(Collections.singleton(new Image()), experience.getImages());
//    }
//*/
//    // add more tests for the other mapper methods
}
