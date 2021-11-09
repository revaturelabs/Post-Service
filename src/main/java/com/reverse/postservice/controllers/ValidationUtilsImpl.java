package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import com.reverse.postservice.tools.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Slf4j
@Service
public class ValidationUtilsImpl implements ValidationUtils{

    private static final String validationAddress = System.getenv("VALIDATION");

    public void validateJwt(String token) throws InvalidJwtException {
        //todo: Call User-Service to validate the jwt.
        Log.getLog().debug("Valid JWT received.");
        Log.getLog().debug("Token: " + token);
        HttpStatus status = HttpStatus.OK; //todo: Set this status from the ResponseEntity

        if(status != HttpStatus.OK) {
            Log.getLog().error("Invalid JWT received.");
            Log.getLog().error("Token: " + token);

        log.debug(validationAddress);

        WebClient client = WebClient.create();

        ResponseEntity<String> response = client
                .post()
                .uri(validationAddress)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"token\":" + token + "}")
                .retrieve().toEntity(String.class)
                .block();

        if(Objects.requireNonNull(response).getStatusCodeValue() != 200) {

            throw new InvalidJwtException("Invalid JWT received");
        }
    }
}
