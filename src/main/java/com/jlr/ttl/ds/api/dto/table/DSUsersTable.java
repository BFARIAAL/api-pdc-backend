package com.jlr.ttl.ds.api.dto.table;

import com.jlr.ttl.ds.api.dto.entity.DSUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "ds_users")
public class DSUsersTable implements DSTableInterface<DSUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "user_1_id")
    private String user1Id;

    @Column(name = "scan_admin")
    private Character scanAdminFlag;

    @Column(name = "scan_user")
    private Character scanUserFlag;

    @Column(name = "privilege")
    private Integer privilege;

    @Column(name = "default_location")
    private String defaultLocation;

    @Column(name = "default_brand")
    private String defaultBrand;

    @Column(name = "default_carrier")
    private Integer defaultCarrier;

    @Column(name = "user_group")
    private Integer userGroup;

    @Column(name = "last_accessed")
    private Date lastAccessed;

    @Column(name = "default_language")
    private String defaultLanguage;

    @Column(name = "password")
    private String password;

    @Override
    public DSUser createEntity() {
        return new DSUser(id, description, lastUpdated, endDate, user1Id, scanAdminFlag, scanUserFlag, privilege,
                defaultLocation, defaultBrand, defaultCarrier, userGroup, lastAccessed, defaultLanguage, password);
    }
}
