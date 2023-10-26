package com.jlr.ttl.ds.api.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vehicle implements Entity<Vehicle> {

    private String id;
    private String vin;
    private String locCode;
    private Date endDate;
}