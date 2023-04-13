package com.tripMate.demo.config;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.entity.Category;
import com.tripMate.demo.entity.City;
import com.tripMate.demo.entity.Tag;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.repository.CategoryRepository;
import com.tripMate.demo.repository.CityRepository;
import com.tripMate.demo.repository.TagRepository;
import com.tripMate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final UserService userService;
    private final CityRepository cityRepository;
    @Value("${dataloader:false}")
    private boolean isDataAutoLoaded;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, TagRepository tagRepository, UserService userService, CityRepository cityRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.userService = userService;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isDataAutoLoaded) {
            categoryLoading();
            cityLoading();
            userLoading();
        }
    }

    private void userLoading() throws ResourceNotFoundException {
        UserCreateDTO user1 = UserCreateDTO.builder()
                .email("user@user.com")
                .name("Jorge")
                .lastname("User")
                .password("12345")
                .build();
        UserCreateDTO user2 = UserCreateDTO.builder()
                .email("admin@admin.com")
                .name("Jorge")
                .lastname("Admin")
                .password("12345")
                .build();

        try {
            userService.createUser(user1, "USER");
            userService.createUser(user2, "ADMIN");
        } catch (ResourceAlreadyExistsException e) {
            System.out.println("User already exists");
        }

    }

    private void categoryLoading() {
        Set<Tag> foodTags = Set.of(
                Tag.builder().title("Breakfast").icon("fas fa-utensils").build(),
                Tag.builder().title("Lunch").icon("fas fa-utensils").build(),
                Tag.builder().title("Dinner").icon("fas fa-utensils").build(),
                Tag.builder().title("Snack").icon("fas fa-utensils").build()
        );
        Category category1 = Category.builder()
                .title("Food")
                .icon("fas fa-utensils")
                .tags(foodTags)
                .build();
        tagRepository.saveAll(foodTags);
        categoryRepository.save(category1);

        Set<Tag> transportTags = Set.of(
                Tag.builder().title("Bus").icon("fas fa-bus").build(),
                Tag.builder().title("Train").icon("fas fa-train").build(),
                Tag.builder().title("Plane").icon("fas fa-plane").build(),
                Tag.builder().title("Car").icon("fas fa-car").build()
        );
        Category category2 = Category.builder()
                .title("Transport")
                .icon("fas fa-bus")
                .tags(transportTags)
                .build();
        tagRepository.saveAll(transportTags);
        categoryRepository.save(category2);

        Set<Tag> accommodationTags = Set.of(
                Tag.builder().title("Hotel").icon("fas fa-hotel").build(),
                Tag.builder().title("Hostel").icon("fas fa-bed").build(),
                Tag.builder().title("Airbnb").icon("fas fa-home").build(),
                Tag.builder().title("Camping").icon("fas fa-campground").build()
        );
        Category category3 = Category.builder()
                .title("Accommodation")
                .icon("fas fa-hotel")
                .tags(accommodationTags)
                .build();
        tagRepository.saveAll(accommodationTags);
        categoryRepository.save(category3);
    }
    private void cityLoading() {
        City city1 = City.builder().city("London").country("UK").province("England").build();
        cityRepository.save(city1);

        City city2 = City.builder().city("Paris").country("France").province("Ile-de-France").build();
        cityRepository.save(city2);

        City city3 = City.builder().city("Berlin").country("Germany").province("Berlin").build();
        cityRepository.save(city3);

        City city4 = City.builder().city("Rome").country("Italy").province("Lazio").build();
        cityRepository.save(city4);

        City city5 = City.builder().city("Madrid").country("Spain").province("Madrid").build();
        cityRepository.save(city5);

        City city6 = City.builder().city("Barcelona").country("Spain").province("Catalonia").build();
        cityRepository.save(city6);

        City city7 = City.builder().city("Amsterdam").country("Netherlands").province("North Holland").build();
        cityRepository.save(city7);
    }



}
