/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.model.Grape;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GrapeConverterTest extends BaseUnitTest {

    GrapeDto grapeDto;

    @BeforeEach
    void setUp() {
        grapeDto = new GrapeDto();
        grapeDto.setName("Grape dto");
        grapeDto.setDescription("dto description");
        grapeDto.setWeblink("dto weblink");
        grapeDto.setColor("orange");
    }

    @Test
    void toDto() {
        grapeDto = GrapeConverter.toDto(pinotNoir);
        assertNotNull(grapeDto);

        assertEquals(pinotNoir.getName(), grapeDto.getName());
        assertEquals(pinotNoir.getColor(), grapeDto.getColor());
    }

    @Test
    void toDtoNull() {
        assertThat(GrapeConverter.toDto((Grape) null)).isNull();
    }

    @Test
    void testToDtoList() {
        List<GrapeDto> result = GrapeConverter.toDto(grapes);

        assertEquals(2, result.size());
        GrapeDto dto = result.get(0);
        assertNotNull(dto);
        assertEquals(albarino.getName(), dto.getName());
    }

    @Test
    void testToDtoListSorted() {
        List<GrapeDto> result = GrapeConverter.toDto(grapes);
        assertEquals("Albarino", result.get(0).getName());
        assertEquals("albarino", result.get(0).getKey());
        assertEquals("Pinot Noir", result.get(1).getName());
        assertEquals("pinot_noir", result.get(1).getKey());
    }

    @Test
    void toDtoListNull() {
        assertThat(GrapeConverter.toDto((Set<Grape>) null)).isEmpty();
    }

    @Test
    void toEntity() {
        Grape result = GrapeConverter.toEntity(pinotNoir, grapeDto);
        assertNotNull(result);

        assertNotEquals(grapeDto.getName(), result.getName());

        assertEquals(grapeDto.getDescription(), result.getDescription());
        assertEquals(grapeDto.getWeblink(), result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Grape result = GrapeConverter.toEntity(null, grapeDto);
        assertNotNull(result);

        assertEquals(grapeDto.getName(), result.getName());
        assertEquals(grapeDto.getDescription(), result.getDescription());
        assertEquals(grapeDto.getWeblink(), result.getWeblink());
    }
}
