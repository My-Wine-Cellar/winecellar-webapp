package info.mywinecellar.api;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AreaITCase extends BaseITCase {

    @Test
    void areaByIdGet() {
        ResponseEntity<String> response = apiRequest("/area/80", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getAreas().forEach(area -> {
            assertEquals(1, area.getRegions().size());
            assertEquals(1, area.getPrimaryGrapes().size());
            assertEquals("Napa Valley AVA", area.getName());
            assertEquals("napa_valley_ava", area.getKey());
        });
    }

    @Test
    void areaEdit() {
        ResponseEntity<String> response = apiRequest("/area/80/edit", jsonBody(), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getAreas().forEach(area -> {
            assertNotNull(area.getDescription());
            assertNotNull(area.getWeblink());
        });
    }

    @Test
    void areaAddProducer() throws JsonProcessingException {
        ProducerDto request = new ProducerDto();
        request.setName("Opus Two");
        ResponseEntity<String> response = apiRequest("/area/80/add-producer",
                objectMapper.writeValueAsString(request), HttpMethod.POST);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void areaAddGrape() {
        ResponseEntity<String> response = apiRequest("/area/80/add-grape?grapeId=45&grapeId=23&grapeId=1",
                null, HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        AreaDto dto = myWineCellar.getAreas().get(0);
        assertEquals(4, dto.getPrimaryGrapes().size());
    }
}
