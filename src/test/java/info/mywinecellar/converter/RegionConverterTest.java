/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertThat(RegionConverter.toDto((Region) null)).isNull();
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
        assertThat(result.get(0).getName()).isEqualTo("California").isNotEqualTo("Washington");
        assertThat(result.get(1).getName()).isEqualTo("Washington").isNotEqualTo("California");
    }

    @Test
    void toDtoNullList() {
        assertThat(RegionConverter.toDto(Collections.emptySet())).isEmpty();
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
