package com.jlr.ttl.poc.loadmanager.services;

import com.jlr.ttl.poc.loadmanager.models.LoadStatus;
import com.jlr.ttl.poc.loadmanager.repositories.LoadStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoadStatusService {

    @Autowired
    private final LoadStatusRepo loadStatusRepo;

    public LoadStatusService(LoadStatusRepo loadStatusRepo) {
        this.loadStatusRepo = loadStatusRepo;
    }

    public List<LoadStatus> getAllLoadStatus() {
        return loadStatusRepo.findAll();
    }

    public void addLoadStatus(LoadStatus newLoadStatus) {
        Optional<LoadStatus> loadStatus = loadStatusRepo.findById(newLoadStatus.getStatusCode());
        if (loadStatus.isPresent()) {
            throw new IllegalStateException();
        }
        loadStatusRepo.save(newLoadStatus);
    }

}
