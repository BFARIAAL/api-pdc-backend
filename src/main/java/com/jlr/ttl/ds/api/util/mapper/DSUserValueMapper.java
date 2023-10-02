package com.jlr.ttl.ds.api.util.mapper;

import com.jlr.ttl.ds.api.dto.entity.DSUser;
import com.jlr.ttl.ds.api.dto.request.DSUserRequest;
import com.jlr.ttl.ds.api.dto.response.DSUserResponse;

public class DSUserValueMapper implements DSValueMapper<DSUser, DSUserResponse, DSUserRequest> {

public static DSUser responseToEntity(DSUserResponse dsUserResponse){
        return new DSUser(dsUserResponse.getId(),dsUserResponse.getDescription(),dsUserResponse.getLastUpdated(),
        dsUserResponse.getEndDate(),dsUserResponse.getUser1Id(),dsUserResponse.getScanAdminFlag(),
        dsUserResponse.getScanUserFlag(),dsUserResponse.getPrivilege(),dsUserResponse.getDefaultLocation(),
        dsUserResponse.getDefaultBrand(),dsUserResponse.getDefaultCarrier(),dsUserResponse.getUserGroup(),
        dsUserResponse.getLastAccessed(),dsUserResponse.getDefaultLanguage(),dsUserResponse.getPassword());
        }
public static DSUserResponse entityToResponse(DSUser dsUser){
        return new DSUserResponse(dsUser.getId(),dsUser.getDescription(),dsUser.getLastUpdated(),
        dsUser.getEndDate(),dsUser.getUser1Id(),dsUser.getScanAdminFlag(),
        dsUser.getScanUserFlag(),dsUser.getPrivilege(),dsUser.getDefaultLocation(),
        dsUser.getDefaultBrand(),dsUser.getDefaultCarrier(),dsUser.getUserGroup(),
        dsUser.getLastAccessed(),dsUser.getDefaultLanguage(), dsUser.getPassword());
        }
}