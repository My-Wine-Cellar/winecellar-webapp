/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CountryConverterUnitTest extends BaseUnitTest {

    CountryDto dto;

    @BeforeEach
    void setUp() {
        dto = new CountryDto();
        dto.setName("dto");
        dto.setFlag("dto flag");
        dto.setDescription("dto description");
        dto.setWeblink("dto weblink");
    }

    @Test
    void toDto() {
        dto = CountryConverter.toDto(us);

        assertNotNull(dto);
        assertEquals(us.getName(), dto.getName());
        assertEquals("united_states", dto.getKey());
    }

    @Test
    void toDtoNullCountry() {
        assertThat(CountryConverter.toDto((Country) null)).isNull();
    }

    @Test
    void testToDtoList() {
        List<CountryDto> result = CountryConverter.toDto(countries);
        CountryDto countryDto = result.get(0);
        assertEquals("Italy", countryDto.getName());
        assertEquals("italy", countryDto.getKey());
        assertEquals(2, result.size());
    }

    @Test
    void testToDtoListSorted() {
        List<CountryDto> result = CountryConverter.toDto(countries);
        assertThat(result.get(0).getName()).isEqualTo("Italy").isNotEqualTo("United States");
        assertThat(result.get(1).getName()).isEqualTo("United States").isNotEqualTo("Italy");
    }

    @Test
    void testToDtoNullList() {
        assertThat(CountryConverter.toDto((Set<Country>) null)).isEmpty();
    }

    @Test
    void toEntity() {
        Country result = CountryConverter.toEntity(new Country(), dto);
        assertNotNull(result);
        // on conversion name and flag should not change
        assertNotEquals(dto.getName(), result.getName());
        assertNotEquals(dto.getFlag(), result.getFlag());
        // on conversion only the description and weblink can change
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getWeblink(), result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Country result = CountryConverter.toEntity(null, dto);
        assertNotNull(result);
        assertEquals("dto", result.getName());
        assertEquals("dto description", result.getDescription());
        assertEquals("dto weblink", result.getWeblink());
        assertEquals("dto flag", result.getFlag());
    }

}
