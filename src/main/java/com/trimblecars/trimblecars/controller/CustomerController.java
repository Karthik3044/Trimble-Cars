package com.trimblecars.trimblecars.controller;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.entity.User;
import com.trimblecars.trimblecars.service.CarOwnerService;
import com.trimblecars.trimblecars.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listAvailableCars() {
        return ResponseEntity.ok(customerService.listAvailableCars());
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<Car>> viewAllCars() {
        return ResponseEntity.ok(customerService.viewAllCars());
    }

    @PostMapping("/start-lease/{customerId}/{carId}")
    public ResponseEntity<Lease> startLease(@PathVariable Long customerId, @PathVariable Long carId) {
        return ResponseEntity.ok(customerService.startLease(customerId, carId));
    }

    @PutMapping("/end-lease/{leaseId}")
    public ResponseEntity<Lease> endLease(@PathVariable Long leaseId) {
        return ResponseEntity.ok(customerService.endLease(leaseId));
    }

    @GetMapping("/lease-history/{customerId}")
    public ResponseEntity<List<Lease>> getCustomerHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerHistory(customerId));
    }

    @PostMapping("/save-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){

        return ResponseEntity.ok(customerService.saveUser(user));
    }
}
