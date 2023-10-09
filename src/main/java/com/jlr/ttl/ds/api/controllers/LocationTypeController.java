package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.LocationTypeNotFoundException;
import com.jlr.ttl.ds.api.services.LocationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/location_type")
@CrossOrigin("http://localhost:4200")
public class LocationTypeController {

    private LocationTypeService locationTypeService;

    /**
     * Getting all the location types from database.
     *
     * @return a list of LocationTypeResponse
     */
    @GetMapping("/all")
    public ResponseEntity<DSResponse<List<LocationTypeResponse>>> getAllLocationTypes() {
        List<LocationTypeResponse> locationTypeResponse = null;
        try{
            locationTypeResponse = locationTypeService.getAllLocationTypes();
        }catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<List<LocationTypeResponse>>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(DSResponse
                .<List<LocationTypeResponse>>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(locationTypeResponse)
                .build(), HttpStatus.OK);
    }

    /**
     * Fetch the location type response by the id passed
     *
     * @return LocationType
     */
    @GetMapping("/by_id/{id}")
    public ResponseEntity<DSResponse<LocationTypeResponse>> getLocationTypeById(@PathVariable(value = "id") String id){
        LocationTypeResponse locationTypeResponse = null;
        try {
            locationTypeResponse = locationTypeService.getLocationTypeByID(id);
        } catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<LocationTypeResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (LocationTypeNotFoundException locationTypeNotFoundException){
            return new ResponseEntity<>(DSResponse
                    .<LocationTypeResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DSResponse
                .<LocationTypeResponse>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(locationTypeResponse)
                .build(), HttpStatus.OK);
    }
}
