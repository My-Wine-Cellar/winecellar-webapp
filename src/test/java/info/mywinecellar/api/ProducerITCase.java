package info.mywinecellar.api;

import info.mywinecellar.dto.ProducerDto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProducerITCase extends BaseITCase {

    @Test
    void producerEdit() throws JsonProcessingException {
        ProducerDto edit = new ProducerDto();
        edit.setName("Edited Producer");
        edit.setDescription("edited description");
        edit.setWebsite("edited weblink");
        edit.setPhone("1234567890");
        edit.setEmail("producer@gmail.com");

        ResponseEntity<String> response = apiRequest("/producer/2/edit",
                objectMapper.writeValueAsString(edit), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        ProducerDto producer = myWineCellar.getProducers().get(0);
        assertEquals(2, producer.getId());
        assertEquals("edited_producer", producer.getKey());
    }

    @Test
    void producerImage() {
        ResponseEntity<String> response = apiImageRequest("/producer/1/image");
        assertEquals(202, response.getStatusCodeValue());
    }
}
