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

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryConverterUnitTest extends BaseUnitTest {

    @Inject
    CountryConverter countryConverter;

    CountryDto dto;

    @BeforeEach
    void setUp() {
        countryConverter = new CountryConverter();

        dto = new CountryDto();
        dto.setName("dto");
        dto.setFlag("dto flag");
        dto.setDescription("dto description");
        dto.setWeblink("dto weblink");
    }

    @Test
    void toDto() {
        dto = countryConverter.toDto(us);

        assertNotNull(dto);
        assertEquals(us.getName(), dto.getName());
        assertEquals("united_states", dto.getKey());
    }

    @Test()
    void toDtoNullCountry() {
        assertThrows(IllegalStateException.class, () -> countryConverter.toDto((Country) null));
    }

    @Test
    void testToDtoList() {
        List<CountryDto> result = countryConverter.toDto(countries);
        CountryDto countryDto = result.get(0);
        assertEquals("Italy", countryDto.getName());
        assertEquals("italy", countryDto.getKey());
        assertEquals(2, result.size());
    }

    @Test
    void testToDtoListSorted() {
        List<CountryDto> result = countryConverter.toDto(countries);
        assertEquals("Italy", result.get(0).getName());
        assertEquals("italy", result.get(0).getKey());
        assertEquals("United States", result.get(1).getName());
        assertEquals("united_states", result.get(1).getKey());
    }

    @Test
    void testToDtoNullList() {
        assertThrows(IllegalStateException.class, () -> countryConverter.toDto((Set<Country>) null));
    }

    @Test
    void toEntity() {
        Country result = countryConverter.toEntity(new Country(), dto);
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
        Country result = countryConverter.toEntity(null, dto);
        assertNotNull(result);
        assertEquals("dto", result.getName());
        assertEquals("dto description", result.getDescription());
        assertEquals("dto weblink", result.getWeblink());
        assertEquals("dto flag", result.getFlag());
    }

}
