package com.jlr.ttl.poc.loadmanager.controllers;

import com.jlr.ttl.poc.loadmanager.models.Vehicle;
import com.jlr.ttl.poc.loadmanager.repositories.VehicleRepo;
import com.jlr.ttl.poc.loadmanager.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/load_management/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepo vehicleRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> response = vehicleService.getAllVehiclesService();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-vin/{vin}")
    public ResponseEntity<?> indexVehicle(@PathVariable(value = "vin") String vin) {
        try {
            return ResponseEntity.ok(vehicleService.findVehicleByVinService(vin));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Vehicle not existing!");
        }
    }

    @GetMapping("/by-loc/{loc_code}")
    public ResponseEntity<?> findVehiclesByLoc(@PathVariable(value = "loc_code") String loc_code) {

        try {
            return ResponseEntity.ok(vehicleService.findVehiclesByLocService(loc_code));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Illegal location!");
        }
    }

    @PostMapping("/add-vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
            try {
                vehicleService.addNewVehicleService(vehicle);
                return ResponseEntity.ok("Vehicle added successfully");
            } catch (IllegalStateException e) {
                return ResponseEntity.ok("Vehicle already existed");
            }
    }

    @DeleteMapping("/del-vehicle/{vin}")
    public ResponseEntity<String> deleteVehicle(@PathVariable(value = "vin") String vin) {
            try {
                vehicleService.deleteVehicleService(vin);
                return ResponseEntity.ok("Vehicle deleted successfully");
            } catch (IllegalStateException e) {
                return ResponseEntity.ok("Vehicle not existed!");
            }

    }

    @PutMapping("/edit-vehicle/{vin}")
    public ResponseEntity<String> updateVehicle(@PathVariable(name = "vin") String vin, @RequestBody Vehicle vehicle) {
            try {
                vehicleService.updateVehicleService(vin, vehicle);
                return ResponseEntity.ok("Vehicle updated successfully");
            } catch (IllegalStateException e) {
                return ResponseEntity.ok("Vehicle not existed!");
            }
    }

}