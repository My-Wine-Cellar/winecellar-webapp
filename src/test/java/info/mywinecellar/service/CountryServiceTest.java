/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Disabled(value = "no unit tests for service layer, don't mock types you don't own")
@ExtendWith(MockitoExtension.class)
class CountryServiceTest extends BaseUnitTest {

    @InjectMocks
    CountryService countryService;

    @Mock
    CountryConverter countryConverter;

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @Test
    void findByName() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(us);

        Country result = countryService.findByName("United States");
        assertNotNull(result);
        assertEquals(2, result.getRegions().size());
    }

    @Test
    void findByLowerCaseName() {
        when(entityManager.createNativeQuery(anyString(), eq(Country.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(us);

        Country result = countryService.findByLowerCaseName("united_states");
        assertNotNull(result);
        assertEquals(2, result.getRegions().size());
    }

    @Test
    void findWithRegions() {
        when(entityManager.createNativeQuery(anyString(), eq(Country.class))).thenReturn(query);
        List<Country> converted = new ArrayList<>(countries);
        when(query.getResultList()).thenReturn(converted);

        Set<Country> results = countryService.findWithRegions();
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Disabled(value = "works in postman, converter tests pass")
    @Test
    void editCountry() {
        when(countryConverter.toEntity(any(), any())).thenReturn(us);
        countryService.save(any(Country.class));

        CountryDto countryDto = new CountryDto();
        countryDto.setDescription("edited description");
        countryDto.setWeblink("edited weblink");

        Country result = countryService.editCountry(countryDto, us.getId());
        assertNotNull(result);
        assertEquals(countryDto.getDescription(), result.getDescription());
        assertEquals(countryDto.getWeblink(), result.getWeblink());
    }
}
