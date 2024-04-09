package tqs.hw1.backend.service;

import java.util.List;
import java.util.Optional;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.entity.Trip;

public interface BookingService {
    // Trip methods
    public Trip saveTrip(Trip trip);
    public Optional<Trip> getTripById(Long id);
    public List<Trip> getTrips();
    public List<Trip> getTripsByCitiesAndWeekDay(String departure, String destionation, String weekDay);

    // Ticket methods
    public Ticket saveTicket(Ticket ticket);
    public Ticket getTicketById(Long id);
    public Ticket getTicketByCitizenCardAndEmail(Long id, String citizenCard, String email);
    public Long countTripTickets(Bus bus, Trip trip, String date, String startTime, String endTime);

    // Bus methods
    public Bus saveBus(Bus bus);
    public Optional<Bus> getBusById(Long id);
    public List<Bus> getBuses();
}
