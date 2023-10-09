package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.annotation.TrackExecutionTime;
import com.jlr.ttl.ds.api.dto.table.DSTableInterface;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.LocationNotFoundException;
import com.jlr.ttl.ds.api.exception.data.LocationTypeNotFoundException;
import com.jlr.ttl.ds.api.repositories.LocationTypeRepository;
import com.jlr.ttl.ds.api.util.mapper.LocationTypeValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LocationTypeService {

    private LocationTypeRepository locationTypeRepository;

    /**
     * Getting all the location types from database.
     * @return a list of LocationTypeResponse
     */
    public List<LocationTypeResponse> getAllLocationTypes() throws ServiceBusinessException {
        try {
            List<LocationTypeTable> dbResponse = locationTypeRepository.findAll();
            List<LocationTypeResponse> listOfLocationTypeResponse = new ArrayList<LocationTypeResponse>();
            log.info("Retrieved data from database, transforming to response");
            for(LocationTypeTable l : dbResponse) {
                listOfLocationTypeResponse.add(LocationTypeValueMapper.entityToResponse(l.createEntity()));
            }
            return listOfLocationTypeResponse;
        } catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching locations");
        }
    }

    /**
     * Fetch the location type info by the id passed
     *
     * @return Location
     */
    @TrackExecutionTime
    public LocationTypeResponse getLocationTypeByID(String id) throws ServiceBusinessException {
        if(id==null || id.length()==0){
            String errorMessage = "No location type id was provided";
            log.warn(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            DSTableInterface<LocationType> dbResponse = locationTypeRepository.findById(id)
                    .orElseThrow(() -> new LocationNotFoundException("No location type with ID : " + id + " was found"));
            return LocationTypeValueMapper.entityToResponse(dbResponse.createEntity());
        }catch (LocationTypeNotFoundException locationTypeNotFoundException){
            String errorMessage = locationTypeNotFoundException.getMessage();
            log.warn(errorMessage);
            throw new LocationTypeNotFoundException(errorMessage);
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching location with id : " + id);
        }
    }
}
