package info.mywinecellar.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

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

    @Disabled(value = "need to fix extraneous Spring logging")
    @Test
    void regionEdit_500() {
        assertThrows(HttpServerErrorException.class,
                () -> apiRequest("/region/999/edit", jsonBody(), HttpMethod.PUT));
    }
}
