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
public class LoadStatusController {

    @Autowired
    private LoadStatusService loadStatusService;

    @GetMapping
    List<LoadStatus> getAllLoadStatus() {
        return loadStatusService.getAllLoadStatus();
    }

    @PostMapping("/add-load_status")
    public ResponseEntity<String> addLoadStatus(@RequestBody LoadStatus newLoadStatus) {
        try {
            loadStatusService.addLoadStatus(newLoadStatus);
            return ResponseEntity.ok("Load_status added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("load_status " + newLoadStatus.getStatusCode() + " existed!");
        }
    }
}
