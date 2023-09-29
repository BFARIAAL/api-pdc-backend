package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Carrier;
import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.repositories.CarrierRepo;
import com.jlr.ttl.poc.loadmanager.repositories.LoadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierService {

    @Autowired
    private final CarrierRepo carrierRepo;

    public CarrierService(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    public List<Carrier> getAllCarriersService(CarrierRepo carrierRepo) {
        return carrierRepo.findAll();
    }
}
