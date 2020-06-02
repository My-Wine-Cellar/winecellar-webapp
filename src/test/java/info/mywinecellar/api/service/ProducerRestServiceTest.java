package info.mywinecellar.api.service;

import info.mywinecellar.model.Producer;
import info.mywinecellar.service.ProducerService;

import nl.altindag.log.LogCaptor;
import org.mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerRestServiceTest {

    @Mock
    ProducerService producerService;

    @InjectMocks
    ProducerRestService producerRestService = new ProducerRestService();

    Producer update;
    Producer request;

    String expectedInfoMessage;

    LogCaptor<ProducerRestService> logCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing().when(producerService).save(Mockito.any());

        logCaptor = LogCaptor.forClass(ProducerRestService.class);

        update = new Producer();
        request = new Producer();

        expectedInfoMessage = "==== Updated Producer(2) ====";
    }

    @Test
    void updateProducer_requestIsNull(){
        request.setId(1L);
        request.setName(null);
        request.setDescription(null);
        request.setWebsite(null);
        request.setEmail(null);
        request.setPhone(null);
        request.setFax(null);

        update.setId(2L);
        update.setName("name");
        update.setDescription("description");
        update.setWebsite("website");
        update.setEmail("test@email.com");
        update.setPhone("123456");
        update.setFax("123456");

        producerRestService.updateProducer(update, request);

        assertTrue(logCaptor.getInfoLogs().contains(expectedInfoMessage));
    }

    @Test
    void updateProducer_requestIsNotNull(){
        request.setId(1L);
        request.setName("name");
        request.setDescription("description");
        request.setWebsite("website");
        request.setEmail("test@email.com");
        request.setPhone("123456");
        request.setFax("123456");

        update.setId(2L);
        update.setName(null);
        update.setDescription(null);
        update.setWebsite(null);
        update.setEmail(null);
        update.setPhone(null);
        update.setFax(null);

        producerRestService.updateProducer(update, request);

        assertTrue(logCaptor.getInfoLogs().contains(expectedInfoMessage));
    }
}