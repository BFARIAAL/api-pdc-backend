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

    public List<Location> getAllLocationService() { return locationRepo.findAll(); }

    public Location findLocationService(String loc_code) {
        return locationRepo.findById(loc_code).
                orElseThrow(IllegalStateException::new);
    }

    public void addNewLocService(Location newLoc) {
        Optional<Location> location = locationRepo.findById(newLoc.getLoc_code());
        if (location.isPresent()) {
            throw new IllegalStateException();
        }
        locationRepo.save(newLoc);
    }

    public void deleteLocService(String loc) {
        locationRepo.findById(loc)
                .orElseThrow(IllegalStateException::new);
        locationRepo.deleteById(loc);
    }
}
