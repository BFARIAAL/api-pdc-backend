package com.jlr.ttl.ds.api.util.mapper;

import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.dto.request.VehicleRequest;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;

public class VehicleValueMapper implements DSValueMapper<Vehicle, VehicleResponse, VehicleRequest> {

    public static Vehicle responseToEntity(VehicleResponse dsVehicleResponse){
        return new Vehicle(dsVehicleResponse.getId(), dsVehicleResponse.getVin(),
                dsVehicleResponse.getLocCode(), dsVehicleResponse.getEndDate(),
                dsVehicleResponse.getStatus(), dsVehicleResponse.getOrderNumber());
    }

    public static VehicleResponse entityToResponse(Vehicle vehicle){
        return new VehicleResponse(vehicle.getId(), vehicle.getVin(),
                vehicle.getLocCode(), vehicle.getEndDate(),
                vehicle.getStatus(), null, vehicle.getOrderNumber());
    }

}
