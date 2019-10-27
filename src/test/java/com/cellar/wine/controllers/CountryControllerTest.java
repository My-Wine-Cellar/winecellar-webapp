package com.cellar.wine.controllers;

import com.cellar.wine.models.Country;
import com.cellar.wine.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @InjectMocks
    CountryController controller;

    @Mock
    CountryService countryService;

    Country country;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setId(1L);
        country.setName("France");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void countryEditGet() throws Exception {

        when(countryService.findById(anyLong())).thenReturn(country);

        mockMvc.perform(get("/country/{countryId}/edit", country.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("country", country))
                .andExpect(view().name("country/countryEdit"));
    }

    @Test
    void countryEditPost() {
    }
}