package com.jlr.ttl.poc.loadmanager.controllers;

import com.jlr.ttl.poc.loadmanager.models.Location;
import com.jlr.ttl.poc.loadmanager.models.Vehicle;
import com.jlr.ttl.poc.loadmanager.repositories.LocationRepo;
import com.jlr.ttl.poc.loadmanager.repositories.VehicleRepo;
import com.jlr.ttl.poc.loadmanager.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/load_management/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationRepo locationRepo;

    /*@GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> response = locationService.getAllLoctionService();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
}
