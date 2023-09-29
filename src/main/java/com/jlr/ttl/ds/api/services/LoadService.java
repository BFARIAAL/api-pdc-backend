package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.dto.Load;
import com.jlr.ttl.ds.api.repositories.LoadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadService {

    @Autowired
    private final LoadRepo loadRepo;

    public LoadService(LoadRepo loadRepo) {
        this.loadRepo = loadRepo;
    }

    public List<Load> getAllLoadsService(LoadRepo loadRepo) {
        return loadRepo.findAll();
    }
}
