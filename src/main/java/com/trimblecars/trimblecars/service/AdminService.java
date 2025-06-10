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

@Service
public class AdminService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
