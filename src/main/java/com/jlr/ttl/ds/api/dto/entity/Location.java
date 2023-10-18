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
public class Location implements Entity<Location> {

    private String id;
    private String locType;
    private String description;
    private Date endDate;
    private Integer country;
}
