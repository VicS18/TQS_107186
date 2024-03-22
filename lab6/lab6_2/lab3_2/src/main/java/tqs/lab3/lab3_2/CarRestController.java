package tqs.lab3.lab3_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarRestController {

    private CarManagerService carManagerService;

    @Autowired
    public CarRestController(CarManagerService carManagerService){
        this.carManagerService = carManagerService;
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carManagerService.createCar(car);
        return new ResponseEntity<Car>(createdCar, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carManagerService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        }
    }
}

