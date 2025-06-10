package com.trimblecars.trimblecars.service;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.entity.User;
import com.trimblecars.trimblecars.repository.CarRepository;
import com.trimblecars.trimblecars.repository.LeaseRepository;
import com.trimblecars.trimblecars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Car> listAvailableCars() {
        return carRepository.findByStatus("IDLE");
    }

    public List<Car> viewAllCars() {
        return carRepository.findAll();
    }

    public Lease startLease(Long customerId, Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!"IDLE".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("Car is not available for lease");
        }

        long activeLeaseCount = leaseRepository.findByCustomerId(customerId).stream()
                .filter(l -> l.getEndDate() == null)
                .count();

        if (activeLeaseCount >= 2) {
            throw new RuntimeException("Customer already has 2 active leases");
        }

        car.setStatus("ON_LEASE");
        carRepository.save(car);

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        lease.setStartDate(LocalDate.now());
        return leaseRepository.save(lease);
    }

    public Lease endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        lease.setEndDate(LocalDate.now());
        lease.getCar().setStatus("IDLE");
        carRepository.save(lease.getCar());

        return leaseRepository.save(lease);
    }

    public List<Lease> getCustomerHistory(Long customerId) {
        return leaseRepository.findByCustomerId(customerId);
    }
}
