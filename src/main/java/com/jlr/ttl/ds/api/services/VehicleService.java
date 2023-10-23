package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.annotation.TrackExecutionTime;
import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;
import com.jlr.ttl.ds.api.dto.table.DSTableInterface;
import com.jlr.ttl.ds.api.dto.table.VehiclesTable;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.LocationNotFoundException;
import com.jlr.ttl.ds.api.exception.data.VehicleNotFoundException;
import com.jlr.ttl.ds.api.repositories.LocationRepository;
import com.jlr.ttl.ds.api.repositories.VehicleRepository;
import com.jlr.ttl.ds.api.util.mapper.LocationValueMapper;
import com.jlr.ttl.ds.api.util.mapper.VehicleValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private LocationRepository locationRepository;

    /**
     * Retrieves the vehicle details for the provided vehicle ID.
     * Additional vehicle information can be included based on the request parameters.
     *
     * @param info Request parameters list, starting with vehicle ID
     * @return VehicleResponse containing requested vehicle details
     * @throws VehicleNotFoundException If no vehicle found for given ID
     * @throws ServiceBusinessException For any other errors retrieving data
     * @since v2
     */
    @TrackExecutionTime
    public VehicleResponse getVehicleByID(List<String> info) throws ServiceBusinessException {

        // Case of empty list of String, or the first string is empty, or the first string is not id number
        if(info.isEmpty() || info.get(0).isEmpty() || !isNumeric(info.get(0))){
            String errorMessage = "No vehicle id was provided";
            throw new IllegalArgumentException(errorMessage);
        }

        String id = info.get(0);
        try {
            DSTableInterface<Vehicle> dbResponse = vehicleRepository.findById(id)
                    .orElseThrow(() -> new VehicleNotFoundException("No vehicle with ID : " + id + " was found"));
            VehicleResponse vehicleResponse = VehicleValueMapper.entityToResponse(dbResponse.createEntity());
            // Check request parameters
            if (info.contains("location")) {
                // Find location of the vehicle
                String locCode = vehicleResponse.getLocCode();
                DSTableInterface<Location> lcResponse = locationRepository.findById(locCode).orElseThrow(
                        () -> new LocationNotFoundException("No location with location code : " + locCode + " was found")
                );
                vehicleResponse.setLocationResponse(LocationValueMapper.entityToResponse(lcResponse.createEntity()));
            }
            return vehicleResponse;
        }catch (VehicleNotFoundException vehicleNotFoundException){
            String errorMessage = vehicleNotFoundException.getMessage();
            log.warn(errorMessage);
            throw new VehicleNotFoundException(errorMessage);
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching vehicle with id : " + id);
        }
    }

    /**
     * Getting all the vehicles from database.
     * @return a list of VehicleResponse
     */
    public List<VehicleResponse> getAllVehicles() throws ServiceBusinessException {
        try {
            List<VehiclesTable> dbResponse = vehicleRepository.findAll();

            List<VehicleResponse> listOfVehicleResponse = new ArrayList<VehicleResponse>();
            log.info("Retrieved data from database, transforming to response");
            for(VehiclesTable v : dbResponse) {
                listOfVehicleResponse.add(VehicleValueMapper.entityToResponse(v.createEntity()));
            }
            return listOfVehicleResponse;
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching vehicles");
        }
    }

    // Helper method
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
