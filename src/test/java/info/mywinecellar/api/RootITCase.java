/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.dto.WineDto;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RootITCase extends BaseITCase {

    @Test
    void dataApiRootGet() {
        ResponseEntity<String> response = apiRequest("/countries", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertEquals(2, myWineCellar.getCountries().size());

        List<CountryDto> dtos = myWineCellar.getCountries();
        dtos.forEach(countryDto -> assertFalse(countryDto.getKey().isEmpty()));
    }

    @Test
    void countryByNameGet() {
        ResponseEntity<String> response = apiRequest("/united_states", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertEquals(50, myWineCellar.getRegions().size());

        CountryDto dto = myWineCellar.getCountries().get(0);
        assertEquals("United States", dto.getName());
    }

    @Test
    void regionByNameGet() {
        ResponseEntity<String> response = apiRequest("/united_states/california", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertEquals(1, myWineCellar.getCountries().size());
        assertEquals(1, myWineCellar.getRegions().size());
        assertEquals(125, myWineCellar.getAreas().size());

        RegionDto dto = myWineCellar.getRegions().get(0);
        assertEquals("California", dto.getName());
    }

    @Test
    void areaByNameGet() {
        ResponseEntity<String> response = apiRequest("/united_states/california/napa_valley_ava", null,
                HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertFalse(myWineCellar.getProducers().isEmpty());

        AreaDto dto = myWineCellar.getAreas().get(0);
        assertEquals("Napa Valley AVA", dto.getName());
    }

    @Test
    void producerByNameGet() {
        ResponseEntity<String> response =
                apiRequest("/united_states/california/napa_valley_ava/opus_one_winery", null,
                        HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertEquals(1, myWineCellar.getCountries().size());
        assertEquals(1, myWineCellar.getRegions().size());
        assertEquals(1, myWineCellar.getAreas().size());

        ProducerDto dto = myWineCellar.getProducers().get(0);
        assertEquals("Opus One Winery", dto.getName());
    }

    @Test
    void wineByNameGet() {
        ResponseEntity<String> response =
                apiRequest("/united_states/california/napa_valley_ava/opus_one_winery/opus_one/2015/0.75",
                        null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        assertEquals(1, myWineCellar.getProducers().size());

        WineDto dto = myWineCellar.getWines().get(0);
        assertEquals("Opus One", dto.getName());
    }
}
