package info.mywinecellar.controller;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Producer;
import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProducerControllerTest {

    @InjectMocks
    ProducerController controller;

    @Mock
    ProducerService producerService;

    @Mock
    AreaService areaService;

    private Producer producer;

    private Area area;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        producer = new Producer();
        producer.setId(1L);
        producer.setName("Producer");

        area = new Area();
        area.setId(1L);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void producerEditGet() throws Exception {
        when(producerService.findById(anyLong())).thenReturn(producer);

        mockMvc.perform(get("/producer/{producerId}/edit", producer.getId())
                .param("areaId", area.getId().toString()))
                .andExpect(model().attribute("producer", producer))
                //.andExpect(model().attribute("area", area))
                .andExpect(status().is(200))
                .andExpect(view().name("producer/producerAddEdit"));
    }

    //@Test
    void producerEditPost() throws Exception {

        when(producerService.findById(anyLong())).thenReturn(producer);

        mockMvc.perform(post("/producer/{producerId}/edit", producer.getId())
                .param("action", "save")
                .param("areaId", area.getId().toString()))
                .andExpect(model().attribute("producer", producer))
                .andExpect(status().isOk());
    }
}