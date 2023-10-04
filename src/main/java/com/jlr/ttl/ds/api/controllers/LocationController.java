package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.repositories.LocationRepository;
import com.jlr.ttl.ds.api.repositories.VehicleRepository;
import com.jlr.ttl.ds.api.services.LocationService;
import com.jlr.ttl.ds.api.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/load_management/locations")
@CrossOrigin("http://localhost:4200")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> response = locationService.getAllLocationService();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/byid/{lId}")
    public ResponseEntity<?> indexVehicle(@PathVariable(value = "lId") String lId) {
        try {
            return ResponseEntity.ok(locationService.getLocationByID(lId).getlDescription());
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Location Doesn't Exist!");
        }
    }

}