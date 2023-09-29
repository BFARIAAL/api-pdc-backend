package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Vehicle_Load;
import com.jlr.ttl.poc.loadmanager.repositories.Vehicle_loadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Vehicle_loadService {

    @Autowired
    private final Vehicle_loadRepo vehicle_loadRepo;

    public Vehicle_loadService(Vehicle_loadRepo vehicle_loadRepo) {
        this.vehicle_loadRepo = vehicle_loadRepo;
    }

    public List<Vehicle_Load> getAllVehicle_loads() { return vehicle_loadRepo.findAll(); }

    public Vehicle_Load findVehicle_load(Integer id) {
        return vehicle_loadRepo.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public void addVehicle_load(Vehicle_Load newVehicle_load) {
        Optional<Vehicle_Load> vl = vehicle_loadRepo.findById(newVehicle_load.getVehicleId());
        if (vl.isPresent()) {
            throw new IllegalStateException();
        }
        vehicle_loadRepo.save(newVehicle_load);
    }

}
