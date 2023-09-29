package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrier")
public class Carrier {

    @Id
    @Column(name = "id")
    private String carrier_ref;

    @Column
    private String description;

    public Carrier() {
    }

    public Carrier(String carrier_ref) {
        this.carrier_ref = carrier_ref;
    }

    public Carrier(String carrier_ref, String description) {
        this.carrier_ref = carrier_ref;
        this.description = description;
    }

    public String getCarrier_ref() {
        return carrier_ref;
    }

    public String getDescription() {
        return description;
    }

    public void setCarrier_ref(String carrier_ref) {
        this.carrier_ref = carrier_ref;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
