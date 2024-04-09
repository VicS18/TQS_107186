package tqs.hw1.backend.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
    
    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;
    
    @Column(name = "start_day_of_week")
    @Enumerated(EnumType.STRING)
    private Weekday startDayOfWeek;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "trip")
    private Set<Ticket> tickets;

    public Trip() {
    }

    public Trip(Long id, String departure, String destination, Weekday startDayOfWeek, String startTime, String endTime,
            Double price, Set<Ticket> tickets) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.tickets = tickets;
    }

    public Trip(Bus bus, String departure, String destination, Weekday startDayOfWeek, String startTime, String endTime,
            Double price, Set<Ticket> tickets) {
        this.bus = bus;
        this.departure = departure;
        this.destination = destination;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.tickets = tickets;
    }

    public Trip(String departure, String destination, Weekday startDayOfWeek, String startTime, String endTime,
            Double price, Set<Ticket> tickets) {
        this.departure = departure;
        this.destination = destination;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.tickets = tickets;
    }

    public TripDTO toDTO() {
        return new TripDTO(bus.getId(), id, departure, destination, startDayOfWeek, startTime, endTime, price, tickets);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BusTrip{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", startDayOfWeek='" + startDayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Weekday getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public void setStartDayOfWeek(Weekday startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((departure == null) ? 0 : departure.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((startDayOfWeek == null) ? 0 : startDayOfWeek.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trip other = (Trip) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (departure == null) {
            if (other.departure != null)
                return false;
        } else if (!departure.equals(other.departure))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (startDayOfWeek != other.startDayOfWeek)
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (tickets == null) {
            if (other.tickets != null)
                return false;
        } else if (!tickets.equals(other.tickets))
            return false;
        return true;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    
}

