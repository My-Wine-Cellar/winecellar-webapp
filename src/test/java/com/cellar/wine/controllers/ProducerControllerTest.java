package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.services.ProducerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @Mock
    ProducerService producerService;

    @InjectMocks
    ProducerController producerController;

    Set<Producer> producers;

    MockMvc mockMvc;

}
