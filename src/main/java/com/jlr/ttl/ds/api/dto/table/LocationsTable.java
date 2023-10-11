package com.jlr.ttl.ds.api.dto.table;

import com.jlr.ttl.ds.api.dto.entity.Location;
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
@Table(name = "locations")
public class LocationsTable implements DSTableInterface<Location>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "loc_type")
    private String locType;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private Date endDate;

    @Override
    public Location createEntity() {
        return new Location(id, locType, description, endDate);
    }
}
