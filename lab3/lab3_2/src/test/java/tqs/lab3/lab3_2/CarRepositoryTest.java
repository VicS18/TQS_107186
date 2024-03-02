package tqs.lab3.lab3_2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void findById() {
        Car toyota = new Car("Toyota", "Corolla");
        entityManager.persistAndFlush(toyota);

        Car foundCar = carRepository.findById(toyota.getId()).get();

        assertThat(foundCar).isNotNull();
        assertThat(foundCar).isEqualTo(toyota);
    }

    @Test
    public void findByInvalidId() {
        Optional<Car> foundCar = carRepository.findById(-1L);

        assertThat(foundCar).isEmpty();
    }

    @Test
    public void findAll() {
        Car toyota = new Car("Toyota", "Corolla");
        Car honda = new Car("Honda", "Civic");
        Car ford = new Car("Ford", "Focus");

        entityManager.persist(toyota);
        entityManager.persist(honda);
        entityManager.persist(ford);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).containsExactlyInAnyOrder(toyota, honda, ford);
    }

    @Test
    public void invalidFindByAll() {
        List<Car> foundCars = carRepository.findAll();

        assertThat(foundCars).isEmpty();
    }
}
