package com.jlr.ttl.poc.loadmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {
    @Id
    @Column(name="id")
    private Integer vin;
    @Column(name = "loc_code")
    private String loc;

    @Column(name = "status")
    private String status;
}
