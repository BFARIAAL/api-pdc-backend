package com.jlr.ttl.poc.loadmanager.repositories;

import com.jlr.ttl.poc.loadmanager.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo
        extends JpaRepository<Location, String> {

}
