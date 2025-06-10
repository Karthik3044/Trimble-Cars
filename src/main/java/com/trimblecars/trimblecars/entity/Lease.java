package com.trimblecars.trimblecars.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User customer;

    private LocalDate startDate;
    private LocalDate endDate;

    public Lease(Long id, LocalDate endDate, LocalDate startDate, User customer, Car car) {
        this.id = id;
        this.endDate = endDate;
        this.startDate = startDate;
        this.customer = customer;
        this.car = car;
    }

    public Lease() {
    }

    public Lease(int i, String lease1) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
// Getters and Setters
}
