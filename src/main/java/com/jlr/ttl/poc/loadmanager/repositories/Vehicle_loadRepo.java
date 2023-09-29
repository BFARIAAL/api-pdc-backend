package com.jlr.ttl.poc.loadmanager.repositories;

import com.jlr.ttl.poc.loadmanager.models.Vehicle_Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Vehicle_loadRepo
        extends JpaRepository<Vehicle_Load, Integer> {
}
