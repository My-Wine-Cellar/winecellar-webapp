/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.BarrelService;
import info.mywinecellar.service.ClosureService;
import info.mywinecellar.service.ColorService;
import info.mywinecellar.service.GrapeService;
import info.mywinecellar.service.ShapeService;
import info.mywinecellar.service.TypeService;
import info.mywinecellar.service.WineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class WineControllerTest {

    @InjectMocks
    WineController controller;

    @Mock WineService wineService;
    @Mock ShapeService shapeService;
    @Mock ClosureService closureService;
    @Mock GrapeService grapeService;
    @Mock BarrelService barrelService;
    @Mock ColorService colorService;
    @Mock TypeService typeService;

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
