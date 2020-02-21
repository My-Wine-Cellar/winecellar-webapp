/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.WineRestService;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.WineService;

import java.util.Collections;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class WineRestControllerTest {

    @InjectMocks
    WineRestController controller;

    @Mock
    WineService wineService;

    @Mock
    ProducerService producerService;

    @Mock
    WineRestService restService;

    Wine opusOne;
    Producer opusProducer;
    Wine empty;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        opusProducer = new Producer();
        opusProducer.setId(1L);
        opusProducer.setName("Opus Producer");
        opusOne = new Wine();

        opusOne.setId(1L);
        opusOne.setName("Opus One");
        opusOne.setProducer(opusProducer);

        opusProducer.setWines(Collections.singletonList(opusOne));

        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void wineNewPost() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("/api/wine/new")
                .queryParam("producerId", String.valueOf(1L))
                .queryParam("shapeId", String.valueOf(1L))
                .queryParam("typeId", String.valueOf(1L))
                .queryParam("colorId", String.valueOf(1L))
                .queryParam("closureId", String.valueOf(1L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Wine())))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void wineEditPut() throws Exception {
        given(wineService.findById(opusOne.getId())).willReturn(opusOne);

        opusOne.setName("Opus One Wine");

        MockHttpServletResponse response = mockMvc.perform(put("/api/wine/{id}/edit", opusOne.getId())
                .content(new ObjectMapper().writeValueAsString(opusOne))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals("Opus One Wine", opusOne.getName());
        assertEquals(1L, opusOne.getId());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    // TODO write null check on object test

}
