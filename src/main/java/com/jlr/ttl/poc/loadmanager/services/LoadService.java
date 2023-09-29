package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.repositories.LoadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoadService {

    @Autowired
    private final LoadRepo loadRepo;

    public LoadService(LoadRepo loadRepo) {
        this.loadRepo = loadRepo;
    }

    public List<Load> getAllLoads() {
        return loadRepo.findAll();
    }

    public void addLoad(Load newLoad) {
        Optional<Load> load = loadRepo.findById(newLoad.getLoadRef());
        if (load.isPresent()) {
            throw new IllegalStateException();
        }
        loadRepo.save(newLoad);
    }

    public Load findLoad(String load_ref) {
        return loadRepo.findById(load_ref).
                orElseThrow(IllegalStateException::new);
    }
}
