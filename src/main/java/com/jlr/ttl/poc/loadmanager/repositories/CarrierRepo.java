package com.jlr.ttl.poc.loadmanager.repositories;


import com.jlr.ttl.poc.loadmanager.models.Carrier;
import com.jlr.ttl.poc.loadmanager.models.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepo
        extends JpaRepository<Carrier, String> {

}
