package tqs.lab3.lab3_2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;


@WebMvcTest(CarRestController.class)
public class CarRestController_MockServiceTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    /*
     * Post simple Car and verify returned value from controller
     */
    public void postCar() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");

        when(service.createCar(Mockito.any())).thenReturn(mercedes);

        mvc.perform(
            post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(mercedes))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.make", is("Mercedes")))
        .andExpect(jsonPath("$.model", is("Maybach EQS 680 SUV")));

        verify(service, times(1)).createCar(Mockito.any());
    }

    public void getCarById() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");

        when(service.getCarById(1L)).thenReturn(mercedes);

        mvc.perform(
            get("/api/car/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.make", is("Mercedes")))
        .andExpect(jsonPath("$.model", is("Maybach EQS 680 SUV")));

        verify(service, times(1)).getCarById(Mockito.any());
    }

    public void getAllCars() throws Exception{
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");
        Car toyota = new Car("Toyota", "Prius");
        Car mitsubishi = new Car("Mitsubishi", "2024 Outlander PHEV");

        List<Car> cars = Arrays.asList(mercedes, toyota, mitsubishi);

        when(service.getAllCars()).thenReturn(cars);

        mvc.perform(
            get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(cars.size())))

        .andExpect(jsonPath("$[0].make", is("Mercedes")))
        .andExpect(jsonPath("$[0].model", is("Maybach EQS 680 SUV")))

        .andExpect(jsonPath("$[1].make", is("Toyota")))
        .andExpect(jsonPath("$[1].model", is("Prius")))

        .andExpect(jsonPath("$[2].make", is("Mitsubishi")))
        .andExpect(jsonPath("$[2].model", is("2024 Outlander PHEV")));

        verify(service, times(1)).getAllCars();
    }
}
