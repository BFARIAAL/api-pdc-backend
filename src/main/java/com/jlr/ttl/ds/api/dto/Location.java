package com.jlr.ttl.ds.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    private String loc_code;

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
