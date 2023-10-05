package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LocationService {

    @Autowired
    private final LocationRepository locationRepository;




    //get all of the locations
    public List<Location> getAllLocationService() {
        return locationRepository.findAll();
    }
    public Location getLocationByID(String id) {
        return locationRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }


}