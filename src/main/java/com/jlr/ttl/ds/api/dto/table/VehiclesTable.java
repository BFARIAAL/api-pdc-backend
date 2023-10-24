package com.jlr.ttl.ds.api.dto.table;

import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "vehicles")
public class VehiclesTable implements DSTableInterface<Vehicle>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "short_vin")
    private String vin;

    @Column(name = "loc_code")
    private String locCode;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "order_number")
    private String orderNumber;

    @Override
    public Vehicle createEntity() {
        return new Vehicle(id, vin, locCode, endDate, status, orderNumber);
    }
}
