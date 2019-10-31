package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.GrapeService;
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
class AreaControllerTest {

    @InjectMocks
    AreaController areaController;

    @Mock
    AreaService areaService;

    @Mock
    GrapeService grapeService;

    Area area;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        area = new Area();
        area.setId(1L);
        area.setName("Grand Valley AVA");

        mockMvc = MockMvcBuilders.standaloneSetup(areaController).build();
    }

    @Test
    void areaEditGet() throws Exception {
        when(areaService.findById(anyLong())).thenReturn(area);

        mockMvc.perform(get("/area/{areaId}/edit", area.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("area", area))
                .andExpect(view().name("area/areaEdit"));

    }

    @Test
    void areaEditPost() {
    }

    @Test
    void areaAddProducerGet() throws Exception {
        when(areaService.findById(anyLong())).thenReturn(area);

        mockMvc.perform(get("/area/{areaId}/addProducer", area.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("area", area))
                .andExpect(view().name("producer/producerAddEdit"));
    }

    @Test
    void areaAddProducerPost() {
    }

    @Test
    void areaAddGrapeGet() throws Exception {
        when(areaService.findById(anyLong())).thenReturn(area);

        mockMvc.perform(get("/area/{areaId}/addGrape", area.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("area", area))
                .andExpect(view().name("area/areaAddGrape"));
    }

    @Test
    void areaAddGrapePost() {
    }
}