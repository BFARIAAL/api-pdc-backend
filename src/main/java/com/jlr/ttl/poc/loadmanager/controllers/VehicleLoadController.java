package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.VehicleLoad;
import com.jlr.ttl.poc.loadmanager.services.VehicleLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle_loads")
public class VehicleLoadController {

    @Autowired
    private VehicleLoadService vehicleLoadService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleLoad>> getAllVehicleLoads() {
        List<VehicleLoad> response = vehicleLoadService.getAllVehicleLoads();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find_vehicle_load/{id}")
    public ResponseEntity<?> indexVehicleLoad(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(vehicleLoadService.findVehicleLoad(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle_load " + id + " not existing");
        }
    }

    @PostMapping("/add-vehicle_load")
    public ResponseEntity<String> addVehicleLoad(@RequestBody VehicleLoad newVehicleLoad) {
        try {
            vehicleLoadService.addVehicleLoad(newVehicleLoad);
            return ResponseEntity.ok("Vehicle_load added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Vehicle_load " + newVehicleLoad.getVehicleId() + " existed!");
        }
    }

}
