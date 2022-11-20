/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.WineDto;
import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WineITCase extends BaseITCase {

    @Test
    @Order(1)
    void wineNew() throws JsonProcessingException {
        WineDto body = new WineDto();
        body.setName("New Wine");
        body.setVintage(2020);
        body.setSize(0.75f);

        MyWineCellar response = apiRequest("/wine/new?producerId=1", objectMapper.writeValueAsString(body), HttpMethod.POST);
        assertThat(response).isNotNull();

        response.getWines().forEach(wine -> {
            assertThat(wine.getKey()).isEqualTo("new_wine");
            assertThat(wine.getName()).isEqualTo("New Wine");
            assertThat(wine.getVintage()).isEqualTo(2020);
            assertThat(wine.getSize()).isEqualTo(0.75f);
        });
    }

    @Test
    @Order(2)
    void wineEdit() throws JsonProcessingException {
        WineDto body = new WineDto();
        body.setName("Edit Wine");
        body.setVintage(2010);
        body.setSize(3.0f);

        MyWineCellar response = apiRequest("/wine/4/edit", objectMapper.writeValueAsString(body), HttpMethod.PUT);
        assertThat(response).isNotNull();

        assertThat(response.getWines()).isNotEmpty();
        response.getWines().forEach(wine -> {
            assertThat(wine.getKey()).isEqualTo("edit_wine");
            assertThat(wine.getName()).isEqualTo("Edit Wine");
            assertThat(wine.getVintage()).isEqualTo(2010);
        });
    }

    @Test
    void wineEdit_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(() -> apiRequest("/wine/4/edit", null, HttpMethod.PUT))
                .withMessageContaining("wine request for id 4 was null");
    }

    @Test
    void wineImage() {
        MyWineCellar response = apiImageRequest("/wine/1/image");
        assertThat(response).isNotNull();

        WineDto wine = response.getWines().get(0);
        assertThat(wine.getImage()).isNotNull();
    }
}
