package com.jlr.ttl.ds.api.repositories;

import com.jlr.ttl.ds.api.dto.table.DSUsersTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DSUserRepository extends JpaRepository<DSUsersTable, String> {
}
