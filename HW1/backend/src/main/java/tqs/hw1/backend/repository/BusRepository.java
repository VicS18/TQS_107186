package tqs.hw1.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.hw1.backend.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    
}
