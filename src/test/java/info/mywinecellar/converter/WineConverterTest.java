/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.model.Wine;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WineConverterTest extends BaseUnitTest {

    @Inject
    WineConverter wineConverter;

    WineDto wineDto;

    @BeforeEach
    void setUp() {
        wineConverter = new WineConverter();

        wineDto = new WineDto();
        wineDto.setName("Wine Dto");
        wineDto.setSize(0.75f);
        wineDto.setVintage(2018);
    }

    @Test
    void toDto() {
        wineDto = wineConverter.toDto(opusOne2015);
        assertNotNull(wineDto);

        assertEquals(opusOne2015.getName(), wineDto.getName());
        assertEquals("opus_one", wineDto.getKey());
        assertEquals(2015, wineDto.getVintage());
    }

    @Test
    void toDtoNull() {
        assertThrows(IllegalStateException.class, () -> wineConverter.toDto((Wine) null));
    }

    @Test
    void toDtoList() {
        List<WineDto> result = wineConverter.toDto(wines);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        WineDto dto = result.iterator().next();
        assertNotNull(dto);
        assertEquals(opusOne2015.getName(), dto.getName());
    }

    @Test
    void toDtoEmptyList() {
        List<WineDto> result = wineConverter.toDto(Collections.emptySet());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toDtoListSorted() {
    }

    @Test
    void toDtoListNull() {
        assertThrows(IllegalStateException.class, () -> wineConverter.toDto((Set<Wine>) null));
    }

    @Test
    void toEntity() {
        Wine result = wineConverter.toEntity(opusOne2015, wineDto);
        assertNotNull(result);

        assertEquals(wineDto.getName(), result.getName());
        assertEquals(wineDto.getVintage(), result.getVintage());
        assertEquals(wineDto.getSize(), result.getSize());
    }

    @Test
    void toEntityNull() {
        Wine result = wineConverter.toEntity(null, wineDto);
        assertNotNull(result);

        assertEquals(wineDto.getName(), result.getName());
        assertEquals(wineDto.getVintage(), result.getVintage());
        assertEquals(wineDto.getSize(), result.getSize());
    }
}
