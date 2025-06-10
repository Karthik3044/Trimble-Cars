package com.trimblecars.trimblecars.dto;

import com.trimblecars.trimblecars.entity.User;

public class CarDto {

    private Long id;
    private String model;
    private String number;
    private String status; // IDLE, ON_LEASE, ON_SERVICE
    private User owner;

    public CarDto() {
    }

    public CarDto(Long id, String model, String number, String status, User owner) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.status = status;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
