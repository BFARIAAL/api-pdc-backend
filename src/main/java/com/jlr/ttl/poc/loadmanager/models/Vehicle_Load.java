package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle_loads")
public class Vehicle_Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "veh")
    private Integer vehicleId;

    private String loadRef;

    @Column(name = "end_date")
    private Date endDate;

}
