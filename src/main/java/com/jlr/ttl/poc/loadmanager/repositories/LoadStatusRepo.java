package com.jlr.ttl.poc.loadmanager.repositories;

import com.jlr.ttl.poc.loadmanager.models.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadStatusRepo
        extends JpaRepository<LoadStatus, String> {
}
