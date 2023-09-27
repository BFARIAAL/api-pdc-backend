package com.jlr.ttl.poc.loadmanager.controllers;


import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.repositories.LoadRepo;
import com.jlr.ttl.poc.loadmanager.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/load_management/loads")
public class LoadController {

    @Autowired
    private LoadService loadService;
    @Autowired
    private LoadRepo loadRepo;

    @GetMapping
    public List<Load> getAllLoads() {
        return loadService.getAllLoadsService(loadRepo);
    }
}