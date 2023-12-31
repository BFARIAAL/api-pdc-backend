package com.jlr.ttl.ds.api.util.mapper;

import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.request.LocationRequest;
import com.jlr.ttl.ds.api.dto.response.LocationResponse;

public class LocationValueMapper implements DSValueMapper<Location, LocationResponse, LocationRequest> {

    public static Location responseToEntity(LocationResponse dsLocationResponse){
        return new Location(dsLocationResponse.getId(), dsLocationResponse.getLocType(), dsLocationResponse.getDescription(), dsLocationResponse.getEndDate(), dsLocationResponse.getCountry());
    }

    public static LocationResponse entityToResponse(Location location){
        return new LocationResponse(location.getId(), location.getLocType(), location.getDescription(), location.getEndDate(), location.getCountry());
    }
}
