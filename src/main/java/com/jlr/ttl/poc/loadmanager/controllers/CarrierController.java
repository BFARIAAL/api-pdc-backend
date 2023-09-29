package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Carrier;
import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.repositories.CarrierRepo;
import com.jlr.ttl.poc.loadmanager.repositories.LoadRepo;
import com.jlr.ttl.poc.loadmanager.services.CarrierService;
import com.jlr.ttl.poc.loadmanager.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/load_management/carriers")
public class CarrierController {
    @Autowired
    private CarrierService carrierService;
    @Autowired
    private CarrierRepo carrierRepo;

    @GetMapping("/all")
    public List<Carrier> getAllCarriers() {
        return carrierService.getAllCarriersService(carrierRepo);
    }

}
