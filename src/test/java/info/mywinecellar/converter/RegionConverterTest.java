/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegionConverterTest extends BaseUnitTest {

    RegionDto regionDto;

    @BeforeEach
    void setUp() {
        regionDto = new RegionDto();
        regionDto.setName("Region Dto");
        regionDto.setDescription("dto description");
        regionDto.setWeblink("dto weblink");
    }

    @Test
    void toDto() {
        regionDto = RegionConverter.toDto(cali);
        assertNotNull(regionDto);
        assertEquals("California", regionDto.getName());
        assertEquals("california", regionDto.getKey());
    }

    @Test
    void toDtoNullRegion() {
        assertThrows(IllegalStateException.class, () -> RegionConverter.toDto((Region) null));
    }

    @Test
    void toDtoList() {
        List<RegionDto> result = RegionConverter.toDto(usRegions);
        RegionDto dto = result.get(0);
        assertEquals(cali.getName(), dto.getName());
        assertEquals("california", dto.getKey());
    }

    @Test
    void toDoListSorted() {
        List<RegionDto> result = RegionConverter.toDto(usRegions);
        assertEquals("California", result.get(0).getName());
        assertEquals("california", result.get(0).getKey());
        assertEquals("Washington", result.get(1).getName());
        assertEquals("washington", result.get(1).getKey());
    }

    @Test
    void toDtoNullList() {
        assertThrows(IllegalStateException.class, () -> RegionConverter.toDto((Set<Region>) null));
    }

    @Test
    void toEntity() {
        Region result = RegionConverter.toEntity(cali, regionDto);
        assertNotNull(result);

        assertNotEquals(regionDto.getName(), result.getName());

        assertEquals(regionDto.getDescription(), result.getDescription());
        assertEquals(regionDto.getWeblink(), result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Region result = RegionConverter.toEntity(null, regionDto);
        assertNotNull(result);

        assertEquals(regionDto.getName(), result.getName());
        assertEquals(regionDto.getDescription(), result.getDescription());
        assertEquals(regionDto.getWeblink(), result.getWeblink());
    }
}
