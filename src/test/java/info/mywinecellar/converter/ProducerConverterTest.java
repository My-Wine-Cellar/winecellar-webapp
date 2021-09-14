/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProducerConverterTest extends BaseUnitTest {

    ProducerDto producerDto;

    @BeforeEach
    void setUp() {
        producerDto = new ProducerDto();
        producerDto.setName("Producer Dto");
        producerDto.setDescription("dto description");
        producerDto.setWebsite("dto website");
        producerDto.setPhone("1234567890");
        producerDto.setFax("1234567890");
        producerDto.setEmail("test@test.com");
    }

    @Test
    void toDto() {
        producerDto = ProducerConverter.toDto(opusOne);
        assertNotNull(producerDto);

        assertEquals(opusOne.getName(), producerDto.getName());
        assertEquals("opus_one", producerDto.getKey());
        assertEquals(1, producerDto.getAreas().size());
    }

    @Test
    void toDtoNull() {
        assertThrows(IllegalStateException.class, () -> ProducerConverter.toDto((Producer) null));
    }

    @Test
    void toDtoList() {
        List<ProducerDto> result = ProducerConverter.toDto(producers);

        assertNotNull(result);
        assertEquals(1, result.size());

        ProducerDto dto = result.get(0);
        assertNotNull(dto);
        assertEquals(opusOne.getName(), dto.getName());
    }

    @Test
    void toDtoListNull() {
        assertThrows(IllegalStateException.class, () -> ProducerConverter.toDto((Set<Producer>) null));
    }

    @Test
    void toEntity() {
        Producer result = ProducerConverter.toEntity(opusOne, producerDto);
        assertNotNull(result);

        assertEquals(producerDto.getName(), result.getName());
        assertEquals(producerDto.getDescription(), result.getDescription());
        assertEquals(producerDto.getWebsite(), result.getWebsite());
        assertEquals(producerDto.getPhone(), result.getPhone());
        assertEquals(producerDto.getFax(), result.getFax());
        assertEquals(producerDto.getEmail(), result.getEmail());
    }

    @Test
    void toEntityNull() {
        Producer result = ProducerConverter.toEntity(null, producerDto);
        assertNotNull(result);

        assertEquals(producerDto.getName(), result.getName());
        assertEquals(producerDto.getDescription(), result.getDescription());
        assertEquals(producerDto.getWebsite(), result.getWebsite());
        assertEquals(producerDto.getPhone(), result.getPhone());
        assertEquals(producerDto.getFax(), result.getFax());
        assertEquals(producerDto.getEmail(), result.getEmail());
    }
}
