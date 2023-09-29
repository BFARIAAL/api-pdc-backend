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


}
