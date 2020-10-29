package info.mywinecellar.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

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

    @Test
    void countryEdit_400() {
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> apiRequest("/country/185/edit", null, HttpMethod.PUT));
    }
}
