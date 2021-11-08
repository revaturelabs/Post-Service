package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationUtilsImpl implements ValidationUtils{

    public void validateJwt(String token) throws InvalidJwtException {
        //todo: Call User-Service to validate the jwt.
        HttpStatus status = HttpStatus.OK; //todo: Set this status from the ResponseEntity

        if(status != HttpStatus.OK) {
            throw new InvalidJwtException("Invalid JWT received");
        }
    }
}
