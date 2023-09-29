package com.jlr.ttl.poc.loadmanager.services;


import com.jlr.ttl.poc.loadmanager.models.Location;
import com.jlr.ttl.poc.loadmanager.repositories.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private final LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public List<Location> getAllLocations() { return locationRepo.findAll(); }

    public Location findLocation(String locCode) {
        return locationRepo.findById(locCode).
                orElseThrow(IllegalStateException::new);
    }

}
