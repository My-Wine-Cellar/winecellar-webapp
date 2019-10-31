package com.cellar.wine.controllers;

import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.BarrelService;
import com.cellar.wine.services.ClosureService;
import com.cellar.wine.services.GrapeService;
import com.cellar.wine.services.ShapeService;
import com.cellar.wine.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class WineControllerTest {

    @InjectMocks
    WineController controller;

    @Mock
    WineService wineService;

    @Mock
    ShapeService shapeService;

    @Mock
    ClosureService closureService;

    @Mock
    GrapeService grapeService;

    @Mock
    BarrelService barrelService;

    private Wine wine;

    private GrapeComponent grape;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        wine = new Wine();
        wine.setId(1L);

        grape = new GrapeComponent();
        grape.setId(1L);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void wineAddRequiredGet() throws Exception {
        mockMvc.perform(get("/wine/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("wine/wineAddEditDetails"));
    }

    @Test
    void wineAddGrapeComponentsGet() throws Exception {
        mockMvc.perform(get("/wine/grape"))
                .andExpect(model().attribute("wine", new Wine()))
                .andExpect(status().isOk())
                .andExpect(view().name("wine/wineAddGrape"));
    }

    @Test
    void wineAddGrapeBarrelGet() throws Exception {
        mockMvc.perform(get("/wine/grape/{grapeId}/barrel", grape.getId())
                .param("action", "save"))
                .andExpect(model().attribute("wine", new Wine()))
                .andExpect(status().isOk())
                .andExpect(view().name("wine/wineAddGrapeBarrel"));
    }

    @Test
    void wineEditGet() throws Exception {
        mockMvc.perform(get("/wine/{wineId}/edit", wine.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("wine/wineAddEditDetails"));
    }
}