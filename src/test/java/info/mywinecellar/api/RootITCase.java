/*
 * My-Wine-Cellar, copyright 2022
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
import info.mywinecellar.json.MyWineCellar;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RootITCase extends BaseITCase {

    @Test
    void dataApiRootGet() {
        MyWineCellar response = apiRequest("/countries", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getCountries()).hasSize(2);

        List<CountryDto> dtos = response.getCountries();
        dtos.forEach(countryDto -> assertThat(countryDto.getKey()).isNotEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"United States", "united states", "UNITED STATES", "united States"})
    void countryByNameGet(String path) {
        MyWineCellar response = apiRequest(path, null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getRegions()).hasSize(50);

        CountryDto dto = response.getCountries().get(0);
        assertThat(dto.getName()).isEqualTo("United States");
    }

    @ParameterizedTest
    @ValueSource(strings = {"California", "california", "CALIFORNIA"})
    void regionByNameGet(String path) {
        MyWineCellar response = apiRequest("/united_states/" + path, null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getCountries()).hasSize(1);
        assertThat(response.getRegions()).hasSize(1);
        assertThat(response.getAreas()).hasSize(125);

        RegionDto dto = response.getRegions().get(0);
        assertEquals("California", dto.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Napa Valley AVA", "NAPA VALLEY AVA", "napa valley ava"})
    void areaByNameGet(String path) {
        MyWineCellar response = apiRequest("/united_states/california/" + path, null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getProducers()).isNotEmpty();

        AreaDto dto = response.getAreas().get(0);
        assertEquals("Napa Valley AVA", dto.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Opus One Winery", "opus one winery", "OPUS ONE WINERY"})
    void producerByNameGet(String path) {
        MyWineCellar response = apiRequest("/united_states/california/napa_valley_ava/" + path, null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getCountries()).hasSize(1);
        assertThat(response.getRegions()).hasSize(1);
        assertThat(response.getAreas()).hasSize(1);

        ProducerDto dto = response.getProducers().get(0);
        assertEquals("Opus One Winery", dto.getName());
    }

    @Test
    void wineByNameGet() {
        MyWineCellar response = apiRequest("/united_states/california/napa_valley_ava/opus_one_winery/opus_one/2015/0.75", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getProducers()).hasSize(1);

        WineDto dto = response.getWines().get(0);
        assertEquals("Opus One", dto.getName());
    }
}
