package com.jlr.ttl.ds.api.controllers;


import com.jlr.ttl.ds.api.dto.Load;
import com.jlr.ttl.ds.api.repositories.LoadRepo;
import com.jlr.ttl.ds.api.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
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