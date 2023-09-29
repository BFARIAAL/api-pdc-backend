package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Vehicle_Load;
import com.jlr.ttl.poc.loadmanager.services.Vehicle_loadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle_loads")
public class Vehicle_loadController {

    @Autowired
    private Vehicle_loadService vehicle_loadService;

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle_Load>> getAllVehicle_loads() {
        List<Vehicle_Load> response = vehicle_loadService.getAllVehicle_loads();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find_vehicle_load/{id}")
    public ResponseEntity<?> indexVehicle_load(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(vehicle_loadService.findVehicle_load(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle_load " + id + " not existing");
        }
    }

    @PostMapping("/add-vehicle_load")
    public ResponseEntity<String> addVehicle_load(@RequestBody Vehicle_Load newVehicle_load) {
        try {
            vehicle_loadService.addVehicle_load(newVehicle_load);
            return ResponseEntity.ok("Vehicle_load added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Vehicle_load " + newVehicle_load.getVehicleId() + " existed!");
        }
    }

}
