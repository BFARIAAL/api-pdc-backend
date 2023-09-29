package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.Load_status;
import com.jlr.ttl.poc.loadmanager.repositories.Load_statusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Load_statusService {

    @Autowired
    private final Load_statusRepo load_statusRepo;

    public Load_statusService(Load_statusRepo load_statusRepo) {
        this.load_statusRepo = load_statusRepo;
    }

    public List<Load_status> getAllLoad_status() {
        return load_statusRepo.findAll();
    }

    public void addLoad_status(Load_status newLoad_status) {
        Optional<Load_status> load_status = load_statusRepo.findById(newLoad_status.getStatusCode());
        if (load_status.isPresent()) {
            throw new IllegalStateException();
        }
        load_statusRepo.save(newLoad_status);
    }

}
