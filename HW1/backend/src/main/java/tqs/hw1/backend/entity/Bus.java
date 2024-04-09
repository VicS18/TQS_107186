package tqs.hw1.backend.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_seats")
    private Integer maxSeats;

    @OneToMany(mappedBy = "bus")
    private Set<Ticket> tickets;

    public Bus() {
    }

    public Bus(Long id, Integer maxSeats) {
        this.id = id;
        this.maxSeats = maxSeats;
    }

    public Bus(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public BusDTO toDTO() {
        return new BusDTO(id, maxSeats, tickets);
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maxSeats == null) ? 0 : maxSeats.hashCode());
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
		Bus other = (Bus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxSeats == null) {
			if (other.maxSeats != null)
				return false;
		} else if (!maxSeats.equals(other.maxSeats))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Bus [id=" + id + ", maxSeats=" + maxSeats + "]";
    }
}
