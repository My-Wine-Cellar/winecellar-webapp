/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GrapeITCase extends BaseITCase {

    @Test
    void grapeRedGet() {
        MyWineCellar response = apiRequest("/grape/red", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        response.getGrapes().forEach(grape -> assertEquals("Red", grape.getColor()));
        assertThat(response.getGrapes()).hasSize(122);
    }

    @Test
    void grapeWhiteGet() {
        MyWineCellar response = apiRequest("/grape/white", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getGrapes()).isNotEmpty();
        response.getGrapes().forEach(grape -> assertEquals("White", grape.getColor()));
        assertThat(response.getGrapes()).hasSize(99);
    }

    @Test
    void grapeDetailsGet() {
        MyWineCellar response = apiRequest("/grape/albarino", null, HttpMethod.GET);
        assertThat(response).isNotNull();

        assertThat(response.getGrapes()).isNotEmpty();

        GrapeDto grape = response.getGrapes().get(0);
        assertThat(grape.getName()).isEqualTo("Albarino");
        assertThat(grape.getKey()).isEqualTo("albarino");
    }

    @Test
    void grapeEdit() {
        MyWineCellar response = apiRequest("/grape/4/edit", jsonBody(), HttpMethod.PUT);
        assertThat(response).isNotNull();

        assertThat(response.getGrapes()).isNotEmpty();
        response.getGrapes().forEach(grape -> {
            assertThat(grape.getDescription()).isNotNull();
            assertThat(grape.getWeblink()).isNotNull();
        });
    }

    @Test
    void grapeEdit_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.BadRequest.class)
                .isThrownBy(() -> apiRequest("/grape/4/edit", null, HttpMethod.PUT))
                .withMessageContaining("grape request for id 4 was null");
    }
}
