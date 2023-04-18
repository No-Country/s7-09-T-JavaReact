package com.tripMate.demo.config;

import com.tripMate.demo.dto.UserCreateDTO;
import com.tripMate.demo.entity.*;
import com.tripMate.demo.exception.ResourceAlreadyExistsException;
import com.tripMate.demo.exception.ResourceNotFoundException;
import com.tripMate.demo.repository.*;
import com.tripMate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final UserService userService;
    private final ExperienceRepository experienceRepository;
    private final ImageRepository imageRepository;
    private final CityRepository cityRepository;
    @Value("${dataloader:false}")
    private boolean isDataAutoLoaded;

    private List<Category> categories;
    private List<City> cities;
    private List<Experience> experiences;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, TagRepository tagRepository, UserService userService, ExperienceRepository experienceRepository, ImageRepository imageRepository, CityRepository cityRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.userService = userService;
        this.experienceRepository = experienceRepository;
        this.imageRepository = imageRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isDataAutoLoaded) {
            categoryLoading();
            cityLoading();
            userLoading();
            experienceLoading();
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
        categories = new ArrayList<>();
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
        categories.add(categoryRepository.save(category1));

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
        categories.add(categoryRepository.save(category2));

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
        categories.add(categoryRepository.save(category3));
    }
    private void cityLoading() {
        cities = new ArrayList<>();
        City city1 = City.builder().city("London").country("UK").province("England").build();
        cities.add(cityRepository.save(city1));

        City city2 = City.builder().city("Paris").country("France").province("Ile-de-France").build();
        cities.add(cityRepository.save(city2));
        City city3 = City.builder().city("Berlin").country("Germany").province("Berlin").build();
        cities.add(cityRepository.save(city3));
        City city4 = City.builder().city("Rome").country("Italy").province("Lazio").build();
        cities.add(cityRepository.save(city4));
        City city5 = City.builder().city("Madrid").country("Spain").province("Madrid").build();
        cities.add(cityRepository.save(city5));
        City city6 = City.builder().city("Barcelona").country("Spain").province("Catalonia").build();
        cities.add(cityRepository.save(city6));
        City city7 = City.builder().city("Amsterdam").country("Netherlands").province("North Holland").build();
        cities.add(cityRepository.save(city7));
    }
    private void experienceLoading() {
        experiences = new ArrayList<>();
        Set<Image> images1 = new HashSet<>();
        Experience experience1 = Experience.builder()
                .category(categories.get(0))
                .longitude(40L)
                .latitude(2L)
                .city(cities.get(0))
                .title("Breakfast in London")
                .subtitle("Breakfast in London")
                .description("Breakfast in London")
                .build();
        images1.add(
                Image.builder().id(1).url("https://scontent.fros2-2.fna.fbcdn.net/v/t1.6435-9/118311723_2653864184852495_3157254426733207356_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=730e14&_nc_ohc=Wt3HBrr94z4AX9lsDjQ&_nc_ht=scontent.fros2-2.fna&oh=00_AfB3Xz3IjFvo4wu-Lk2gu5no9RxjZJJMonAp5VdfzgZmog&oe=645F4EED").alt("Breakfast in London").experience(experience1).build());
        images1.add(
                Image.builder().id(2).url("https://grownuptravelguide.com/wp-content/gallery/london-breakfasts/c-craig-groble-via-flickr.jpg").alt("Breakfast in London").experience(experience1).build()
        );
        images1.add(
                Image.builder().id(3).url("https://scontent.fros2-2.fna.fbcdn.net/v/t1.6435-9/118311723_2653864184852495_3157254426733207356_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=730e14&_nc_ohc=Wt3HBrr94z4AX9lsDjQ&_nc_ht=scontent.fros2-2.fna&oh=00_AfB3Xz3IjFvo4wu-Lk2gu5no9RxjZJJMonAp5VdfzgZmog&oe=645F4EED").alt("Breakfast in London").experience(experience1).build());

        experiences.add(experienceRepository.save(experience1));
        imageRepository.saveAll(images1);


        Set<Image> images2 = new HashSet<>();
        Experience experience2 = Experience.builder()
                .category(categories.get(1))
                .longitude(40L)
                .latitude(2L)
                .city(cities.get(0))
                .title("Bus in London")
                .subtitle("Bus in London")
                .description("Bus in London")
                .build();
        images2.add(
                Image.builder().id(4).url("https://upload.wikimedia.org/wikipedia/commons/f/f4/LTZ1683_159_1.jpg").alt("Bus in London").experience(experience2).build());
        images2.add(
                Image.builder().id(5).url("https://www.visitbritainshop.com/sites/default/files/styles/twitter_card_image/public/2021-06/london-sightseeing-bus_0.jpg?h=206619ce&itok=bcArEcZH").alt("Bus in London").experience(experience2).build()
        );
        images2.add(
                Image.builder().id(6).url("https://upload.wikimedia.org/wikipedia/commons/f/f4/LTZ1683_159_1.jpg").alt("Bus in London").experience(experience2).build());

        experiences.add(experienceRepository.save(experience2));
        imageRepository.saveAll(images2);

        Set<Image> images3 = new HashSet<>();
        Experience experience3 = Experience.builder()
                .category(categories.get(2))
                .longitude(40L)
                .latitude(2L)
                .city(cities.get(0))
                .title("Hotel in London")
                .subtitle("Hotel in London")
                .description("Hotel in London")
                .build();

        images3.add(
                Image.builder().id(7).url("https://media-cdn.tripadvisor.com/media/photo-s/25/3c/3f/c9/exterior-building.jpg").alt("Hotel in London").experience(experience3).build());
        images3.add(
                Image.builder().id(8).url("https://assets3.thrillist.com/v1/image/969896/828x610/flatten;crop;webp=auto;jpeg_quality=60.jpg").alt("Hotel in London").experience(experience3).build()
        );
        images3.add(
                Image.builder().id(9).url("https://cf.bstatic.com/xdata/images/hotel/max1280x900/137951151.jpg?k=5cc0167f5d536000bf1d7428c2c7fd1f2f7e36836bb55d4e6fe73d524c9d303f&o=&hp=1").experience(experience3).build());

        experiences.add(experienceRepository.save(experience3));
        imageRepository.saveAll(images3);


    }



}
