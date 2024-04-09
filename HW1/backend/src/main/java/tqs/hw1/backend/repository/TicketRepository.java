package tqs.hw1.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.hw1.backend.entity.Bus;
import tqs.hw1.backend.entity.Ticket;
import tqs.hw1.backend.entity.Trip;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public Ticket findByIdAndCitizenCardAndEmail(Long id, String citizenCard, String email);
    public Long countByBusAndTripAndDateAndStartTimeAndEndTime(Bus bus, Trip trip, String date, String startTime, String endTime);
}
