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
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> response = vehicleService.getAllVehicles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-vin/{vin}")
    public ResponseEntity<?> indexVehicle(@PathVariable(value = "vin") String vin) {
        try {
            return ResponseEntity.ok(vehicleService.findVehicleByVin(vin));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle not found!");
        }
    }

    @GetMapping("/by-loc/{locCode}")
    public ResponseEntity<?> findVehiclesByLoc(@PathVariable(value = "locCode") String locCode) {

        try {
            return ResponseEntity.ok(vehicleService.findVehiclesByLoc(locCode));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Illegal location!");
        }
    }

    @PostMapping("/add-vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
            try {
                vehicleService.addVehicle(vehicle);
                return ResponseEntity.ok("Vehicle added successfully");
            } catch (IllegalStateException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Vehicle existed!");
            }
    }

    @DeleteMapping("/del-vehicle/{vin}")
    public ResponseEntity<String> deleteVehicle(@PathVariable(value = "vin") String vin) {
            try {
                vehicleService.deleteVehicle(vin);
                return ResponseEntity.ok("Vehicle deleted successfully");
            } catch (IllegalStateException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehicle not found!");
            }

    }

//    @PutMapping("/edit-vehicle/{vin}")
//    public ResponseEntity<String> updateVehicle(@PathVariable(name = "vin") String vin, @RequestBody Vehicle vehicle) {
//            try {
//                vehicleService.updateVehicleService(vin, vehicle);
//                return ResponseEntity.ok("Vehicle updated successfully");
//            } catch (IllegalStateException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("Vehicle not found!");
//            }
//    }

}