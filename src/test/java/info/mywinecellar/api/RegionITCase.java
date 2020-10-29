package info.mywinecellar.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegionITCase extends BaseITCase {

    @Test
    void regionEdit() {
        ResponseEntity<String> response = apiRequest("/region/47/edit", jsonBody(), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getRegions().forEach(region -> {
            assertNotNull(region.getDescription());
            assertNotNull(region.getWeblink());
        });
    }

    @Test
    void regionEdit_400() {
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> apiRequest("/region/47/edit", null, HttpMethod.PUT));
    }
}
