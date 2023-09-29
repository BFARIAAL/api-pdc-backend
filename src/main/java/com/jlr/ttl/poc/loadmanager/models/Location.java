package com.jlr.ttl.poc.loadmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "id")
    private String locCode;

    @Column
    private String description;

    @Column(name = "end_date")
    private Date endDate;

}
