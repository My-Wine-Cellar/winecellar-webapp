/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ProducerITCase extends BaseITCase {

    @Test
    void producerEdit() throws JsonProcessingException {
        ProducerDto edit = new ProducerDto();
        edit.setName("Edited Producer");
        edit.setDescription("edited description");
        edit.setWebsite("edited weblink");
        edit.setPhone("1234567890");
        edit.setEmail("producer@gmail.com");

        MyWineCellar response = apiRequest("/producer/2/edit", objectMapper.writeValueAsString(edit), HttpMethod.PUT);
        assertThat(response).isNotNull();

        assertThat(response.getProducers()).isNotEmpty();
        ProducerDto producer = response.getProducers().get(0);
        assertThat(producer.getName()).isEqualTo("Edited Producer");
        assertThat(producer.getKey()).isEqualTo("edited_producer");
    }

    @Test
    void producerEdit_Exception() {
        assertThatExceptionOfType(HttpClientErrorException.BadRequest.class)
                .isThrownBy(() -> apiRequest("/producer/2/edit", null, HttpMethod.PUT))
                .withMessageContaining("producer request for id 2 was null");
    }

    @Test
    void producerImage() {
        MyWineCellar response = apiImageRequest("/producer/1/image");

        ProducerDto producer = response.getProducers().get(0);
        assertThat(producer.getImage()).isNotNull();
    }
}
