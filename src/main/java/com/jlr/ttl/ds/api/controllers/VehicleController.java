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
     * Retrieves information for a vehicle based on the provided ID and parameters.
     *
     * This method accepts a list of request parameters that must start with a valid
     * vehicle ID, and can optionally include additional fields to return information
     * for, such as location or vehicle attributes. The corresponding VehicleResponse
     * object containing the requested details is returned.
     *
     * @param info info List of request parameters starting with vehicle ID
     * @return VehicleResponse object with requested details
     * @throws ServiceBusinessException On error retrieving vehicle
     * @throws VehicleNotFoundException If vehicle ID not found
     * @since v2
     */
    @GetMapping("/by_id/")
    public ResponseEntity<DSResponse<VehicleResponse>> getVehicleById(
            @RequestParam List<String> info){
        VehicleResponse vehicleResponse = null;
        try {
            vehicleResponse = vehicleService.getVehicleByID(info);
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