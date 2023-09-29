package com.jlr.ttl.ds.api.services;


import com.jlr.ttl.ds.api.repositories.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
