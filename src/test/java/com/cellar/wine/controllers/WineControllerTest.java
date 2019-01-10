package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.repositories.WineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class WineControllerTest {

    @Mock
    WineRepository wineService;

    @Mock
    ProducerRepository producerService;

    @InjectMocks
    WineController wineController;

    MockMvc mockMvc;

    Producer producer;
    Wine wine;

    @BeforeEach
    void setUp() {
        producer = new Producer();

        mockMvc = MockMvcBuilders
                .standaloneSetup(wineController)
                .build();
    }

//    @Test
//    void initCreationForm() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer);
//
//        mockMvc.perform(get("/producers/1/wines/new"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("producer"))
//                .andExpect(model().attributeExists("wine"))
//                .andExpect(view().name("wines/createOrUpdateWine"));
//    }
//
//    @Test
//    void processCreationForm() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer);
//
//        mockMvc.perform(post("/producers/1/wines/new"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/producers/1"));
//    }
//
//    @Test
//    void initUpdateForm() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer);
//
//        when(wineService.findById(anyLong())).thenReturn(wine);
//
//        mockMvc.perform(get("/producers/1/wines/2/edit"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("producer"))
//                .andExpect(model().attributeExists("wine"))
//                .andExpect(view().name("wines/createOrUpdateWine"));
//    }
//
//    @Test
//    void processUpdateForm() throws Exception {
//        when(producerService.findById(anyLong())).thenReturn(producer);
//
//        mockMvc.perform(post("/producers/1/wines/2/edit"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/producers/1"));
//
//        verify(wineService).save(any());
//    }


}
