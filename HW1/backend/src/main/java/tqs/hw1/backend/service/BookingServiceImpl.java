package tqs.hw1.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.Weekday;
import tqs.hw1.backend.repository.BusRepository;
import tqs.hw1.backend.repository.TicketRepository;
import tqs.hw1.backend.repository.TripRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusRepository busRepository;

    private static Logger logger = LogManager.getLogger(BookingServiceImpl.class);

    // Trip methods
    public Trip saveTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(Long id){
        return tripRepository.findById(id);
    }

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByCitiesAndWeekDay(String departure, String destination, String weekDay){
        return tripRepository.findByDepartureAndDestinationAndStartDayOfWeek(departure, destination, Weekday.valueOf(weekDay));
    }

    // Ticket methods
    public Ticket saveTicket(Ticket ticket){
        // Check if there's an available seat
        int maxBusSeats = ticket.getBus().getMaxSeats();
        logger.info("Max Bus Seats: " + Integer.toString(maxBusSeats));
        long occupiedSeats = this.countTripTickets(
            ticket.getBus(),
            ticket.getTrip(),
            ticket.getDate(),
            ticket.getStartTime(),
            ticket.getEndTime());
        logger.info("Occupied Seats: " + Long.toString(occupiedSeats));
        if(occupiedSeats >= (long)(maxBusSeats)){
            return null;
        }
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id){
        return ticketRepository.findById(id).get();
    }

    public Ticket getTicketByCitizenCardAndEmail(Long id, String citizenCard, String email){
        return ticketRepository.findByIdAndCitizenCardAndEmail(id, citizenCard, email);
    }

    public Long countTripTickets(Bus bus, Trip trip, String date, String startTime, String endTime){
        return ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, date, startTime, endTime);
    }

    // Bus methods
    public Bus saveBus(Bus bus){
        return busRepository.save(bus);
    }

    public Optional<Bus> getBusById(Long id){
        return busRepository.findById(id);
    }

    public List<Bus> getBuses(){
        return busRepository.findAll();
    }
}
