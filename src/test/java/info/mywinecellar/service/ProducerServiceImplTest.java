package info.mywinecellar.service;

import info.mywinecellar.model.Producer;
import info.mywinecellar.repository.ProducerRepository;
import info.mywinecellar.service.impl.ProducerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerServiceImplTest {

    @InjectMocks
    ProducerServiceImpl producerService;

    @Mock
    ProducerRepository producerRepository;

    private Producer returnProducer;

    @BeforeEach
    void setUp() {
        returnProducer = new Producer();
        returnProducer.setId(1L);
        returnProducer.setName("Producer");
    }

    @Test
    void findById() {
        when(producerRepository.findById(anyLong())).thenReturn(Optional.of(returnProducer));
        Producer producer = producerService.findById(1L);

        assertNotNull(producer);
    }

    @Test
    void findByIdNotFound() {
        when(producerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Producer producer = producerService.findById(1L);

        assertNull(producer);
    }

    @Test
    void save() {
        when(producerRepository.save(any())).thenReturn(returnProducer);
        Producer producerToSave = new Producer();
        producerToSave.setId(1L);

        Producer savedProducer = producerService.save(producerToSave);

        assertNotNull(savedProducer);
        verify(producerRepository).save(any());
    }

    @Test
    void delete() {
        Producer delete = new Producer();
        producerService.delete(delete);

        assertNotNull(delete);
    }

    @Test
    void findByName() {
        when(producerRepository.findByName(any())).thenReturn(returnProducer);

        Producer producer = producerService.findByName("Producer");

        assertEquals("Producer", producer.getName());
        assertNotNull(returnProducer);
    }
}