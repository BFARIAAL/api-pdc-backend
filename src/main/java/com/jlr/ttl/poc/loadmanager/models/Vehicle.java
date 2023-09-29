package com.jlr.ttl.poc.loadmanager.models;

import jakarta.persistence.*;

 enum Status{
    INTRANS, DLVRD, INLOAD, BUILT
}

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(name="id")
    private Integer vin;
    @Column(name = "loc_code")
    private String loc;

    @Column(name = "status")
    private Status status;

    public Vehicle(Integer vin, String loc_code, Status status) {
        this.vin = vin;
        this.loc = loc_code;
        this.status = status;
    }

    public Vehicle() {
    }

    public Vehicle(Integer vin) {
        this.vin = vin;
    }

    public Integer getVin() {
        return vin;
    }

    public String getLoc_code() {
        return loc;
    }

    public void setLoc_code(String loc_code) {
        this.loc = loc_code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
