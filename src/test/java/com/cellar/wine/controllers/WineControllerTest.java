package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class WineControllerTest {

    @Mock
    WineService wineService;

    @Mock
    ProducerService producerService;

    @InjectMocks
    WineController wineController;

    MockMvc mockMvc;

    Producer producer;
    Wine wine;

    @BeforeEach
    void setUp() throws Exception {
        producer = new Producer();
        producer.setId(1L);
        mockMvc = MockMvcBuilders
            .standaloneSetup(wineController)
            .build();
    }

    @Test
    void initCreationForm() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer);

        mockMvc.perform(get("/producers/1/wines/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("producer"))
                .andExpect(model().attributeExists("wine"))
                .andExpect(view().name("wines/createOrUpdateWine"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer);

        mockMvc.perform(post("/producers/1/wines/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producers/" + producer.getId()));
    }

    @Test
    void initUpdateForm() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer);

        when(wineService.findById(anyLong())).thenReturn(wine);

        mockMvc.perform(get("/producers/1/wines/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("producer"))
                .andExpect(view().name("wines/createOrUpdateWine"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer);

        mockMvc.perform(post("/producers/1/wines/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producers/" + producer.getId()));

        verify(wineService).save(any());
    }


}
