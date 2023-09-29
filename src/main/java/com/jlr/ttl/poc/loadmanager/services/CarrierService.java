package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Carrier;
import com.jlr.ttl.poc.loadmanager.repositories.CarrierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {

    @Autowired
    private final CarrierRepo carrierRepo;

    public CarrierService(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    public List<Carrier> getAllCarriers() {
        return carrierRepo.findAll();
    }

    public void addCarrier(Carrier newCarrier) {
        Optional<Carrier> carrier = carrierRepo.findById(newCarrier.getCarrierRef());
        if (carrier.isPresent()) {
            throw new IllegalStateException();
        }
        carrierRepo.save(newCarrier);
    }
}
