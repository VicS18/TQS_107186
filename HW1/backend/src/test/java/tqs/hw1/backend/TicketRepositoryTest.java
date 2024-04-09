package tqs.hw1.backend;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.Weekday;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.repository.TicketRepository;
import tqs.hw1.backend.entity.Status;

@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;
    
    @Test
    public void testFindByIdAndCitizenCardAndEmail_WhenExists(){
        Bus bus = new Bus(40);
        Trip trip = new Trip(bus, "Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        Ticket ticket = new Ticket(bus, trip, Status.PENDING, "2024-04-23", 20.0, "13:30", "16:00", "000000000ZZ4", "test@test.pt");
        
        entityManager.persistAndFlush(bus);
        entityManager.persistAndFlush(trip);
        entityManager.persistAndFlush(ticket);

        Ticket found = ticketRepository.findByIdAndCitizenCardAndEmail(ticket.getId(), ticket.getCitizenCard(), ticket.getEmail());
        assertThat(found).isEqualTo(ticket);
    }

    @Test
    public void testFindByIdAndCitizenCardAndEmail_WhenInvalidSearch(){
        Ticket found = ticketRepository.findByIdAndCitizenCardAndEmail(1l, "000000000ZZ4", "blablabla I'm a bad email");
        assertThat(found).isNull();
    }

    @Test
    public void testCountByBusAndTripAndDateAndStartTimeAndEndTime_WhenExists(){
        Bus bus = new Bus(40);
        entityManager.persistAndFlush(bus);
        Trip trip = new Trip(bus, "Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        entityManager.persistAndFlush(trip);
        Ticket ticket = new Ticket(bus, trip, Status.PENDING, "2024-04-23", 20.0, "13:30", "16:00", "000000000ZZ4", "test@test.pt");
        Ticket ticket2 = new Ticket(bus, trip, Status.PENDING, "2024-04-23", 20.0, "13:30", "16:00", "000000000ZZ4", "different@mail.pt");

        entityManager.persistAndFlush(ticket);
        entityManager.persistAndFlush(ticket2);

        Long count = ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-23", "13:30", "16:00");
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void testCountByBusAndTripAndDateAndStartTimeAndEndTime_WhenDifferent(){
        Bus bus = new Bus(40);
        Trip trip = new Trip(bus, "Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        Trip trip2 = new Trip(bus, "Lisbon", "Aveiro", Weekday.MONDAY, "14:30", "17:00", 21.0, null);
        Ticket ticket = new Ticket(bus, trip, Status.PENDING, "2024-04-23", 20.0, "13:30", "16:00", "000000000ZZ4", "test@test.pt");
        Ticket ticket2 = new Ticket(bus, trip2, Status.PENDING, "2024-04-23", 20.0, "14:30", "17:00", "000000000ZZ4", "different@mail.pt");

        entityManager.persistAndFlush(bus);
        entityManager.persistAndFlush(trip);
        entityManager.persistAndFlush(trip2);
        entityManager.persistAndFlush(ticket);
        entityManager.persistAndFlush(ticket2);

        Long count = ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-23", "13:30", "16:00");
        assertThat(count).isEqualTo(1);
    }
}
