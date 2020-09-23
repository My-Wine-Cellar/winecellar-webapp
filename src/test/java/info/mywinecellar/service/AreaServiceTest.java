/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.model.Area;

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
class AreaServiceTest extends BaseUnitTest {

    @InjectMocks
    AreaService areaService;

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @Test
    void findByName() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(napa);

        Area result = areaService.findByName(anyString());
        assertNotNull(result);
        assertEquals(1, result.getRegions().size());
        assertEquals(2, result.getPrimaryGrapes().size());
        assertEquals(1, result.getProducers().size());
    }

    @Test
    void findByLowerCaseName() {
        when(entityManager.createNativeQuery(anyString(), eq(Area.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(sonoma);

        Area result = areaService.findByLowerCaseName("sonoma_valley_ava");
        assertNotNull(result);
        assertEquals(1, result.getRegions().size());
        assertEquals(2, result.getPrimaryGrapes().size());
    }

    @Test
    void editArea() {
    }

    @Test
    void addProducer() {
    }
}
