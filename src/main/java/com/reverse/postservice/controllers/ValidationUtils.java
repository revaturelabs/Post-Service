package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationUtils {

    static void validateJwt(String token) throws InvalidJwtException {
        //todo: Call User-Service to validate the jwt.
        HttpStatus status = HttpStatus.OK; //todo: Set this status from the ResponseEntity

        if(status.equals(HttpStatus.UNAUTHORIZED)) {
            throw new InvalidJwtException("Invalid JWT received");
        }
    }
}
