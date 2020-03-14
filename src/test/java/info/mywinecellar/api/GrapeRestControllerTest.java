/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Grape;
import info.mywinecellar.service.GrapeService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class GrapeRestControllerTest {

    @InjectMocks
    GrapeRestController controller;

    @Mock
    GrapeService service;

    Grape whiteGrape1;
    Grape whiteGrape2;
    Grape redGrape1;
    Grape redGrape2;

    List<Grape> whiteGrapes = new ArrayList<>();
    List<Grape> redGrapes = new ArrayList<>();
    List<Grape> allGrapes = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        whiteGrape1 = new Grape();
        whiteGrape1.setId(1L);
        whiteGrape1.setName("grape");
        whiteGrape2 = new Grape();
        whiteGrape2.setId(2L);
        redGrape1 = new Grape();
        redGrape1.setId(3L);
        redGrape2 = new Grape();
        redGrape2.setId(4L);
        whiteGrapes.add(whiteGrape1);
        whiteGrapes.add(whiteGrape2);
        redGrapes.add(redGrape1);
        redGrapes.add(redGrape2);
        allGrapes.addAll(redGrapes);
        allGrapes.addAll(whiteGrapes);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void grapeRedGet() throws Exception {
        given(service.getRedGrapes()).willReturn(redGrapes);

        MockHttpServletResponse response = mockMvc.perform(get("/api/grape/red")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void grapeWhiteGet() throws Exception {
        given(service.getWhiteGrapes()).willReturn(whiteGrapes);

        MockHttpServletResponse response = mockMvc.perform(get("/api/grape/white")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void grapeListGet() throws Exception {
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/grape/list")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void grapeDetailsGet() throws Exception {
        given(service.findByLowerCaseName(whiteGrape1.getName())).willReturn(whiteGrape1);

        MockHttpServletResponse response = mockMvc.perform(get("/api/grape/{grape}", whiteGrape1.getName())
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
