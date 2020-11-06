package info.mywinecellar.api;

import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegionITCase extends BaseITCase {

    @Test
    void regionEdit() {
        MyWineCellar response = apiRequest("/region/47/edit", jsonBody(), HttpMethod.PUT);
        assertThat(response).isNotNull();

        response.getRegions().forEach(region -> {
            assertNotNull(region.getDescription());
            assertNotNull(region.getWeblink());
        });
    }

    @Test
    void regionEdit_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.BadRequest.class)
                .isThrownBy(() -> apiRequest("/region/47/edit", null, HttpMethod.PUT))
                .withMessageContaining("region request for id 47 was null");
    }
}
