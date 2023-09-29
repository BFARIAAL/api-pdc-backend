package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.LoadStatus;
import com.jlr.ttl.poc.loadmanager.services.LoadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/load_status")
public class Load_statusController {

    @Autowired
    private LoadStatusService load_statusService;

    @GetMapping
    List<LoadStatus> getAllLoadStatus() {
        return load_statusService.getAllLoadStatus();
    }

    @PostMapping("/add-load_status")
    public ResponseEntity<String> addLoadStatus(@RequestBody LoadStatus newLoadStatus) {
        try {
            load_statusService.addLoadStatus(newLoadStatus);
            return ResponseEntity.ok("Load_status added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("load_status " + newLoadStatus.getStatusCode() + " existed!");
        }
    }
}
