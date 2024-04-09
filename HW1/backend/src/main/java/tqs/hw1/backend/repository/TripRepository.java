package tqs.hw1.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.hw1.backend.entity.Trip;
import tqs.hw1.backend.entity.Weekday;

public interface TripRepository extends JpaRepository<Trip, Long> {
    public List<Trip> findByDepartureAndDestinationAndStartDayOfWeek(String departure, String destination, Weekday weekDay);
}
