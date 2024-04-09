package tqs.hw1.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "ticket_date")
    private String date;

    @Column(name = "price") 
    private Double price;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "citizen_card")
    private String citizenCard;

    @Column(name = "email")
    private String email;

    public Ticket() {
    }

    public Ticket(Bus bus, Trip trip, Status status, String date, Double price, String startTime, String endTime,
            String citizenCard, String email) {
        this.bus = bus;
        this.trip = trip;
        this.status = status;
        this.date = date;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.citizenCard = citizenCard;
        this.email = email;
    }

    public TicketDTO toDTO(){
        return new TicketDTO(id, bus.getId(), trip.getId(), status.toString(), date, price, startTime, endTime, citizenCard, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((bus == null) ? 0 : bus.hashCode());
        result = prime * result + ((trip == null) ? 0 : trip.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((citizenCard == null) ? 0 : citizenCard.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Ticket other = (Ticket) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (bus == null) {
            if (other.bus != null)
                return false;
        } else if (!bus.getId().equals(other.bus.getId()))
            return false;
        if (trip == null) {
            if (other.trip != null)
                return false;
        } else if (!trip.getId().equals(other.trip.getId()))
            return false;
        if (status != other.status)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
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
        if (citizenCard == null) {
            if (other.citizenCard != null)
                return false;
        } else if (!citizenCard.equals(other.citizenCard))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", bus=" + bus + ", trip=" + trip + ", status=" + status + ", date=" + date
                + ", price=" + price + ", startTime=" + startTime + ", endTime=" + endTime + ", citizenCard="
                + citizenCard + ", email=" + email + "]";
    }

    
    
}
