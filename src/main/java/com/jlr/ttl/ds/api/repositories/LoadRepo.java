package com.jlr.ttl.ds.api.repositories;

import com.jlr.ttl.ds.api.dto.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepo
        extends JpaRepository<Load, String> {

}
