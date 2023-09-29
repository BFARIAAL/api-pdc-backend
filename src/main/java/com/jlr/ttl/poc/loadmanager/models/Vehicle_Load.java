package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vehicle_loads")
public class Vehicle_Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "veh")
    private Integer vehicle_id;

    @Column
    private String load_ref;

    @Column(name = "end_date")
    private Date date;

    public Vehicle_Load() {
    }

    public Vehicle_Load(Integer id, Integer vehicle_id, String load_ref) {
        this.id = id;
        this.vehicle_id = vehicle_id;
        this.load_ref = load_ref;
    }

    public Vehicle_Load(Integer id, Integer vehicle_id, String load_ref, Date date) {
        this.id = id;
        this.vehicle_id = vehicle_id;
        this.load_ref = load_ref;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public String getLoad_ref() {
        return load_ref;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
