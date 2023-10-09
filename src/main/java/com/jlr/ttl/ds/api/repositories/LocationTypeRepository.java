package com.jlr.ttl.ds.api.repositories;

import com.jlr.ttl.ds.api.dto.table.LocationTypeTable;
import com.jlr.ttl.ds.api.dto.table.LocationsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeRepository
        extends JpaRepository<LocationTypeTable, String> {
}
