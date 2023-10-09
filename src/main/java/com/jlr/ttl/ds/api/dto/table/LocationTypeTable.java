package com.jlr.ttl.ds.api.dto.table;

import com.jlr.ttl.ds.api.dto.entity.LocationType;
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
@Table(name = "location_type")
public class LocationTypeTable implements DSTableInterface<LocationType>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String description;

    @Column(name = "end_date")
    private Date endDate;

    @Override
    public LocationType createEntity() {
        return new LocationType(id, description, endDate);
    }
}
