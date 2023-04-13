package entity;

import com.tripMate.demo.entity.City;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityModelTests {

    @Test
    public void testCityConstructor() {
        City city = new City(1, "Paris", "Ile-de-France", "France");
        assertEquals(1, city.getId());
        assertEquals("Paris", city.getCity());
        assertEquals("Ile-de-France", city.getProvince());
        assertEquals("France", city.getCountry());
    }

    @Test
    public void testCitySetters() {
        City city = new City(1, "city 1", "province 2", "USA");
        city.setId(1);
        city.setCity("Paris");
        city.setProvince("Ile-de-France");
        city.setCountry("France");

        assertEquals(1, city.getId());
        assertEquals("Paris", city.getCity());
        assertEquals("Ile-de-France", city.getProvince());
        assertEquals("France", city.getCountry());
    }
}
