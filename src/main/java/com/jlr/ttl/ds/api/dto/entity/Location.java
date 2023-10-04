package com.jlr.ttl.ds.api.dto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name="id")
    private String lId;
    @Column(name = "description")
    private String lDescription;

    public Location(String lId, String lDescription) {
        this.lId = lId;
        this.lDescription = lDescription;
    }

    public Location(){}
    public String getlId() {
        return lId;
    }

    public String getlDescription() {
        return lDescription;
    }
}
