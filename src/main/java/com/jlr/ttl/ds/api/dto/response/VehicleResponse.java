package com.jlr.ttl.ds.api.dto.response;

import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VehicleResponse implements DSResponseInterface<Vehicle> {

    private String id;
    private String vin;
    private String locCode;
    private Date endDate;
    private String status;
    private LocationResponse locationResponse;
}
