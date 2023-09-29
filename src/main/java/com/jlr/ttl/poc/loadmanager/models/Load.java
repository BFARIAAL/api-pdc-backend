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
@Table(name = "loads")
public class Load {

    @Id
    @Column(name = "id")
    private String loadRef;

    private String depLoc;

    private String destLoc;

    private String status;

    private Carrier carrier;

    @Column(name = "end_date")
    private Date endDate;

}
