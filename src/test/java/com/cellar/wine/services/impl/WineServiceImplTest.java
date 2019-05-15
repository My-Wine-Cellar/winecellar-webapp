package com.cellar.wine.services.impl;

import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.WineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class WineServiceImplTest {

    @Mock
    WineRepository wineRepository;

    @InjectMocks
    WineServiceImpl wineServiceImpl;

    private Wine returnWine;

    @BeforeEach
    void setUp() {
        returnWine = Wine.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Wine> wines = new HashSet<>();
        wines.add(Wine.builder().id(1L).build());
        wines.add(Wine.builder().id(2L).build());

        assertNotNull(wines);
        assertEquals(2, wines.size());
    }

    @Test
    void findById() {
        assertNotNull(returnWine);
    }

}