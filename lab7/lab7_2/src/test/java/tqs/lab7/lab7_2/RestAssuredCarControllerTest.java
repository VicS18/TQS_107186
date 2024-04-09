package tqs.lab7.lab7_2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.*;

@WebMvcTest(CarRestController.class)
public class RestAssuredCarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    /*
     * Post simple Car and verify returned value from controller
     */
    public void postCar() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");

        when(service.createCar(Mockito.any())).thenReturn(mercedes);

        RestAssuredMockMvc
            .given()
                .contentType("application/json")
                .body(JsonUtils.toJson(mercedes))
            .when()
                .post("/api/car")
            .then()
                .statusCode(201)
                .body("make", is("Mercedes"))
                .body("model", is("Maybach EQS 680 SUV"));

        verify(service, times(1)).createCar(Mockito.any());
    }
    
    @Test
    public void getCarById() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");

        when(service.getCarById(1L)).thenReturn(mercedes);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/car/1")
            .then()
                .statusCode(200)
                .body("make", is("Mercedes"))
                .body("model", is("Maybach EQS 680 SUV"));

        verify(service, times(1)).getCarById(Mockito.any());
    }

    @Test
    public void getAllCars() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");
        Car toyota = new Car("Toyota", "Prius");
        Car mitsubishi = new Car("Mitsubishi", "2024 Outlander PHEV");

        List<Car> cars = Arrays.asList(mercedes, toyota, mitsubishi);

        when(service.getAllCars()).thenReturn(cars);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/cars")
            .then()
                .statusCode(200)
                .body("", hasSize(cars.size()))

                .body("[0].make", is("Mercedes"))
                .body("[0].model", is("Maybach EQS 680 SUV"))

                .body("[1].make", is("Toyota"))
                .body("[1].model", is("Prius"))

                .body("[2].make", is("Mitsubishi"))
                .body("[2].model", is("2024 Outlander PHEV"));

        verify(service, times(1)).getAllCars();
    }
}
