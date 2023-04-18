package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.*;
import com.tripMate.demo.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExperienceMapperTest {

    private final ImageMapper imageMapper;
    private final ExperienceMapper mapper;
    private final CityMapper cityMapper;

    @Autowired
    public ExperienceMapperTest(ImageMapper imageMapper, ExperienceMapper mapper, CityMapper cityMapper) {
        this.imageMapper = imageMapper;
        this.mapper = mapper;
        this.cityMapper = cityMapper;
    }

    @Test
    void toExperienceDTO() {

        City city = City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
        //when

        CityDTO cityDTO = cityMapper.toCityDTO(city);

        Set<Tag> tags = new HashSet<>();
        tags.add(Tag.builder().id(1).title("tag1").icon("icon1").build());
        tags.add(Tag.builder().id(2).title("tag2").icon("icon2").build());
        Category category = Category.builder().id(1).title("name").icon("icon3").tags(tags).build();

        Set<Image> images = new HashSet<>();

        Experience experience = Experience.builder().id(1)
                .title("title1")
                .subtitle("subtitle")
                .description("description1")
                .latitude(50f)
                .totalScore(20)
                .totalReviews(4)
                .city(city)
                .category(category)
                .longitude(40f)
                .images(images).build();

        images.add(Image.builder().id(1).url ("http://example.com/image.jpg").alt("image 1").experience(experience).build());
        images.add(Image.builder().id(2).url ("http://example.com/image2.jpg").alt("image 2").experience(experience).build());
        //when
        ExperienceDTO experienceDTO = mapper.toExperienceDTO(experience);


        //then
        assertEquals(experience.getId(), experienceDTO.getId());
        assertEquals(experience.getTitle(), experienceDTO.getTitle());
        assertEquals(experience.getSubtitle(), experienceDTO.getSubtitle());
        assertEquals(experience.getDescription(), experienceDTO.getDescription());
        assertEquals(experience.getLongitude(), experienceDTO.getLongitude());
        assertEquals(experience.getLatitude(), experienceDTO.getLatitude());
        assertEquals(experience.getImages().size(), experienceDTO.getImages().size());
        assertEquals(experience.getCategory().getId(), experienceDTO.getCategory().getId());
        assertEquals(experience.getCategory().getTitle(), experienceDTO.getCategory().getTitle());
        assertEquals(experience.getCategory().getIcon(), experienceDTO.getCategory().getIcon());
        assertEquals(experience.getCategory().getTags().size(), experienceDTO.getCategory().getTags().size());
        assertEquals(experience.getCity().getId(), experienceDTO.getCity().getId());
        assertEquals(experience.getCity().getCity(), experienceDTO.getCity().getCity());
        assertEquals(experience.getCity().getProvince(), experienceDTO.getCity().getProvince());
        assertEquals(experience.getCity().getCountry(), experienceDTO.getCity().getCountry());
        assertEquals(5, experienceDTO.getAverageScore());

        experience.getImages().forEach(img -> {
            assertTrue(experienceDTO.getImages().stream().anyMatch(imgDTO -> imgDTO.getId() == img.getId()));
        });

    }

    @Test
    void shouldMapWhenTotalReviewsIs0() {
        //given
        City city = City.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();

        CityDTO cityDTO = cityMapper.toCityDTO(city);

        Set<Tag> tags = new HashSet<>();
        tags.add(Tag.builder().id(1).title("tag1").icon("icon1").build());
        tags.add(Tag.builder().id(2).title("tag2").icon("icon2").build());
        Category category = Category.builder().id(1).title("name").icon("icon3").tags(tags).build();

        Set<Image> images = new HashSet<>();

        Experience experience = Experience.builder().id(1)
                .title("title1")
                .subtitle("subtitle")
                .description("description1")
                .latitude(50f)
                .totalScore(0)
                .totalReviews(0)
                .city(city)
                .category(category)
                .longitude(40f)
                .images(images).build();

        images.add(Image.builder().id(1).url ("http://example.com/image.jpg").alt("image 1").experience(experience).build());
        images.add(Image.builder().id(2).url ("http://example.com/image2.jpg").alt("image 2").experience(experience).build());
        //when
        ExperienceDTO experienceDTO = mapper.toExperienceDTO(experience);
        //then
        assertEquals(0, experienceDTO.getAverageScore());
    }

    @Test
    void toExperience() {
        //given
        CityDTO cityDTO = CityDTO.builder().id(1).city("Rosario").province("Santa Fe").country("Argentina").build();
        City city = cityMapper.toCity(cityDTO);

        Set<TagDTO> tags = new HashSet<>();
        tags.add(TagDTO.builder().id(1).title("tag1").icon("icon1").build());
        tags.add(TagDTO.builder().id(2).title("tag2").icon("icon2").build());
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1).title("name").icon("icon3").tags(tags).build();

        Set<ImageDTO> images = new HashSet<>();

        ExperienceDTO experienceDTO = ExperienceDTO.builder().id(1)
                .title("title1")
                .subtitle("subtitle")
                .description("description1")
                .latitude(50L)
                .city(cityDTO)
                .category(categoryDTO)
                .contact(new ContactDTO(1, "email", "phone", "website", "facebook", "instagram"))
                .longitude(40L)
                .averageScore(2L)
                .images(images).build();

        images.add(ImageDTO.builder().id(1).url("http://example.com/image.jpg").alt("image 1").build());
        images.add(ImageDTO.builder().id(2).url("http://example.com/image2.jpg").alt("image 2").build());

        //when
        Experience experience = mapper.toExperience(experienceDTO);

        //then
        assertEquals(experienceDTO.getId(), experience.getId());
        assertEquals(experienceDTO.getTitle(), experience.getTitle());
        assertEquals(experienceDTO.getSubtitle(), experience.getSubtitle());
        assertEquals(experienceDTO.getDescription(), experience.getDescription());
        assertEquals(experienceDTO.getLongitude(), experience.getLongitude());
        assertEquals(experienceDTO.getLatitude(), experience.getLatitude());
        assertEquals(experienceDTO.getImages().size(), experience.getImages().size());
        assertEquals(experienceDTO.getCategory().getId(), experience.getCategory().getId());
        assertEquals(experienceDTO.getCategory().getTitle(), experience.getCategory().getTitle());
        assertEquals(experienceDTO.getCategory().getIcon(), experience.getCategory().getIcon());
        assertEquals(experienceDTO.getCategory().getTags().size(), experience.getCategory().getTags().size());
        assertEquals(experienceDTO.getCity().getId(), experience.getCity().getId());
        assertEquals(experienceDTO.getCity().getCity(), experience.getCity().getCity());
        assertEquals(experienceDTO.getCity().getProvince(), experience.getCity().getProvince());
        assertEquals(experienceDTO.getCity().getCountry(), experience.getCity().getCountry());
        assertEquals(0, experience.getTotalScore());
        assertEquals(0, experience.getTotalScore());
    }



//    @Test
//    void toExperience() {
//        ImageDTO imageDTO = new ImageDTO("http://example.com/image.jpg");
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

}
