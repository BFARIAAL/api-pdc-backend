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

    public Location(String loc_code, String description) {
        this.loc_code = loc_code;
        this.description = description;
    }

    public Location(String loc_code, Date date) {
        this.loc_code = loc_code;
        this.date = date;
    }

    public Location(String loc_code, String description, Date date) {
        this.loc_code = loc_code;
        this.description = description;
        this.date = date;
    }

    public String getLoc_code() {
        return loc_code;
    }

    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
