package tqs.lab3.lab3_2;

import java.util.List;

public interface CarManagerService {
    public Car createCar(Car car);

    public List<Car> getAllCars();

    public Car getCarById(Long id);
}
