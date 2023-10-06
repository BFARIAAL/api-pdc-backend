package com.jlr.ttl.ds.api.repositories;

import com.jlr.ttl.ds.api.dto.table.LocationsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository
        extends JpaRepository<LocationsTable, String> {
}
