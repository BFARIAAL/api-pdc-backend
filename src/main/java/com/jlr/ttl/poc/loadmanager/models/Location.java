package com.jlr.ttl.poc.loadmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "id")
    private String loc_code;

    @Column
    private String description;

    @Column(name = "end_date")
    private Date date;

    public Location() {
    }

    public Location(String loc_code) {
        this.loc_code = loc_code;
    }

    public String getLoc_code() {
        return loc_code;
    }

    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }
}
