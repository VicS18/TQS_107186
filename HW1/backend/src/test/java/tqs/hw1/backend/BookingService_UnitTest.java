package tqs.hw1.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Status;
import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.entity.Weekday;
import tqs.hw1.backend.repository.BusRepository;
import tqs.hw1.backend.repository.TicketRepository;
import tqs.hw1.backend.repository.TripRepository;
import tqs.hw1.backend.service.BookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingService_UnitTest {
    @Mock(lenient = true)
    private TripRepository tripRepository;

    @Mock(lenient = true)
    private BusRepository busRepository;

    @Mock(lenient = true)
    private TicketRepository ticketRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    // private static Logger logger = LogManager.getLogger(BookingService_UnitTest.class);

    @BeforeEach
    public void setUp() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mock(Ticket.class));
    }

    @Test 
    public void testSaveTicket() {
        Bus bus = new Bus(40);
        bus.setId(1l);
        Trip trip = new Trip("Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        trip.setId(1l);
        Ticket ticket = new Ticket(bus, trip, Status.PENDING, "2024-04-23", 20.0, "13:30", "16:00", "000000000ZZ4", "test@test.pt");
        
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket saved = bookingService.saveTicket(ticket);

        assertThat(saved).isEqualTo(ticket);

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    /* 
    @Test 
    public void testSaveTicket_WhenNoSeatsLeft() {
        Bus bus = new Bus(40);
        bus.setId(1l);
        Trip trip = new Trip("Aveiro", "Lisbon", Weekday.MONDAY, "13:30", "16:00", 20.0, null);
        trip.setId(1l);

        // Ticket ticket = null;
        // Ticket saved = null;

        // for(int i = 0; i <= bus.getMaxSeats(); i++){
        //     ticket = new Ticket(
        //         bus, 
        //         trip, 
        //         Status.PENDING, 
        //         "2024-04-23", 
        //         20.0,
        //         "13:30",
        //         "16:00",
        //         "000000000ZZ4",
        //         "test"+Integer.toString(i)+"@test.pt"
        //     );
        //     when(ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-03", "13:30", "16:00")).thenReturn((long)i);
        //     logger.info("COUNT: " + Long.toString(ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-03", "13:30", "16:00")));
        //     when(ticketRepository.save(ticket)).thenReturn(ticket);
        //     saved = bookingService.saveTicket(ticket);
        //     logger.info("Saved ticket: " + saved);
        // }

        // Assume there are 40 tickets = 40 seats

        Ticket ticket = new Ticket(
            bus, 
            trip, 
            Status.PENDING, 
            "2024-04-23", 
            20.0,
            "13:30",
            "16:00",
            "000000000ZZ4",
            "test"+Integer.toString(10)+"@test.pt"
        );

        when(ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-03", "13:30", "16:00")).thenReturn((long) bus.getMaxSeats());
        logger.info("COUNT: " + Long.toString(ticketRepository.countByBusAndTripAndDateAndStartTimeAndEndTime(bus, trip, "2024-04-03", "13:30", "16:00")));
        
        Ticket saved = bookingService.saveTicket(ticket);

        assertThat(saved).isNull();

        verify(ticketRepository, times(bus.getMaxSeats()+1)).save(any(Ticket.class));
    }
    */
}
