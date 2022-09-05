package info.mywinecellar.api;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AreaITCase extends BaseITCase {

    @Test
    void areaByIdGet() {
        MyWineCellar response = apiRequest("/area/80", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        response.getAreas().forEach(area -> {
            assertEquals(1, area.getRegions().size());
            assertEquals(1, area.getPrimaryGrapes().size());
            assertEquals("Napa Valley AVA", area.getName());
            assertEquals("napa_valley_ava", area.getKey());
        });
    }

    @Test
    void areaEdit() {
        MyWineCellar response = apiRequest("/area/80/edit", jsonBody(), HttpMethod.PUT);
        assertThat(response).isNotNull();

        response.getAreas().forEach(area -> {
            assertNotNull(area.getDescription());
            assertNotNull(area.getWeblink());
        });
    }

    @Test
    void areaEdit_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.BadRequest.class)
                .isThrownBy(() -> apiRequest("/area/80/edit", null, HttpMethod.PUT))
                .withMessageContaining("area request for id 80 was null");
    }

    @Test
    void areaAddProducer() throws JsonProcessingException {
        ProducerDto request = new ProducerDto();
        request.setName("Opus Two");

        MyWineCellar response = apiRequest("/area/80/add-producer", objectMapper.writeValueAsString(request), HttpMethod.POST);
        assertThat(response).isNotNull();
    }

    @Test
    void areaAddGrape() {
        MyWineCellar response = apiRequest("/area/80/add-grape?grapeId=45&grapeId=23&grapeId=1", null, HttpMethod.PUT);
        assertThat(response).isNotNull();

        AreaDto dto = response.getAreas().get(0);
        assertThat(dto.getPrimaryGrapes()).hasSize(4);
    }

    @Test
    void areaAddGrape_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(() -> apiRequest("/area/80/add-grape", null, HttpMethod.PUT))
                .withMessageContaining("grape was null for area id 80");
    }

}
