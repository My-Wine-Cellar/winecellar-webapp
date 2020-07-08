package info.mywinecellar.api;

import info.mywinecellar.dto.WineDto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WineITCase extends BaseITCase {

    @Test
    @Order(1)
    void wineNew() throws JsonProcessingException {
        WineDto body = new WineDto();
        body.setName("New Wine");
        body.setVintage(2020);
        body.setSize(0.75f);

        ResponseEntity<String> response = apiRequest("/wine/new?producerId=1",
                objectMapper.writeValueAsString(body), HttpMethod.POST);
        assertEquals(201, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getWines().forEach(wine -> {
            assertEquals(4, wine.getId());
            assertEquals("new_wine", wine.getKey());
            assertEquals(1, wine.getProducerId());
            assertEquals(1, wine.getColorId());
            assertEquals(1, wine.getClosureId());
            assertEquals(1, wine.getShapeId());
            assertEquals(1, wine.getTypeId());
        });
    }

    @Disabled()
    @Test
    void wineNew_500Exception() {
        // name, vintage, size are required fields
        WineDto body = new WineDto();

        assertThrows(HttpServerErrorException.class,
                () -> apiRequest("/wine/new?producerId=1",
                        objectMapper.writeValueAsString(body), HttpMethod.POST));
    }

    @Test
    @Order(2)
    void wineEdit() throws JsonProcessingException {
        WineDto body = new WineDto();
        body.setName("Edit Wine");
        body.setVintage(2010);
        body.setSize(3.0f);

        ResponseEntity<String> response = apiRequest("/wine/4/edit",
                objectMapper.writeValueAsString(body), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getWines().forEach(wine -> {
            assertEquals(4, wine.getId());
            assertEquals("edit_wine", wine.getKey());
            assertEquals(2010, wine.getVintage());
        });
    }

    @Test
    void wineImage() {
        ResponseEntity<String> response = apiImageRequest("/wine/1/image");
        assertEquals(202, response.getStatusCodeValue());
    }
}
