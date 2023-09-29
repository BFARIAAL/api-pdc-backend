package com.jlr.ttl.poc.loadmanager.controllers;

import com.jlr.ttl.poc.loadmanager.models.Location;
import com.jlr.ttl.poc.loadmanager.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> response = locationService.getAllLocations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find_loc/{locCode}")
    public ResponseEntity<?> findLocation(@PathVariable(value = "locCode") String locCode) {
        try {
            return ResponseEntity.ok(locationService.findLocation(locCode));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Location " + locCode + " not found");
        }
    }
}
