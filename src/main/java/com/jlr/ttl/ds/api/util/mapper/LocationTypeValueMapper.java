package com.jlr.ttl.ds.api.util.mapper;

import com.jlr.ttl.ds.api.dto.entity.LocationType;
import com.jlr.ttl.ds.api.dto.request.LocationTypeRequest;
import com.jlr.ttl.ds.api.dto.response.LocationTypeResponse;

public class LocationTypeValueMapper implements DSValueMapper<LocationType, LocationTypeResponse, LocationTypeRequest> {

    public static LocationType responseToEntity(LocationTypeResponse dsLocationTypeResponse){
        return new LocationType(dsLocationTypeResponse.getId(), dsLocationTypeResponse.getDescription(), dsLocationTypeResponse.getEndDate());
    }

    public static LocationTypeResponse entityToResponse(LocationType locType){
        return new LocationTypeResponse(locType.getId(), locType.getDescription(), locType.getEndDate());
    }
}