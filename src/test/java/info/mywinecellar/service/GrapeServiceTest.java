/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.model.Grape;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

@ExtendWith(MockitoExtension.class)
class GrapeServiceTest extends BaseUnitTest {

    @InjectMocks
    GrapeService grapeService;

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @Test
    void findByName() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(pinotNoir);

        Grape result = grapeService.findByName(anyString());
        assertNotNull(result);
        assertEquals(2, result.getAreas().size());
        assertEquals("Red", result.getColor());
    }

    @Test
    void getWhiteGrapes() {
    }

    @Test
    void getRedGrapes() {
    }

    @Test
    void findByLowerCaseName() {
        when(entityManager.createNativeQuery(anyString(), eq(Grape.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(pinotNoir);

        Grape result = grapeService.findByLowerCaseName("pinot_noir");
        assertNotNull(result);
        assertEquals("Pinot Noir", result.getName());
    }

    @Test
    void editGrape() {
    }
}
