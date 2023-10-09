package com.jlr.ttl.ds.api.dto.response;

import com.jlr.ttl.ds.api.dto.entity.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationTypeResponse implements DSResponseInterface<LocationType> {

    private String id;
    private String description;
    private Date endDate;
}
