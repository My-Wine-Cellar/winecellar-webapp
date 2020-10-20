package info.mywinecellar.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryITCase extends BaseITCase {

    @Test
    void countryEdit() {
        ResponseEntity<String> response = apiRequest("/country/185/edit", jsonBody(), HttpMethod.PUT);
        assertEquals(202, response.getStatusCodeValue());

        myWineCellar = setupResponseObject(response);
        myWineCellar.getCountries().forEach(country -> {
            assertNotNull(country.getDescription());
            assertNotNull(country.getWeblink());
        });
    }

    @Disabled(value = "need to fix extraneous Spring logging")
    @Test
    void countryEdit_500() {
        assertThrows(HttpServerErrorException.class,
                () -> apiRequest("/country/999/edit", jsonBody(), HttpMethod.PUT));
    }
}
