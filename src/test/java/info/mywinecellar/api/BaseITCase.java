/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.json.MyWineCellar;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class BaseITCase {

    /**
     * Api path
     */
    protected static final String API_PATH = "http://localhost:8080/api";

    //TODO execute this before any other test

    /**
     * Test our environment
     */
    @Test
    public void healthCheck() {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<MyWineCellar> objectResponse = client.exchange(API_PATH + "/json",
                HttpMethod.GET, entity, MyWineCellar.class);
        assertNotNull(objectResponse);
        assertEquals(200, objectResponse.getStatusCode().value());
        assertTrue(objectResponse.getStatusCode().is2xxSuccessful());

        ResponseEntity<String> stringResponse = client.exchange(API_PATH + "/json",
                HttpMethod.GET, entity, String.class);
        assertNotNull(stringResponse);
        assertEquals(200, stringResponse.getStatusCodeValue());
        //TODO convert json to MyWineCellar object
    }

    protected HttpEntity<String> setupEntity() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(null, headers);
    }

}
