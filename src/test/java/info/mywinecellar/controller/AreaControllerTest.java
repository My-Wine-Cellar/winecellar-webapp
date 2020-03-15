/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.GrapeService;
import info.mywinecellar.ui.AreaUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class AreaControllerTest {

    @InjectMocks
    AreaController areaController;

    @Mock
    AreaService areaService;

    @Mock
    GrapeService grapeService;

    AreaUI area;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        area = new AreaUI();
        area.setId(1L);
        area.setName("Grand Valley AVA");

        mockMvc = MockMvcBuilders.standaloneSetup(areaController).build();
    }

    // @Test
    // void areaEditGet() throws Exception {
    //     when(areaService.findById(anyLong())).thenReturn(area);

    //     mockMvc.perform(get("/area/{areaId}/edit", area.getId()))
    //             .andExpect(status().isOk())
    //             .andExpect(model().attribute("area", area))
    //             .andExpect(view().name("area/areaEdit"));

    // }

    @Test
    void areaEditPost() {
    }

    // @Test
    // void areaAddProducerGet() throws Exception {
    //     when(areaService.findById(anyLong())).thenReturn(area);

    //     mockMvc.perform(get("/area/{areaId}/addProducer", area.getId()))
    //             .andExpect(status().isOk())
    //             .andExpect(model().attribute("area", area))
    //             .andExpect(view().name("producer/producerAddEdit"));
    // }

    @Test
    void areaAddProducerPost() {
    }

    // @Test
    // void areaAddGrapeGet() throws Exception {
    //     when(areaService.findById(anyLong())).thenReturn(area);

    //     mockMvc.perform(get("/area/{areaId}/addGrape", area.getId()))
    //             .andExpect(status().isOk())
    //             .andExpect(model().attribute("area", area))
    //             .andExpect(view().name("area/areaAddGrape"));
    // }

    @Test
    void areaAddGrapePost() {
    }
}
