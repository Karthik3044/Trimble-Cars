package com.trimblecars.trimblecars.repository;

import com.trimblecars.trimblecars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus(String status);
    List<Car> findByOwnerId(Long ownerId);
    Optional<Car> findByIdAndOwnerId(Long id, Long ownerId);
}
