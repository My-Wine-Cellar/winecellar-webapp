package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.repositories.ProducerRepository;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @Mock
    ProducerRepository producerService;

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
        producers.add(producer2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(producerController)
                .build();
    }

//    @Test
//    void displayOwner() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer1);
//
//        mockMvc.perform(get("/producer/1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("producers/details"))
//                .andExpect(model().attribute("producer", hasProperty("id", is(1l))));
//    }


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
                .andExpect(view().name("redirect:/producers/1"))
                .andExpect(model().attributeExists("producers"));

        verify(producerService).save(ArgumentMatchers.any());
    }

//    @Test
//    void initUpdateProducerForm() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer1);
//
//        mockMvc.perform(get("/producers/1/edit"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("producers/createOrUpdateProducer"))
//                .andExpect(model().attributeExists("producer"));
//
//        verifyZeroInteractions(producerService);
//    }

    @Test
    void processUpdateProducerForm() throws Exception {
        when(producerService.save(ArgumentMatchers.any())).thenReturn(producer1);

        mockMvc.perform(post("/producers/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producers/{producerId}"))
                .andExpect(model().attributeExists("producer"));

        verify(producerService).save(ArgumentMatchers.any());
    }
}
