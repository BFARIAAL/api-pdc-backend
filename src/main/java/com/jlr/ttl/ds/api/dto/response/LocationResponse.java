package com.jlr.ttl.ds.api.dto.response;

import com.jlr.ttl.ds.api.dto.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationResponse implements DSResponseInterface<Location> {

    private String id;
    private String description;
    private Date endDate;
}
