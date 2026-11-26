package chnu.edu.websec.service;
import chnu.edu.websec.model.Car;
import chnu.edu.websec.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private static List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("1", "BMW M5", "2024", "High-performance sedan", "Black"));
        cars.add(new Car("2", "Audi A6", "2023", "Comfortable sedan", "White"));
        cars.add(new Car("3", "Toyota Camry", "2024", "Reliable sedan", "Silver"));
        cars.add(new Car("4", "Mercedes-Benz GLE", "2022", "Premium SUV", "Blue"));
        cars.add(new Car("5", "Tesla Model 3", "2025", "Electric sedan", "Red"));
    }

    @PostConstruct
    void init() {
        carRepository.deleteAll();
        carRepository.saveAll(cars);
    }



    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(String id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        Car existing = carRepository.findById(car.getId()).orElse(null);
        if (existing != null) {
            existing.setName(car.getName());
            existing.setYear(car.getYear());
            existing.setDescription(car.getDescription());
            existing.setColor(car.getColor());
            return carRepository.save(existing);
        }
        return null;
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }
}
