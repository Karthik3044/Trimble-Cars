package com.trimblecars.trimblecars.controller;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.entity.User;
import com.trimblecars.trimblecars.service.CustomerService;
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
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void listAvailableCarsReturnsListOfCars() {
        List<Car> cars = List.of(new Car(1L, "Toyota"), new Car(2L, "Honda"));
        Mockito.when(customerService.listAvailableCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = customerController.listAvailableCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void listAvailableCarsReturnsEmptyListWhenNoCarsAvailable() {
        Mockito.when(customerService.listAvailableCars()).thenReturn(List.of());

        ResponseEntity<List<Car>> response = customerController.listAvailableCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void viewAllCarsReturnsListOfCars() {
        List<Car> cars = List.of(new Car(1L, "Toyota"), new Car(2L, "Honda"));
        Mockito.when(customerService.viewAllCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = customerController.viewAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void viewAllCarsReturnsEmptyListWhenNoCarsExist() {
        Mockito.when(customerService.viewAllCars()).thenReturn(List.of());

        ResponseEntity<List<Car>> response = customerController.viewAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void startLeaseReturnsLeaseDetails() {
        Lease lease = new Lease(1, "Lease1");
        Mockito.when(customerService.startLease(1L, 1L)).thenReturn(lease);

        ResponseEntity<Lease> response = customerController.startLease(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lease, response.getBody());
    }

    @Test
    void endLeaseReturnsUpdatedLeaseDetails() {
        Lease lease = new Lease(1, "Lease1");
        Mockito.when(customerService.endLease(1L)).thenReturn(lease);

        ResponseEntity<Lease> response = customerController.endLease(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lease, response.getBody());
    }

    @Test
    void getCustomerHistoryReturnsListOfLeases() {
        List<Lease> leases = List.of(new Lease(1, "Lease1"), new Lease(2, "Lease2"));
        Mockito.when(customerService.getCustomerHistory(1L)).thenReturn(leases);

        ResponseEntity<List<Lease>> response = customerController.getCustomerHistory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leases, response.getBody());
    }

    @Test
    void getCustomerHistoryReturnsEmptyListWhenNoLeasesExist() {
        Mockito.when(customerService.getCustomerHistory(1L)).thenReturn(List.of());

        ResponseEntity<List<Lease>> response = customerController.getCustomerHistory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void saveUserReturnsSavedUser() {
        User user = new User(1, "John");
        Mockito.when(customerService.saveUser(user)).thenReturn(user);

        ResponseEntity<User> response = customerController.saveUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
}