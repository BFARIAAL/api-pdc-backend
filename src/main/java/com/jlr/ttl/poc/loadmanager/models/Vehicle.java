package com.jlr.ttl.poc.loadmanager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    private String vin;
    @Column(name = "loc_code")
    private String loc;

    public Vehicle(String vin, String loc_code) {
        this.vin = vin;
        this.loc = loc_code;
    }

    public Vehicle() {
    }

    public Vehicle(String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    public String getLoc_code() {
        return loc;
    }

    public void setLoc_code(String loc_code) {
        this.loc = loc_code;
    }
}
