/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.model.Region;

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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Disabled(value = "no unit tests for service layer, don't mock types you don't own")
@ExtendWith(MockitoExtension.class)
class RegionServiceTest extends BaseUnitTest {

    @InjectMocks
    RegionService regionService;

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @Test
    void findByName() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(cali);

        Region result = regionService.findByName("California");
        assertNotNull(result);
        assertEquals("United States", result.getCountry().getName());
        assertEquals(2, result.getAreas().size());
    }

    @Test
    void findByLowerCaseName() {
        when(entityManager.createNativeQuery(anyString(), eq(Region.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(cali);

        Region result = regionService.findByLowerCaseName("california", 1L);
        assertNotNull(result);
        assertEquals("United States", result.getCountry().getName());
        assertEquals(2, result.getAreas().size());
    }

    @Test
    void editRegion() {
    }
}
