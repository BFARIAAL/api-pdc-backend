package com.jlr.ttl.ds.api.controllers;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.dto.request.DSUserRequest;
import com.jlr.ttl.ds.api.dto.response.DSUserResponse;
import com.jlr.ttl.ds.api.exception.data.UserNotFoundException;
import com.jlr.ttl.ds.api.services.DSUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
@CrossOrigin("http://localhost:4200")
public class DSUserController {

    private DSUserService dsUserService;

    @PutMapping("/getUserInfo")
    public ResponseEntity<DSResponse<DSUserResponse>> getUserByID(@RequestBody DSUserRequest dsUserRequest){
        DSUserResponse dsUserResponse;
        try{
            dsUserResponse = dsUserService.getUserByID(dsUserRequest);
        }catch (IllegalArgumentException illegalArgumentException){
            return new ResponseEntity<>(DSResponse
                    .<DSUserResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.BAD_REQUEST);
        }catch (UserNotFoundException userNotFoundException){
            return new ResponseEntity<>(DSResponse
                    .<DSUserResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DSResponse
                .<DSUserResponse>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(dsUserResponse)
                .build(), HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<DSResponse<DSUserResponse>> logInUser(@RequestBody DSUserRequest dsUserRequest){
        DSUserResponse dsUserResponse = null;
        try{
            dsUserResponse = dsUserService.logInUser(dsUserRequest);
        }catch (IllegalAccessException illegalAccessException){
            return new ResponseEntity<>(DSResponse
                    .<DSUserResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.FORBIDDEN);
        }catch (UserNotFoundException userNotFoundException){
            return new ResponseEntity<>(DSResponse
                    .<DSUserResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException illegalArgumentException){
            return new ResponseEntity<>(DSResponse
                    .<DSUserResponse>builder()
                    .status(DSConstants.STATUS_FAILED)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(DSResponse
                .<DSUserResponse>builder()
                .status(DSConstants.STATUS_SUCCESS)
                .results(dsUserResponse)
                .build(), HttpStatus.OK);
    }
}
