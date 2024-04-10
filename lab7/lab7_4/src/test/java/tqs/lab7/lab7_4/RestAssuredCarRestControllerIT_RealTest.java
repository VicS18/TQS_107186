package tqs.lab7.lab7_4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class CarRestControllerIT_RealTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres"))
        .withDatabaseName("carDB")
        .withUsername("admin")
        .withPassword("Admin123");

    // Add Spring Boot environment properties from container DB
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", container::getJdbcUrl);
      registry.add("spring.datasource.password", container::getPassword);
      registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private CarRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Configure RestAssuredMockMvc with MockMvc instance
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void createCar() {
        Car toyota = new Car("Toyota", "Corolla");

        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body(toyota)
        .when()
            .post("/api/car")
        .then()
            .statusCode(HttpStatus.CREATED.value());

        assertThat(repository.findAll()).extracting(Car::getMake).containsOnly("Toyota");
    }

    @Test
    public void getCars() {
        createTestCar("Toyota", "Corolla");
        createTestCar("Honda", "Civic");

        RestAssuredMockMvc
        .given()
        .when()
            .get("/api/cars")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("make", hasItems("Toyota", "Honda"))
            .body("model", hasItems("Corolla", "Civic"));
    }

    private void createTestCar(String make, String model) {
        Car car = new Car(make, model);
        repository.save(car);
    }
}

