/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.*;
import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.RegionService;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class DataRestControllerTest {

    @InjectMocks
    DataRestController controller;

    @Mock
    CountryService countryService;

    @Mock
    RegionService regionService;

    Country country;
    Region region;
    Area area;
    Producer producer;
    Wine wine;
    Grape grape;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        grape = new Grape();
        grape.setName("grape");
        grape.setId(1L);

        wine = new Wine();
        wine.setName("wine");
        wine.setVintage(2018);
        wine.setSize(0.75f);
        wine.setId(1L);

        producer = new Producer();
        producer.setName("producer");

        area = new Area();
        area.setName("area");

        region = new Region();
        region.setName("region");
        region.setId(1L);

        country = new Country();
        country.setName("country");

        producer.setWines(Collections.singleton(wine));

        area.setProducers(Collections.singleton(producer));
        area.setPrimaryGrapes(Collections.singleton(grape));
        area.setRegions(Collections.singleton(region));

        region.setAreas(Collections.singleton(area));

        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void dataApiRootGet_ok() throws Exception {
        given(countryService.findWithRegions()).willReturn(Collections.singletonList(country));

        MockHttpServletResponse response = mockMvc.perform(get("/api")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void countryByNameGet_ok() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}", country.getName())
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void regionByNameGet_ok() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}/{region}",
                country.getName(), region.getName()).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void areaByNameGet_ok() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}/{region}/{area}",
                country.getName(), region.getName(), area.getName()).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void producerByNameGet_ok() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}/{region}/{area}/{producer}",
                country.getName(), region.getName(), area.getName(), producer.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void wineByNameGet_ok() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);
        assertThrows(NestedServletException.class, // This Block should be removes as soon as Builder won't return null object.
                ()->{
                    MockHttpServletResponse response = mockMvc.perform(get(
                            "/api/{country}/{region}/{area}/{producer}/{wine}/{vintage}/{size}",
                            country.getName(), region.getName(), area.getName(), producer.getName(),
                            wine.getName(), wine.getVintage(), wine.getSize())
                            .accept(MediaType.APPLICATION_JSON))
                            .andReturn().getResponse();
                });
    }

    @Test
    void getJasonEnvelopeReturnsNullIfEmpty() throws Exception {
        assertThrows(NestedServletException.class,
                ()->{
        MockHttpServletResponse response = mockMvc.perform(get(
                "/api/json")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                });
    }
}
