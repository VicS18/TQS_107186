package tqs.hw1.backend.entity;

import tqs.hw1.backend.service.BookingService;

public class TicketDTO {

    private Long id;

    private Long busId;

    private Long tripId;

    private String status;

    private String date;

    private Double price;

    private String startTime;

    private String endTime;

    private String citizenCard;

    private String email;

    public static Ticket toTicket(BookingService bookingService, TicketDTO ticketDTO){
        Bus bus = bookingService.getBusById(ticketDTO.getBusId()).get();
        Trip trip = bookingService.getTripById(ticketDTO.getTripId()).get();
        
        return new Ticket(
            bus, 
            trip, 
            Status.valueOf(ticketDTO.getStatus()), 
            ticketDTO.getDate(), 
            ticketDTO.getPrice(),
            ticketDTO.getStartTime(),
            ticketDTO.getEndTime(),
            ticketDTO.getCitizenCard(),
            ticketDTO.getEmail()
            );
    }

    public Ticket toTicket(BookingService bookingService){
        return TicketDTO.toTicket(bookingService, this);
    }

    public TicketDTO() {
    }

    public TicketDTO(Long id, Long busId, Long tripId, String status, String date, Double price, String startTime,
            String endTime, String citizenCard, String email) {
        this.id = id;
        this.busId = busId;
        this.tripId = tripId;
        this.status = status;
        this.date = date;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.citizenCard = citizenCard;
        this.email = email;
    }

    public TicketDTO(Long busId, Long tripId, String status, String date, Double price, String startTime, String endTime,
            String citizenCard, String email) {
        this.busId = busId;
        this.tripId = tripId;
        this.status = status;
        this.date = date;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.citizenCard = citizenCard;
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getCitizenCard() {
        return citizenCard;
    }

    public void setCitizenCard(String citizenCard) {
        this.citizenCard = citizenCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TicketDTO [id=" + id + ", busId=" + busId + ", tripId=" + tripId + ", status=" + status + ", date="
                + date + ", price=" + price + ", startTime=" + startTime + ", endTime=" + endTime + ", citizenCard="
                + citizenCard + ", email=" + email + "]";
    }
}
