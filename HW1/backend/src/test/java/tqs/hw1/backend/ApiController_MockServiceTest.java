package tqs.hw1.backend;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.hw1.backend.controller.ApiController;
import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Status;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.entity.TicketDTO;
import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.TripDTO;
import tqs.hw1.backend.entity.Weekday;
import tqs.hw1.backend.service.BookingService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebMvcTest(ApiController.class)
public class ApiController_MockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    private static Logger logger = LogManager.getLogger(ApiController.class);

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);

        // Mocking behavior for getBus
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setMaxSeats(50);
        when(bookingService.getBusById(1L)).thenReturn(Optional.of(bus));

        // Mocking behavior for getTripById
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setBus(bus);
        trip.setDeparture("CityA");
        trip.setDestination("CityB");
        trip.setStartDayOfWeek(Weekday.MONDAY);
        trip.setStartTime("08:00");
        trip.setEndTime("12:00");
        trip.setPrice(100.0);
        when(bookingService.getTripById(1L)).thenReturn(Optional.of(trip));

        // Mocking behavior for getAllTrips
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        when(bookingService.getTrips()).thenReturn(trips);

        // Mocking behavior for getTicketByCCAndEmail
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCitizenCard("000000000ZZ4");
        ticket.setEmail("test@example.com");
        ticket.setBus(bus);
        ticket.setTrip(trip);
        ticket.setStatus(Status.PENDING);
        when(bookingService.getTicketByCitizenCardAndEmail(eq(1L), eq("000000000ZZ4"), eq("test@example.com"))).thenReturn(ticket);
    }

    @Test
    public void testGetTripById() throws Exception {
        given()
            .when()
            .get("/api/v1/trip/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("departure", equalTo("CityA"))
            .body("destination", equalTo("CityB"))
            .body("startDayOfWeek", equalTo("MONDAY"))
            .body("startTime", equalTo("08:00"))
            .body("endTime", equalTo("12:00"))
            .body("price", equalTo(100.0f))
            .body("ticketIds", hasSize(0));
    }

    @Test
    public void testGetAllTrips() throws Exception {
        given()
            .when()
            .get("/api/v1/trips")
            .then()
            .statusCode(200)
            .body("$", hasSize(1))
            .body("[0].id", equalTo(1))
            .body("[0].departure", equalTo("CityA"))
            .body("[0].destination", equalTo("CityB"))
            .body("[0].startDayOfWeek", equalTo("MONDAY"))
            .body("[0].startTime", equalTo("08:00"))
            .body("[0].endTime", equalTo("12:00"))
            .body("[0].price", equalTo(100.0f))
            .body("[0].ticketIds", hasSize(0));
    }

    @Test
    public void testGetBus() throws Exception {
        given()
            .when()
            .get("/api/v1/bus/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("maxSeats", equalTo(50))
            .body("tickets", hasSize(0));
    }

    @Test
    public void testGetTicketByCCAndEmail() throws Exception {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("{\"id\": 1, \"citizenCard\": \"000000000ZZ4\", \"email\": \"test@example.com\"}")
            .when()
            .get("/api/v1/ticket")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("citizenCard", equalTo("000000000ZZ4"))
            .body("email", equalTo("test@example.com"));
    }
    
    @Test
    public void testGetBusById() throws Exception {
        given()
            .when()
            .get("/api/v1/bus/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("maxSeats", equalTo(50))
            .body("tickets", hasSize(0));
    }

    @Test
    public void testPostTrip() throws Exception {
        TripDTO tripDTO = new TripDTO(1L, "CityA", "CityB", Weekday.MONDAY, "08:00", "12:00", 100.0, new HashSet<>());
        Trip trip = tripDTO.toEntity(bookingService);
        logger.info("======== TRIP: " + trip);
        when(bookingService.saveTrip(trip)).thenReturn(trip);

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(tripDTO)
            .when()
            .post("/api/v1/trip")
            .then()
            .statusCode(201)
            .body("departure", equalTo("CityA"))
            .body("destination", equalTo("CityB"))
            .body("startDayOfWeek", equalTo("MONDAY"))
            .body("startTime", equalTo("08:00"))
            .body("endTime", equalTo("12:00"))
            .body("price", equalTo(100.0f))
            .body("ticketIds", hasSize(0));
    }
    
    @Test
    public void testPostTicket() throws Exception {
        TicketDTO ticketDTO = new TicketDTO(1L, 1L, "PENDING", "2024-04-09", 50.0, "08:00", "12:00", "000000000ZZ4", "test@example.com");
        Ticket ticket = ticketDTO.toTicket(bookingService);
        when(bookingService.saveTicket(ticket)).thenReturn(ticket);
    
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(ticketDTO)
            .when()
            .post("/api/v1/ticket")
            .then()
            .statusCode(201)
            .body("citizenCard", equalTo("000000000ZZ4"))
            .body("email", equalTo("test@example.com"));
    }
    
    @Test
    public void testPostBus() throws Exception {
        Bus bus = new Bus(50);
        bus.setId(2L);
        when(bookingService.saveBus(bus)).thenReturn(bus);

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(bus)
            .when()
            .post("/api/v1/bus")
            .then()
            .statusCode(201)
            .body("id", equalTo(2))
            .body("maxSeats", equalTo(50))
            .body("tickets", hasSize(0));
    }
    
}
