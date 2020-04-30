/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.RegionService;

import java.util.Collections;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setName("country");

        region = new Region();
        region.setName("region");

        area = new Area();
        area.setName("area");

        region.setAreas(Collections.singleton(area));

        producer = new Producer();
        producer.setName("producer");
        area.setProducers(Collections.singleton(producer));

        wine = new Wine();
        wine.setName("wine");
        wine.setVintage(2018);
        wine.setSize(0.75f);
        producer.setWines(Collections.singleton(wine));

        mockMvc = standaloneSetup(controller).build();
    }

    //@Test
    void dataApiRootGet() throws Exception {
        given(countryService.findWithRegions()).willReturn(Collections.singletonList(country));

        MockHttpServletResponse response = mockMvc.perform(get("/api")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    void countryByNameGet() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}", country.getName())
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    void regionByNameGet() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{coutry}/{region}",
                country.getName(), region.getName()).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    void areaByNameGet() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}/{region}/{area}",
                country.getName(), region.getName(), area.getName()).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    void producerByNameGet() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get("/api/{country}/{region}/{area}/{producer}",
                country.getName(), region.getName(), area.getName(), producer.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    void wineByNameGet() throws Exception {
        given(countryService.findByLowerCaseName(country.getName())).willReturn(country);
        given(regionService.findByLowerCaseName(region.getName(), country.getId())).willReturn(region);

        MockHttpServletResponse response = mockMvc.perform(get(
                "/api/{country}/{region}/{area}/{producer}/{wine}/{vintage}/{size}",
                country.getName(), region.getName(), area.getName(), producer.getName(),
                wine.getName(), wine.getVintage(), wine.getSize())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
