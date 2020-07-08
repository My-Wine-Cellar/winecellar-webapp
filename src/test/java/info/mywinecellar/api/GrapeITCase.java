package info.mywinecellar.api;

import info.mywinecellar.dto.GrapeDto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GrapeITCase extends BaseITCase {

    @Test
    void grapeRedGet() {
        ResponseEntity<String> response = apiRequest("/grape/red", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getGrapes().forEach(grape -> assertEquals("Red", grape.getColor()));
        assertEquals(122, myWineCellar.getGrapes().size());
    }

    @Test
    void grapeWhiteGet() {
        ResponseEntity<String> response = apiRequest("/grape/white", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getGrapes().forEach(grape -> assertEquals("White", grape.getColor()));
        assertEquals(99, myWineCellar.getGrapes().size());
    }

    @Test
    void grapeDetailsGet() {
        ResponseEntity<String> response = apiRequest("/grape/albarino", null, HttpMethod.GET);
        assertEquals(200, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        GrapeDto grape = myWineCellar.getGrapes().get(0);
        assertEquals("Albarino", grape.getName());
        assertEquals("albarino", grape.getKey());
    }

    @Test
    void grapeEdit() {
        ResponseEntity<String> response = apiRequest("/grape/4/edit", jsonBody(), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getGrapes().forEach(grape -> {
            assertNotNull(grape.getDescription());
            assertNotNull(grape.getWeblink());
        });
    }
}
