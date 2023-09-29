package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/loads")
public class LoadController {

    @Autowired
    private LoadService loadService;


    @GetMapping("/all")
    public List<Load> getAllLoads() {
        return loadService.getAllLoads();
    }

    @GetMapping("/find-load")
    public ResponseEntity<?> indexLoad(@PathVariable(value = "loadRef") String loadRef) {
        try {
            return ResponseEntity.ok(loadService.findLoad(loadRef));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Load " + loadRef + " not found");
        }
    }

    @PostMapping("/add-load")
    public ResponseEntity<String> addLoad(@RequestBody Load newLoad) {
        try {
            loadService.addLoad(newLoad);
            return ResponseEntity.ok("Load added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Load " + newLoad.getLoadRef() + " existed!");
        }
    }


}