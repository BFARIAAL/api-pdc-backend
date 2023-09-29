package com.jlr.ttl.ds.api.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(name="id")
    private Integer vin;
    @Column(name = "loc_code")
    private String loc;

    public Vehicle(Integer vin, String loc_code) {
        this.vin = vin;
        this.loc = loc_code;
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
}
