package com.jlr.ttl.ds.api.repositories;

import com.jlr.ttl.ds.api.dto.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo
        extends JpaRepository<Location, String> {

}
