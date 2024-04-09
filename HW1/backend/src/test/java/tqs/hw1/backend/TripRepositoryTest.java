package tqs.hw1.backend;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.Weekday;
import tqs.hw1.backend.repository.TripRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@DataJpaTest
public class TripRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    private static Logger logger = LogManager.getLogger(TripRepositoryTest.class);
    
    @Test
    public void testFindByDepartureAndDestinationAndStartDayOfWeek_WhenExists(){
        Bus bus = new Bus(50);
        entityManager.persistAndFlush(bus);
        logger.info("========= BUS: " + bus);
        Trip trip = new Trip(bus, "Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        Trip trip2 = new Trip(bus, "Aveiro", "Lisbon", Weekday.MONDAY, "15:30", "18:00", 21.5, null);
        Trip trip3 = new Trip(bus, "Lisbon", "Aveiro", Weekday.TUESDAY, "15:30", "18:00", 22.1, null);
        
        entityManager.persistAndFlush(trip);
        entityManager.persistAndFlush(trip2);
        entityManager.persistAndFlush(trip3);

        List<Trip> found = tripRepository.findByDepartureAndDestinationAndStartDayOfWeek(trip.getDeparture(), trip.getDestination(), trip.getStartDayOfWeek());
        assertThat(found).contains(trip, trip2);
    }

    @Test
    public void testFindByDepartureAndDestinationAndStartDayOfWeek_WhenInvalid(){
        List<Trip> found = tripRepository.findByDepartureAndDestinationAndStartDayOfWeek("Lisbon", "Super Aveiro", Weekday.TUESDAY);
        assertThat(found).isEmpty();
    }
}
