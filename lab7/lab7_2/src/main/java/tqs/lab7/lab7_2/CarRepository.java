package tqs.lab7.lab7_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Method to fetch all cars
    public List<Car> findAll();
}

