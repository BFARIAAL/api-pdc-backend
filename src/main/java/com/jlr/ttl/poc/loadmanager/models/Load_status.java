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
@Table(name = "load_status")
public class Load_status {

    @Id
    @Column(name = "id")
    private String statusCode;

    private String description;

    @Column(name = "end_date")
    private Date endDate;

}
