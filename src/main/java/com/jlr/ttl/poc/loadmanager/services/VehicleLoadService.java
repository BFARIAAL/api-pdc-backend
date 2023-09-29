package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.VehicleLoad;
import com.jlr.ttl.poc.loadmanager.repositories.VehicleLoadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleLoadService {

    @Autowired
    private final VehicleLoadRepo vehicleLoadRepo;

    public VehicleLoadService(VehicleLoadRepo vehicleLoadRepo) {
        this.vehicleLoadRepo = vehicleLoadRepo;
    }

    public List<VehicleLoad> getAllVehicleLoads() { return vehicleLoadRepo.findAll(); }

    public VehicleLoad findVehicleLoad(Integer id) {
        return vehicleLoadRepo.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public void addVehicleLoad(VehicleLoad newVehicleLoad) {
        Optional<VehicleLoad> vl = vehicleLoadRepo.findById(newVehicleLoad.getVehicleId());
        if (vl.isPresent()) {
            throw new IllegalStateException();
        }
        vehicleLoadRepo.save(newVehicleLoad);
    }

}
