package tqs.lab3.lab3_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @AutoConfigureTestDatabase
@TestPropertySource(locations = "application-integrationtest.properties") 
class CarRestControllerIT_Real {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void createCar() {
        Car toyota = new Car("Toyota", "Corolla");
        restTemplate.postForEntity("/api/car", toyota, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMake).containsOnly("Toyota");
    }

    @Test
    public void getCars() {
        createTestCar("Toyota", "Corolla");
        createTestCar("Honda", "Civic");

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMake).containsExactly("Toyota", "Honda");
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Corolla", "Civic");
    }

    private void createTestCar(String make, String model) {
        Car car = new Car(make, model);
        repository.save(car);
    }
}

