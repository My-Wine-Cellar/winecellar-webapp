package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.services.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @Mock
    ProducerService producerService;

    @InjectMocks
    ProducerController producerController;

    Set<Producer> producers;

    MockMvc mockMvc;

    Producer producer1 = new Producer();
    Producer producer2 = new Producer();

    @BeforeEach
    void setUp() {

        producers = new HashSet<>();
        producers.add(producer1);
        producer1.setId(1L);
        producers.add(producer2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(producerController)
                .build();
    }

    @Test
    void displayOwner() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer1);

        mockMvc.perform(get("/producers/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("producers/producerDetails"))
                .andExpect(model().attribute("producer", hasProperty("id", is(1L))));
    }


    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/producers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("producers/createOrUpdateProducer"))
                .andExpect(model().attributeExists("producer"));

        verifyZeroInteractions(producerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(producerService.save(ArgumentMatchers.any())).thenReturn(producer1);

        mockMvc.perform(post("/producers/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producers/" + producer1.getId()));

        verify(producerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateProducerForm() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer1);

        mockMvc.perform(get("/producers/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("producers/createOrUpdateProducer"))
                .andExpect(model().attributeExists("producer"));

        verifyZeroInteractions(producerService);
    }

    @Test
    void processUpdateProducerForm() throws Exception {
        when(producerService.save(ArgumentMatchers.any())).thenReturn(producer1);

        mockMvc.perform(post("/producers/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producers/" + producer1.getId()))
                .andExpect(model().attributeExists("producer"));

        verify(producerService).save(ArgumentMatchers.any());
    }
}
