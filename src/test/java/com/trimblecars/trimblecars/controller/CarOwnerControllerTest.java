package com.trimblecars.trimblecars.controller;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.service.CarOwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarOwnerControllerTest {

    @Mock
    private CarOwnerService carOwnerService;

    @InjectMocks
    private CarOwnerController carOwnerController;

    @Test
    void registerCarReturnsRegisteredCar() {
        Car car = new Car(1L, "Toyota");
        Mockito.when(carOwnerService.registerCar(car)).thenReturn(car);

        ResponseEntity<Car> response = carOwnerController.registerCar(car);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void getOwnerCarsReturnsListOfCars() {
        List<Car> cars = List.of(new Car(1L, "Toyota"), new Car(2L, "Honda"));
        Mockito.when(carOwnerService.getOwnerCars(1L)).thenReturn(cars);

        ResponseEntity<List<Car>> response = carOwnerController.getOwnerCars(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void getOwnerCarsReturnsEmptyListWhenNoCarsExist() {
        Mockito.when(carOwnerService.getOwnerCars(1L)).thenReturn(List.of());

        ResponseEntity<List<Car>> response = carOwnerController.getOwnerCars(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void getCarStatusReturnsCarDetails() {
        Car car = new Car(1L, "Toyota");
        Mockito.when(carOwnerService.getCarStatus(1L, 1L)).thenReturn(car);

        ResponseEntity<Car> response = carOwnerController.getCarStatus(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void getLeaseHistoryReturnsListOfLeases() {
        List<Lease> leases = List.of(new Lease(1, "Lease1"), new Lease(2, "Lease2"));
        Mockito.when(carOwnerService.getLeaseHistory(1L)).thenReturn(leases);

        ResponseEntity<List<Lease>> response = carOwnerController.getLeaseHistory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leases, response.getBody());
    }

    @Test
    void getLeaseHistoryReturnsEmptyListWhenNoLeasesExist() {
        Mockito.when(carOwnerService.getLeaseHistory(1L)).thenReturn(List.of());

        ResponseEntity<List<Lease>> response = carOwnerController.getLeaseHistory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}