package chnu.edu.websec.controller;

/*
  @author   qwert
  @project   web-sec
  @class  CarController
  @version  1.0.0 
  @since 20.09.2026 - 03.18



*/

import chnu.edu.websec.model.Car;
import chnu.edu.websec.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")

@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

        @GetMapping
        public List<Car> getAllCar(String id) {
            return carService.getAllCars();
        }

        @GetMapping("/{id}")
        public Car getCar(@PathVariable String id) {
            return carService.getCar(id);
        }
        @PostMapping
        public Car createCar(@RequestBody Car car) {
            return carService.createCar(car);
        }

        @PutMapping
        public Car updateCar(@RequestBody Car car) {
            return carService.updateCar(car);
        }
        @DeleteMapping("/{id}")
        public void deleteCar(@PathVariable String id) {
            CarService.deleteCar(id);
        }

}
