package com.jlr.ttl.poc.loadmanager.services;


import com.jlr.ttl.poc.loadmanager.models.Location;
import com.jlr.ttl.poc.loadmanager.repositories.LocationRepo;
import com.jlr.ttl.poc.loadmanager.repositories.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@Service
public class LocationService {

    @Autowired
    private final LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    /*public List<Location> getAllLocationService() {
        
    };*/
}
