/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RootITCase extends BaseITCase {

    RestTemplate client = new RestTemplate();

    @Test
    void dataApiRootGet() {
        ResponseEntity<String> response = client.exchange(API_PATH + "/countries",
                HttpMethod.GET, setupEntity(), String.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void countryByNameGet() {
        ResponseEntity<MyWineCellar> response = client.exchange(API_PATH + "/united_states",
                HttpMethod.GET, setupEntity(), MyWineCellar.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void regionByNameGet() {
        ResponseEntity<MyWineCellar> response = client.exchange(
                API_PATH + "/united_states/california",
                HttpMethod.GET, setupEntity(), MyWineCellar.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void areaByNameGet() {
        ResponseEntity<MyWineCellar> response = client.exchange(
                API_PATH + "/united_states/california/napa_valley_ava",
                HttpMethod.GET, setupEntity(), MyWineCellar.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void producerByNameGet() {
        ResponseEntity<MyWineCellar> response = client.exchange(
                API_PATH + "/united_states/california/napa_valley_ava/opus_one_winery",
                HttpMethod.GET, setupEntity(), MyWineCellar.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void wineByNameGet() {
        ResponseEntity<MyWineCellar> response = client.exchange(
                API_PATH + "/united_states/california/napa_valley_ava/opus_one_winery/opus_one/2015/0.75",
                HttpMethod.GET, setupEntity(), MyWineCellar.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}
