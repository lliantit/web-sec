package chnu.edu.websec.service;

import chnu.edu.websec.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private static List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("1", "BMW M5", "2024", "High-performance sedan", "Black"));
        cars.add(new Car("2", "Audi A6", "2023", "Comfortable sedan", "White"));
        cars.add(new Car("3", "Toyota Camry", "2024", "Reliable sedan", "Silver"));
        cars.add(new Car("4", "Mercedes-Benz GLE", "2022", "Premium SUV", "Blue"));
        cars.add(new Car("5", "Tesla Model 3", "2025", "Electric sedan", "Red"));
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public static Car getCar(String id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Car createCar(Car car) {
        cars.add(car);
        return car;
    }

    public Car updateCar(Car car) {
        Car oldCar = getCar(car.getId());

        if (oldCar != null) {
            cars.remove(oldCar);
            cars.add(car);
        }

        return car;
    }

    public static void deleteCar(String id) {
        Car car = getCar(id);

        if (car != null) {
            cars.remove(car);
        }
    }
}
