package com.jlr.ttl.poc.loadmanager.repositories;

import com.jlr.ttl.poc.loadmanager.models.VehicleLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleLoadRepo
        extends JpaRepository<VehicleLoad, Integer> {
}
