package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.annotation.TrackExecutionTime;
import com.jlr.ttl.ds.api.dto.entity.Vehicle;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;
import com.jlr.ttl.ds.api.dto.table.DSTableInterface;
import com.jlr.ttl.ds.api.dto.table.VehiclesTable;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.VehicleNotFoundException;
import com.jlr.ttl.ds.api.repositories.VehicleRepository;
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

    /**
     * Fetch the vehicle by the id passed
     *
     * @return Vehicle
     * @since v1
     */

    @TrackExecutionTime
    public VehicleResponse getVehicleByID(String id) throws ServiceBusinessException {
        if(id==null || id.length()==0){
            String errorMessage = "No vehicle id was provided";
            //log.warn(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            DSTableInterface<Vehicle> dbResponse = vehicleRepository.findById(id)
                    .orElseThrow(() -> new VehicleNotFoundException("No vehicle with ID : " + id + " was found"));
            return VehicleValueMapper.entityToResponse(dbResponse.createEntity());
        }catch (VehicleNotFoundException vehicleNotFoundException){
            String errorMessage = vehicleNotFoundException.getMessage();
            //log.warn(errorMessage);
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
}
