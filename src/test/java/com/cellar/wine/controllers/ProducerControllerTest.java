package com.cellar.wine.controllers;

import com.cellar.wine.model.Producer;
import com.cellar.wine.services.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @Mock
    ProducerService producerService;

    @InjectMocks
    ProducerController producerController;

    Set<Producer> producers;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        producers = new HashSet<>();
        producers.add(Producer.builder().id(1L).build());
        producers.add(Producer.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(producerController)
                .build();
    }

}
