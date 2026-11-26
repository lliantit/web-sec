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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")

@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @GetMapping
    public List<Car> getAllCar(String id) {
        return carService.getAllCars();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @GetMapping("/{id}")
    public Car getCar(@PathVariable String id) {
        return carService.getCar(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @PutMapping
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    @PreAuthorize("hasRole('SUPERADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
    }

    //----------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello/user")
    public String hello() { return "Hello!, user"; }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @GetMapping("/hello/man")
    public String helloMan() { return "Hello, man!"; }

}