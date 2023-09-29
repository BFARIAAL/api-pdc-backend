package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.repositories.LocationRepo;
import com.jlr.ttl.ds.api.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
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
