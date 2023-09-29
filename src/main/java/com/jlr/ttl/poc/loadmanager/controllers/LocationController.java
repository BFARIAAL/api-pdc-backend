package com.jlr.ttl.poc.loadmanager.controllers;

import com.jlr.ttl.poc.loadmanager.models.Location;
import com.jlr.ttl.poc.loadmanager.repositories.LocationRepo;
import com.jlr.ttl.poc.loadmanager.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/load_management/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationRepo locationRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> response = locationService.getAllLocationService();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find_loc/{loc_code}")
    public ResponseEntity<?> findLocation(@PathVariable(value = "loc_code") String loc_code) {
        try {
            return ResponseEntity.ok(locationService.findLocationService(loc_code));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Location not existed!");
        }
    }

    @PostMapping("/add-loc")
    public ResponseEntity<String> addNewLocation(@RequestBody Location newLoc) {
        try {
            locationService.addNewLocService(newLoc);
            return ResponseEntity.ok("Location added");
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Location existed!");
        }
    }

    @DeleteMapping("/del-loc/{loc_code}")
    public ResponseEntity<String> delLocation(@PathVariable(value = "loc_code") String loc_code) {
        try {
            locationService.deleteLocService(loc_code);
            return ResponseEntity.ok("Location deleted");
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Location not existed!");
        }
    }

}
