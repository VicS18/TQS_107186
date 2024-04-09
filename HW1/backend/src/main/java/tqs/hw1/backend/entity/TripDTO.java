package tqs.hw1.backend.entity;

import java.util.HashSet;
import java.util.Set;

import tqs.hw1.backend.service.BookingService;

public class TripDTO {

    private Long busId;
    private Long id;
    private String departure;
    private String destination;
    private Weekday startDayOfWeek;
    private String startTime;
    private String endTime;
    private Double price;
    private Set<Long> ticketIds;

    // Constructor, getters, setters, and other methods

    public TripDTO(Long busId, String departure, String destination, Weekday startDayOfWeek, String startTime,
            String endTime, Double price, Set<Ticket> tickets) {
        this.busId = busId;
        this.departure = departure;
        this.destination = destination;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.ticketIds = convertTicketsToIds(tickets);
    }

    public TripDTO() {
    }

    public TripDTO(Long busId, Long id, String departure, String destination, Weekday startDayOfWeek, String startTime,
            String endTime, Double price, Set<Ticket> tickets) {
        this.busId = busId;
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.ticketIds = convertTicketsToIds(tickets);
    }

    public Trip toEntity(BookingService bookingService){
        return new Trip(bookingService.getBusById(busId).get(), departure, destination, startDayOfWeek, startTime, endTime, price, convertIdsToTickets(ticketIds, bookingService));
    }

    public static Set<Ticket> convertIdsToTickets(Set<Long> ticketIds, BookingService bookingService){
        Set<Ticket> tickets = new HashSet<>();
        if(ticketIds != null)
            for (Long i : ticketIds) {
                tickets.add(bookingService.getTicketById(i));
            }
        return tickets;
    }

    public static Set<Long> convertTicketsToIds(Set<Ticket> tickets) {
        Set<Long> ticketIds = new HashSet<>();
        if(tickets != null)
            for (Ticket ticket : tickets) {
                ticketIds.add(ticket.getId());
            }
        return ticketIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Weekday getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public void setStartDayOfWeek(Weekday startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(Set<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    @Override
    public String toString() {
        return "TripDTO [busId=" + busId + ", id=" + id + ", departure=" + departure + ", destination=" + destination
                + ", startDayOfWeek=" + startDayOfWeek + ", startTime=" + startTime + ", endTime=" + endTime
                + ", price=" + price + ", ticketIds=" + ticketIds + "]";
    }
    
}
