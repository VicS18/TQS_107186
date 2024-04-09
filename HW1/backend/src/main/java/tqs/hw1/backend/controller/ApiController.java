package tqs.hw1.backend.controller;

import tqs.hw1.backend.cache.Currency;
import tqs.hw1.backend.cache.CurrencyAPI;
import tqs.hw1.backend.cache.TTLCache;
import tqs.hw1.backend.entity.*;
import tqs.hw1.backend.service.BookingService;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController 
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private BookingService bookingService;

    private final Duration TTL = Duration.ofSeconds(1500);

    private TTLCache ttlCache = new TTLCache(new CurrencyAPI("fca_live_3hZibvJW9Scmg7giaauLj5XmhaotgLWrBDLtUDAN", "https://api.freecurrencyapi.com/v1/"), TTL);
    
    private static Logger logger = LogManager.getLogger(ApiController.class);

    // Trip searching endpoints
    @GetMapping("trip/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        Trip retTrip = null;
        try{
            retTrip = bookingService.getTripById(id).get();
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retTrip.toDTO(), HttpStatus.OK);
    }

    @PostMapping("trip")
    public ResponseEntity<TripDTO> postTrip(@RequestBody TripDTO tripDTO) {
        logger.info("====== POST_TRIP DTO: " + tripDTO);
        Trip trip = tripDTO.toEntity(bookingService);
        logger.info("====== POST_TRIP: " + trip);
        Trip retTrip = bookingService.saveTrip(trip);
        logger.info("====== POST_RET_TRIP: " + retTrip);
        return new ResponseEntity<>(retTrip.toDTO(), HttpStatus.CREATED);
    }

    @GetMapping("trips")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<Trip> trips = bookingService.getTrips();
        List<TripDTO> tripDTOs = new ArrayList<TripDTO>();
        for(Trip t : trips)
            tripDTOs.add(t.toDTO());
        return new ResponseEntity<>(tripDTOs, HttpStatus.OK);
    }

    @GetMapping("trip")
    public ResponseEntity<List<TripDTO>> getTripsByDeparture(@RequestParam String departure, 
                                                       @RequestParam String destination, 
                                                       @RequestParam String weekDay,
                                                       @RequestParam(required = false) String currency)
                                                       throws IOException {
        List<Trip> trips = bookingService.getTripsByCitiesAndWeekDay(departure, destination, weekDay);
        List<TripDTO> tripDTOs = new ArrayList<TripDTO>();

        // Get exchange rate
        double rate = 1;
        if(currency != null){
            Currency curr = Currency.valueOf(currency);
            rate = ttlCache.getExchangeRate(curr);
        }

        for(Trip t : trips)
        {
            TripDTO dto = t.toDTO();
            dto.setPrice(dto.getPrice()*rate);
            tripDTOs.add(t.toDTO());
        }
        return new ResponseEntity<>(tripDTOs, HttpStatus.OK);
    }

    // Ticket endpoints
    
    @GetMapping("ticket")
    public ResponseEntity<TicketDTO> getTicketByCCAndEmail(@RequestBody TicketRequestDTO req) {
        // TODO: Validate CC and Email
        Ticket ticket = bookingService.getTicketByCitizenCardAndEmail(req.getId(), req.getCitizenCard(), req.getEmail());
        return new ResponseEntity<>(ticket.toDTO(), HttpStatus.OK);
    }

    @PostMapping("ticket")
    public ResponseEntity<TicketDTO> postTicket(@RequestBody TicketDTO ticketDTO) {
        // TODO: Validate CC and Email
        // TODO: Check if bus and trip exist (handle exception)
        // TODO: Check if ticket date is consistent with trip day of week
        logger.info("======== POST_TICKET_DTO: " + ticketDTO.toString());
        Ticket ticket = ticketDTO.toTicket(bookingService);
        logger.info("======== POST_TO_TICKET: " + ticket.toString());

        ticket = bookingService.saveTicket(ticket);
        logger.info("======== POST_TICKET_SAVED: " + ticket);
        
        if(ticket == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(ticket.toDTO(), HttpStatus.CREATED);
    }

    // Bus endpoints

    @GetMapping("bus/{id}")
    public ResponseEntity<BusDTO> getBus(@PathVariable Long id) {
        // TODO: Handle exception
        Bus bus = bookingService.getBusById(id).get();
        return new ResponseEntity<>(bus.toDTO(), HttpStatus.OK);
    }
    
    @PostMapping("bus")
    public ResponseEntity<BusDTO> postBus(@RequestBody Bus bus) {
        //TODO: Validate max seats
        Bus retBus = bookingService.saveBus(bus);
        return new ResponseEntity<>(retBus.toDTO(), HttpStatus.CREATED);
    }
    
}
