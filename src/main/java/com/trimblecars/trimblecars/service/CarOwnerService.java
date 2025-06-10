package com.trimblecars.trimblecars.service;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.entity.User;
import com.trimblecars.trimblecars.repository.CarRepository;
import com.trimblecars.trimblecars.repository.LeaseRepository;
import com.trimblecars.trimblecars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarOwnerService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private UserRepository userRepository;

    public Car registerCar(Car car) {
        car.setStatus("IDLE");
        userRepository.save(car.getOwner());
        return carRepository.save(car);
    }

    public List<Car> getOwnerCars(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }

    public Car getCarStatus(Long ownerId, Long carId) {
        return carRepository.findByIdAndOwnerId(carId, ownerId)
                .orElseThrow(() -> new RuntimeException("Car not found for owner"));
    }

    public List<Lease> getLeaseHistory(Long ownerId) {
        List<Car> cars = carRepository.findByOwnerId(ownerId);
        return cars.stream()
                .flatMap(car -> leaseRepository.findByCarId(car.getId()).stream())
                .collect(Collectors.toList());
    }

}
