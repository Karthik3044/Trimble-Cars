package com.trimblecars.trimblecars.controller;

import com.trimblecars.trimblecars.entity.Car;
import com.trimblecars.trimblecars.entity.Lease;
import com.trimblecars.trimblecars.entity.User;
import com.trimblecars.trimblecars.service.AdminService;
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
class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @Test
    void getAllCarsReturnsListOfCars() {

        User owner1 = new User("Karthik", "karthik@gmail.com", "OWNER");
        User owner2 = new User("Sam", "sam@gmail.com", "OWNER");

        List<Car> cars = List.of(new Car("Toyota","TN24j8769JK", "IDLE",owner1 ), new Car("Honda","Tn45j8769JK","IDLE", owner2));
        Mockito.when(adminService.getAllCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = adminController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void getAllCarsReturnsEmptyListWhenNoCarsExist() {
        Mockito.when(adminService.getAllCars()).thenReturn(List.of());

        ResponseEntity<List<Car>> response = adminController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void getAllLeasesReturnsListOfLeases() {
        List<Lease> leases = List.of(new Lease(1, "Lease1"), new Lease(2, "Lease2"));
        Mockito.when(adminService.getAllLeases()).thenReturn(leases);

        ResponseEntity<List<Lease>> response = adminController.getAllLeases();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leases, response.getBody());
    }

    @Test
    void getAllLeasesReturnsEmptyListWhenNoLeasesExist() {
        Mockito.when(adminService.getAllLeases()).thenReturn(List.of());

        ResponseEntity<List<Lease>> response = adminController.getAllLeases();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void getAllUsersReturnsListOfUsers() {
        List<User> users = List.of(new User(1, "John"), new User(2, "Jane"));
        Mockito.when(adminService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = adminController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void getAllUsersReturnsEmptyListWhenNoUsersExist() {
        Mockito.when(adminService.getAllUsers()).thenReturn(List.of());

        ResponseEntity<List<User>> response = adminController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}