package com.cellar.wine.controllers;

import com.cellar.wine.models.Wine;
import com.cellar.wine.services.WineService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class WineControllerTest {

    @Mock
    WineService wineService;

    @InjectMocks
    WineController wineController;

    Set<Wine> wines;

    MockMvc mockMvc;

}
