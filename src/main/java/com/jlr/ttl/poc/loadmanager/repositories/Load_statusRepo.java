package com.jlr.ttl.poc.loadmanager.repositories;

import com.jlr.ttl.poc.loadmanager.models.Load;
import com.jlr.ttl.poc.loadmanager.models.Load_status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Load_statusRepo
        extends JpaRepository<Load_status, String> {
}
