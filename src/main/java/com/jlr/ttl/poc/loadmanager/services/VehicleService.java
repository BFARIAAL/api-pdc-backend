package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Vehicle;
import com.jlr.ttl.poc.loadmanager.repositories.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private final VehicleRepo vehicleRepo;

    public VehicleService(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    public void addVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleByVin = vehicleRepo.findById(vehicle.getVin());
        if (vehicleByVin.isPresent()) {
            throw new IllegalStateException();
        }
        vehicleRepo.save(vehicle);
    }

    public void deleteVehicle(String vin) {
       vehicleRepo.findById(vin)
                .orElseThrow(IllegalStateException::new);
       vehicleRepo.deleteById(vin);
    }

    public Vehicle findVehicleByVin(String vin) {
        return vehicleRepo.findById(vin)
                .orElseThrow(IllegalStateException::new);
    }

    public List<Vehicle> findVehiclesByLoc(String loc) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleRepo.findVehiclesByLoc(loc);
        // TODO: Validate loc from locations table
//        if () {
//            throw new IllegalStateException();
//        }
        return vehicles;
    }

//    public void updateVehicleService(String vin, Vehicle vehicle) {
//        Vehicle existingVehicle = vehicleRepo.findById(vin)
//                .orElseThrow(IllegalStateException::new);
//
//        existingVehicle.setLoc_code(vehicle.getLoc_code());
//        vehicleRepo.save(existingVehicle);
//    }

}
