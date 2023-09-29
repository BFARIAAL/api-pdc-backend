package com.jlr.ttl.ds.api.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DSUser implements Entity<DSUser>{

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

    /**
     * This method is used to verify a user's credentials before sending them to the frontend
     * @param password The password introduced by the user
     * @return true if the user introduced the correct credentials, else false
     */
    public boolean validateCredentials(String password){
        return password.equals(this.password);
    }
}
