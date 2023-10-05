package com.jlr.ttl.ds.api.repositories;


import com.jlr.ttl.ds.api.dto.table.VehiclesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<VehiclesTable, String> {
}
