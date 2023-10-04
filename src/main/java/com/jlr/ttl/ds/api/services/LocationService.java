package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.repositories.LocationRepository;
import com.jlr.ttl.ds.api.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    //get all of the locations
    public List<Location> getAllLocationService() {
        return locationRepository.findAll();
    }
    public Location getLocationByID(String id) {
        return locationRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }


}