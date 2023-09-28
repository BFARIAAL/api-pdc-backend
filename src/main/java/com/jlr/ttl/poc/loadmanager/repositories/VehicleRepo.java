package com.jlr.ttl.poc.loadmanager.repositories;


import com.jlr.ttl.poc.loadmanager.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehicleRepo
        extends JpaRepository<Vehicle, String> {
    @Query("SELECT v FROM Vehicle v WHERE v.loc = :loc")
    List<Vehicle> findVehiclesByLoc(String loc);

}
