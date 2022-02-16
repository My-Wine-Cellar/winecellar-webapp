/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.model.Area;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AreaConverterTest extends BaseUnitTest {

    private AreaDto areaDto;

    @BeforeEach
    void setUp() {
        areaDto = new AreaDto();
        areaDto.setName("Area Dto");
        areaDto.setDescription("dto description");
        areaDto.setWeblink("dto weblink");
    }

    @Test
    void toDto() {
        areaDto = AreaConverter.toDto(napa);
        assertNotNull(areaDto);

        assertEquals(napa.getName(), areaDto.getName());
        assertEquals("napa_valley_ava", areaDto.getKey());
        assertEquals(2, areaDto.getPrimaryGrapes().size());
        assertEquals(1, areaDto.getRegions().size());
    }

    @Test
    void toDtoNull() {
        assertThrows(IllegalStateException.class, () -> AreaConverter.toDto((Area) null));
    }

    @Test
    void toDtoList() {
        List<AreaDto> result = AreaConverter.toDto(caliAreas);

        assertNotNull(result);
        assertEquals(2, result.size());

        AreaDto dto = result.get(0);
        assertNotNull(dto);
        assertEquals(napa.getName(), dto.getName());
    }

    @Test
    void toDtoListSorted() {
        List<AreaDto> result = AreaConverter.toDto(caliAreas);

        assertEquals("Napa Valley AVA", result.get(0).getName());
        assertEquals("Sonoma Valley AVA", result.get(1).getName());
    }

    @Test
    void toDtoListNull() {
        assertThrows(IllegalStateException.class, () -> AreaConverter.toDto((Set<Area>) null));
    }

    @Test
    void toEntity() {
        Area result = AreaConverter.toEntity(napa, areaDto);
        assertNotNull(result);

        assertNotEquals(areaDto.getName(), result.getName());

        assertEquals(areaDto.getDescription(), result.getDescription());
        assertEquals(areaDto.getWeblink(), result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Area result = AreaConverter.toEntity(null, areaDto);
        assertNotNull(result);

        assertEquals(areaDto.getName(), result.getName());
        assertEquals(areaDto.getDescription(), result.getDescription());
        assertEquals(areaDto.getWeblink(), result.getWeblink());
    }

}
