package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Carrier;
import com.jlr.ttl.poc.loadmanager.services.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carriers")
public class CarrierController {
    @Autowired
    private CarrierService carrierService;

    @GetMapping("/all")
    public List<Carrier> getAllCarriers() {
        return carrierService.getAllCarriers();
    }

    @PostMapping("/add-carrier")
    public ResponseEntity<String> addNewCarrier(@RequestBody Carrier newCarrier) {
        try {
            carrierService.addCarrier(newCarrier);
            return ResponseEntity.ok("Carrier added");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Carrier " + newCarrier.getCarrierRef() + " existed!");
        }
    }

}
