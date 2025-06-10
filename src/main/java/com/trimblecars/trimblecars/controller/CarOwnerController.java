package com.trimblecars.trimblecars.controller;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class CarOwnerController {

    @Autowired
    private CarOwnerService carOwnerService;

    @PostMapping("/register-car")
    public ResponseEntity<Car> registerCar(@RequestBody Car car) {
        return ResponseEntity.ok(carOwnerService.registerCar(car));
    }

    @GetMapping("/cars/{ownerId}")
    public ResponseEntity<List<Car>> getOwnerCars(@PathVariable Long ownerId) {
        return ResponseEntity.ok(carOwnerService.getOwnerCars(ownerId));
    }

    @GetMapping("/car-status/{ownerId}/{carId}")
    public ResponseEntity<Car> getCarStatus(@PathVariable Long ownerId, @PathVariable Long carId) {
        return ResponseEntity.ok(carOwnerService.getCarStatus(ownerId, carId));
    }

    @GetMapping("/lease-history/{ownerId}")
    public ResponseEntity<List<Lease>> getLeaseHistory(@PathVariable Long ownerId) {
        return ResponseEntity.ok(carOwnerService.getLeaseHistory(ownerId));
    }


}
