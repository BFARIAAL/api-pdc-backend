package com.jlr.ttl.ds.api.services;

import com.jlr.ttl.ds.api.annotation.TrackExecutionTime;
import com.jlr.ttl.ds.api.dto.entity.DSUser;
import com.jlr.ttl.ds.api.dto.request.DSUserRequest;
import com.jlr.ttl.ds.api.dto.response.DSUserResponse;
import com.jlr.ttl.ds.api.dto.table.DSTableInterface;
import com.jlr.ttl.ds.api.exception.ServiceBusinessException;
import com.jlr.ttl.ds.api.exception.data.UserNotFoundException;
import com.jlr.ttl.ds.api.repositories.DSUserRepository;
import com.jlr.ttl.ds.api.util.mapper.DSUserValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DSUserService {

    private DSUserRepository dsUserRepository;

    /**
     * Fetch the user by the id passed
     *
     * @return DSUser
     * @since v1
     */
    @TrackExecutionTime
    public DSUserResponse getUserByID(DSUserRequest dsUserRequest) throws ServiceBusinessException {
        String id = dsUserRequest.getId();
        if(id==null || id.length()==0){
            String errorMessage = "No user id was provided";
            log.warn(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            DSTableInterface<DSUser> dbResponse = dsUserRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("No user with ID : " + id + " was found"));
            return DSUserValueMapper.entityToResponse(dbResponse.createEntity());
        }catch (UserNotFoundException userNotFoundException){
            String errorMessage = userNotFoundException.getMessage();
            log.warn(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching user with id : " + id);
        }
    }

    /**
     * This method will perform retrieve a user's information and return it, only if the password introduced by the user
     * matches the account password
     *
     * @return D42User
     * @throws IllegalAccessException If an unsuccessful attempt to get the account information was made
     * @since v1
     */
    @TrackExecutionTime
    public DSUserResponse logInUser(DSUserRequest dsUserRequest) throws ServiceBusinessException, IllegalAccessException {
        try {
            DSUserResponse dsUserResponse = getUserByID(dsUserRequest);
            checkPassword(DSUserValueMapper.responseToEntity(dsUserResponse), dsUserRequest);
            /*
             * If we get here we passed the password check. Set the response to null as we don't want it's information to
             * be sent
             */
            dsUserResponse.setPassword(null);
            return dsUserResponse;
        } catch (IllegalAccessException ex) {
            String errorMessage = "An unsuccessful attempt was made to log into the account with id: " + dsUserRequest.getId();
            log.warn(errorMessage);
            throw new IllegalAccessException(errorMessage);
        } catch (UserNotFoundException userNotFoundException) {
            String errorMessage = userNotFoundException.getMessage();
            log.warn(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }catch (IllegalArgumentException illegalArgumentException){
            String errorMessage = illegalArgumentException.getMessage();
            log.warn(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }catch (Exception ex) {
            throw new ServiceBusinessException("Exception occurred while fetching user with id : " + dsUserRequest.getId());
        }
    }

    /**
     * This method is responsible for validating the password introduced by the users and the one saved in the database.
     * If they don't match, an Illegal Access Exception will be thrown
     * @param dsUser
     * @param dsUserRequest
     * @throws IllegalAccessException
     */
    private void checkPassword(DSUser dsUser, DSUserRequest dsUserRequest) throws IllegalAccessException {
        String password = dsUserRequest.getPassword();
        if(password == null || password.length()==0)
            throw new IllegalArgumentException("No password provided");
        if (!dsUser.validateCredentials(dsUserRequest.getPassword()))
            throw new IllegalAccessException("The credentials introduced are not correct");
    }
}
