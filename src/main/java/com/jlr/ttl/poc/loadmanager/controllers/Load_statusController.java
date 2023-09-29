package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Load_status;
import com.jlr.ttl.poc.loadmanager.services.Load_statusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/load_status")
public class Load_statusController {

    @Autowired
    private Load_statusService load_statusService;

    @GetMapping
    List<Load_status> getAllLoad_status() {
        return load_statusService.getAllLoad_status();
    }

    @PostMapping("/add-load_status")
    public ResponseEntity<String> addLoad_status(@RequestBody Load_status newLoad_status) {
        try {
            load_statusService.addLoad_status(newLoad_status);
            return ResponseEntity.ok("Load_status added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Load_status " + newLoad_status.getStatusCode() + " existed!");
        }
    }
}
