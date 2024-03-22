package tqs.lab3.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerServiceImpl carManagerService;

    @BeforeEach
    public void setUp() {
        Car mercedes = new Car("Mercedes", "Maybach EQS 680 SUV");
        Car toyota = new Car("Toyota", "Prius");
        Car mitsubishi = new Car("Mitsubishi", "2024 Outlander PHEV");

        List<Car> allCars = Arrays.asList(mercedes, toyota, mitsubishi);

        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findById(1L)).thenReturn(Optional.of(mercedes));
        when(carRepository.findById(2L)).thenReturn(Optional.of(toyota));
        when(carRepository.findById(3L)).thenReturn(Optional.of(mitsubishi));
        when(carRepository.findById(999L)).thenReturn(Optional.empty());
    }

    @Test
    void getAllCarsTest() {
        List<Car> cars = carManagerService.getAllCars();

        assertThat(cars).isNotNull();
        assertThat(cars.size()).isEqualTo(3);

        Car mercedes = cars.get(0);
        Car toyota = cars.get(1);
        Car mitsubishi = cars.get(2); 

        assertThat(mercedes.getMake()).isEqualTo("Mercedes");
        assertThat(mercedes.getModel()).isEqualTo("Maybach EQS 680 SUV");

        assertThat(toyota.getMake()).isEqualTo("Toyota");
        assertThat(toyota.getModel()).isEqualTo("Prius");

        assertThat(mitsubishi.getMake()).isEqualTo("Mitsubishi");
        assertThat(mitsubishi.getModel()).isEqualTo("2024 Outlander PHEV");

        verifyFindAllCarsIsCalledOnce();
    }

    @Test
    void getCarByIdTest() {
        Long validId = 1L;
        Long invalidId = 999L;
        Car validCar = carManagerService.getCarById(validId);
        Car invalidCar = carManagerService.getCarById(invalidId);

        assertThat(validCar).isNotNull();
        assertThat(validCar.getMake()).isEqualTo("Mercedes");
        assertThat(validCar.getModel()).isEqualTo("Maybach EQS 680 SUV");

        assertThat(invalidCar).isNull();

        verifyFindByIdIsCalledOnce(invalidId);
        verifyFindByIdIsCalledOnce(validId);
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(id);
    }
    
    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }    
}
