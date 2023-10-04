package com.jlr.ttl.ds.api.repositories;


import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository
        extends JpaRepository<Location, String> {


}
