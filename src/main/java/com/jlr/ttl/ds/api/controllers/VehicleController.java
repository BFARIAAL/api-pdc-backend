package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.VehicleNotFoundException;
import com.jlr.ttl.ds.api.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/vehicles")
@CrossOrigin("http://localhost:4200")
public class VehicleController {

    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<DSResponse<List<VehicleResponse>>> getAllVehicles() {
        List<VehicleResponse> dsVehicleResponse = null;
        try{

            dsVehicleResponse = vehicleService.getAllVehicles();
        }catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<List<VehicleResponse>>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(DSResponse
                .<List<VehicleResponse>>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(dsVehicleResponse)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<DSResponse<VehicleResponse>> getVehicleById(@PathVariable(value = "id") String id){
        VehicleResponse vehicleResponse = null;
        try {
            vehicleResponse = vehicleService.getVehicleByID(id);
        } catch (IllegalStateException illegalStateException) {
            return new ResponseEntity<>(DSResponse
                    .<VehicleResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (VehicleNotFoundException vehicleNotFoundException){
            return new ResponseEntity<>(DSResponse
                    .<VehicleResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DSResponse
                .<VehicleResponse>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(vehicleResponse)
                .build(), HttpStatus.OK);
    }
}