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

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrapeConverterTest extends BaseUnitTest {

    @Inject
    GrapeConverter grapeConverter;

    GrapeDto grapeDto;

    @BeforeEach
    void setUp() {
        grapeConverter = new GrapeConverter();

        grapeDto = new GrapeDto();
        grapeDto.setName("Grape dto");
        grapeDto.setDescription("dto description");
        grapeDto.setWeblink("dto weblink");
        grapeDto.setColor("orange");
    }

    @Test
    void toDto() {
        grapeDto = grapeConverter.toDto(pinotNoir);
        assertNotNull(grapeDto);

        assertEquals(pinotNoir.getName(), grapeDto.getName());
        assertEquals(pinotNoir.getColor(), grapeDto.getColor());
    }

    @Test
    void toDtoNull() {
        assertThrows(IllegalStateException.class, () -> grapeConverter.toDto((Grape) null));
    }

    @Test
    void testToDtoList() {
        List<GrapeDto> result = grapeConverter.toDto(grapes);

        assertEquals(2, result.size());
        GrapeDto dto = result.get(0);
        assertNotNull(dto);
        assertEquals(albarino.getName(), dto.getName());
    }

    @Test
    void testToDtoListSorted() {
        List<GrapeDto> result = grapeConverter.toDto(grapes);
        assertEquals("Albarino", result.get(0).getName());
        assertEquals("albarino", result.get(0).getKey());
        assertEquals("Pinot Noir", result.get(1).getName());
        assertEquals("pinot_noir", result.get(1).getKey());
    }

    @Test
    void toDtoListNull() {
        assertThrows(IllegalStateException.class, () -> grapeConverter.toDto((Set<Grape>) null));
    }

    @Test
    void toEntity() {
        Grape result = grapeConverter.toEntity(pinotNoir, grapeDto);
        assertNotNull(result);

        assertNotEquals(grapeDto.getName(), result.getName());

        assertEquals(grapeDto.getDescription(), result.getDescription());
        assertEquals(grapeDto.getWeblink(), result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Grape result = grapeConverter.toEntity(null, grapeDto);
        assertNotNull(result);

        assertEquals(grapeDto.getName(), result.getName());
        assertEquals(grapeDto.getDescription(), result.getDescription());
        assertEquals(grapeDto.getWeblink(), result.getWeblink());
    }
}
