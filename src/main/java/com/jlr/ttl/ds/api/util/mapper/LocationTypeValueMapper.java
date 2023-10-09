package com.jlr.ttl.ds.api.util.mapper;

public class LocationTypeValueMapper implements DSValueMapper<LocationType, LocationTypeResponse, LocationTypeRequest> {

    public static LocationType responseToEntity(LocationTypeResponse dsLocationTypeResponse){
        return new LocationType(dsLocationTypeResponse.getId(), dsLocationTypeResponse.getDescription(), dsLocationTypeResponse.getEndDate());
    }

    public static LocationTypeResponse entityToResponse(LocationType locType){
        return new LocationTypeResponse(locType.getId(), locType.getDescription(), locType.getEndDate());
    }
}