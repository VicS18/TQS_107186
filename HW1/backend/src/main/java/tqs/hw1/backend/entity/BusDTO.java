package tqs.hw1.backend.entity;

import java.util.HashSet;
import java.util.Set;

public class BusDTO {

    private Long id;
    private Integer maxSeats;
    private Set<Long> tickets;

    public BusDTO(Long id, Integer maxSeats, Set<Ticket> tickets) {
        this.id = id;
        this.maxSeats = maxSeats;
        this.tickets = convertTicketsToIds(tickets);
    }

    public BusDTO(Long id, Integer maxSeats) {
        this.id = id;
        this.maxSeats = maxSeats;
    }

    public static Set<Long> convertTicketsToIds(Set<Ticket> tickets) {
        Set<Long> ticketIds = new HashSet<>();
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                ticketIds.add(ticket.getId());
            }
        }
        return ticketIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Set<Long> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Long> tickets) {
        this.tickets = tickets;
    }


}

