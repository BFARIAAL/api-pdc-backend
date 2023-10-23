package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.dto.response.DSResponseInterface;
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

    /**
     * Fetches the details of a vehicle using the provided vehicle ID.
     *
     * This endpoint allows users to retrieve details of a specified vehicle
     * by its ID. Additionally, users can request specific details about
     * the vehicle by providing an optional 'info' parameter.
     *
     * @param id    The unique identifier of the vehicle, not null.
     * @param info  An optional parameter specifying which additional
     *              details about the vehicle to retrieve (e.g., location).
     * @return      A wrapped response containing the vehicle details or
     *              an error status.
     *
     * @throws ServiceBusinessException  If there's an internal error during the retrieval process.
     * @throws VehicleNotFoundException  If no vehicle is found for the provided ID.
     */
    @GetMapping("/by_id/{id}/")
    public ResponseEntity<DSResponse<VehicleResponse>> getVehicleById(
            @PathVariable String id,
            @RequestParam(required = false) String info){
        VehicleResponse vehicleResponse = null;
        try {
            vehicleResponse = vehicleService.getVehicleByID(id, info);
        } catch (ServiceBusinessException serviceBusinessException) {
            return new ResponseEntity<>(DSResponse
                    .<VehicleResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
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