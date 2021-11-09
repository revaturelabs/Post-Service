package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import com.reverse.postservice.tools.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationUtilsImpl implements ValidationUtils{

    public void validateJwt(String token) throws InvalidJwtException {
        //todo: Call User-Service to validate the jwt.
        Log.getLog().debug("Valid JWT received.");
        Log.getLog().debug("Token: " + token);
        HttpStatus status = HttpStatus.OK; //todo: Set this status from the ResponseEntity

        if(status != HttpStatus.OK) {
            Log.getLog().error("Invalid JWT received.");
            Log.getLog().error("Token: " + token);
            throw new InvalidJwtException("Invalid JWT received");
        }
    }
}
