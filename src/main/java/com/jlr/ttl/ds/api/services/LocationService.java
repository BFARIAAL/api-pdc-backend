package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.annotation.TrackExecutionTime;
import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.dto.response.LocationResponse;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;
import com.jlr.ttl.ds.api.dto.table.DSTableInterface;
import com.jlr.ttl.ds.api.dto.table.LocationsTable;
import com.jlr.ttl.ds.api.dto.table.VehiclesTable;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.LocationNotFoundException;
import com.jlr.ttl.ds.api.exception.data.VehicleNotFoundException;
import com.jlr.ttl.ds.api.repositories.LocationRepository;
import com.jlr.ttl.ds.api.util.mapper.LocationValueMapper;
import com.jlr.ttl.ds.api.util.mapper.VehicleValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LocationService {
    private LocationRepository locationRepository;




    /**
     * Getting all the locations from database.
     * @return a list of LocationResponse
     */
    public List<LocationResponse> getAllLocations() throws ServiceBusinessException {
        try {
            List<LocationsTable> dbResponse = locationRepository.findAll();

            List<LocationResponse> listOfLocationResponse = new ArrayList<LocationResponse>();
            log.info("Retrieved data from database, transforming to response");
            for(LocationsTable l : dbResponse) {
               listOfLocationResponse.add(LocationValueMapper.entityToResponse(l.createEntity()));
            }
            return listOfLocationResponse;
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching locations");
        }
    }







    /**
     * Fetch the location by the id passed
     *
     * @return Location
     */

    @TrackExecutionTime
    public LocationResponse getLocationByID(String id) throws ServiceBusinessException {
        if(id==null || id.length()==0){
            String errorMessage = "No location id was provided";
            log.warn(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            DSTableInterface<Location> dbResponse = locationRepository.findById(id)
                    .orElseThrow(() -> new LocationNotFoundException("No location with ID : " + id + " was found"));
            return LocationValueMapper.entityToResponse(dbResponse.createEntity());
        }catch (LocationNotFoundException locationNotFoundException){
            String errorMessage = locationNotFoundException.getMessage();
            log.warn(errorMessage);
            throw new LocationNotFoundException(errorMessage);
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching location with id : " + id);
        }
    }


}