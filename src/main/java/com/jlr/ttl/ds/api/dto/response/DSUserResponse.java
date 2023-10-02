package com.jlr.ttl.ds.api.dto.response;

import com.jlr.ttl.ds.api.dto.entity.DSUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DSUserResponse implements DSResponseInterface<DSUser> {

    private String id;
    private String description;
    private Date lastUpdated;
    private Date endDate;
    private String user1Id;
    private Character scanAdminFlag;
    private Character scanUserFlag;
    private Integer privilege;
    private String defaultLocation;
    private String defaultBrand;
    private Integer defaultCarrier;
    private Integer userGroup;
    private Date lastAccessed;
    private String defaultLanguage;
    private String password;
}
