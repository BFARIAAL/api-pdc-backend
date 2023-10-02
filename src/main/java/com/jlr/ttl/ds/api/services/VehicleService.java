package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehiclesService() {
        return vehicleRepository.findAll();
    }

    public void addNewVehicleService(Vehicle vehicle) {
        Optional<Vehicle> vehicleByVin = vehicleRepository.findById(vehicle.getVin().toString());
        if (vehicleByVin.isPresent()) {
            throw new IllegalStateException();
        }
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicleService(String vin) {
       vehicleRepository.findById(vin)
                .orElseThrow(IllegalStateException::new);
       vehicleRepository.deleteById(vin);
    }

    public Vehicle findVehicleByVinService(String vin) {
        return vehicleRepository.findById(vin)
                .orElseThrow(IllegalStateException::new);
    }

    public List<Vehicle> findVehiclesByLocService(String loc) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleRepository.findVehiclesByLoc(loc);
        // TODO: Validate loc from locations table
//        if () {
//            throw new IllegalStateException();
//        }
        return vehicles;
    }

    public void updateVehicleService(String vin, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(vin)
                .orElseThrow(IllegalStateException::new);

        existingVehicle.setLoc_code(vehicle.getLoc_code());
        vehicleRepository.save(existingVehicle);
    }

}
