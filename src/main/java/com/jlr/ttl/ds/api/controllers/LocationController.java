package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.dto.entity.Location;
import com.jlr.ttl.ds.api.dto.response.DSUserResponse;
import com.jlr.ttl.ds.api.dto.response.LocationResponse;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.LocationNotFoundException;
import com.jlr.ttl.ds.api.exception.data.UserNotFoundException;
import com.jlr.ttl.ds.api.exception.data.VehicleNotFoundException;
import com.jlr.ttl.ds.api.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/locations")
@CrossOrigin("http://localhost:4200")
public class LocationController {


    private LocationService locationService;

    /**
     * Getting all the locations from database.
     * @return a list of LocationResponse
     */
    @GetMapping("/all")
    public ResponseEntity<DSResponse<List<LocationResponse>>> getAllLocations() {
        List<LocationResponse> locationResponse = null;
        try{
            locationResponse = locationService.getAllLocations();
        }catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<List<LocationResponse>>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(DSResponse
                .<List<LocationResponse>>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(locationResponse)
                .build(), HttpStatus.OK);
    }




    /**
     * Fetch the location by the id passed
     *
     * @return Location
     */
    @GetMapping("/by_id/{id}")
    public ResponseEntity<DSResponse<LocationResponse>> getLocationById(@PathVariable(value = "id") String id){
        LocationResponse locationResponse = null;
        try {
            locationResponse = locationService.getLocationByID(id);
        } catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<LocationResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (LocationNotFoundException locationNotFoundException){
            return new ResponseEntity<>(DSResponse
                    .<LocationResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DSResponse
                .<LocationResponse>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(locationResponse)
                .build(), HttpStatus.OK);
    }

}
