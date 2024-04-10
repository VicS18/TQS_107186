package tqs.lab7.lab7_4;

import java.util.List;

public interface CarManagerService {
    public Car createCar(Car car);

    public List<Car> getAllCars();

    public Car getCarById(Long id);
}
