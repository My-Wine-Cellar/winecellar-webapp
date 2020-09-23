/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.service.ClosureService;
import info.mywinecellar.service.ColorService;
import info.mywinecellar.service.ShapeService;
import info.mywinecellar.service.TypeService;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RootApiUnitTest extends BaseUnitTest {

    @InjectMocks
    RootRestController rootRestController;

    @Mock
    ClosureService closureService;

    @Mock
    ColorService colorService;

    @Mock
    ShapeService shapeService;

    @Mock
    TypeService typeService;

    @Test
    void dataApiRootGet() {
        when(countryService.findWithRegions()).thenReturn(countries);

        MyWineCellar json = rootRestController.dataApiRootGet();
        assertNotNull(json);
        assertEquals(2, json.getCountries().size());
        assertEquals("italy", json.getCountries().get(0).getKey());
    }

    @Test
    void emptyCountries() {
        when(countryService.findWithRegions()).thenReturn(Collections.emptySet());

        MyWineCellar result = rootRestController.dataApiRootGet();
        assertEquals(0, result.getCountries().size());
    }

    @Test
    void countryByNameGet() {
        when(countryService.findByLowerCaseName(anyString())).thenReturn(us);

        MyWineCellar result = rootRestController.countryByNameGet(us.getName());
        assertNotNull(result);
        assertEquals("united_states", result.getCountries().get(0).getKey());
        assertEquals("california", result.getRegions().get(0).getKey());
    }

    @Test
    void regionByNameGet() {
        when(countryService.findByLowerCaseName(anyString())).thenReturn(us);
        when(regionService.findByLowerCaseName(anyString(), anyLong())).thenReturn(cali);

        MyWineCellar result = rootRestController.regionByNameGet(us.getName(), cali.getName());
        assertNotNull(result);
        assertEquals(2, result.getAreas().size());
        assertEquals("napa_valley_ava", result.getAreas().get(0).getKey());
        assertEquals("california", result.getRegions().get(0).getKey());
        assertEquals(1, result.getCountries().size());
        assertEquals(1, result.getRegions().size());
    }

    @Test
    void areaByNameGet() {
        when(countryService.findByLowerCaseName(anyString())).thenReturn(us);
        when(regionService.findByLowerCaseName(anyString(), anyLong())).thenReturn(cali);
        when(areaService.findByLowerCaseName(anyString())).thenReturn(napa);

        MyWineCellar result = rootRestController.areaByNameGet(us.getName(), cali.getName(), napa.getName());
        assertNotNull(result);
        assertEquals(1, result.getCountries().size());
        assertEquals(1, result.getRegions().size());
        assertEquals(1, result.getProducers().size());
        assertEquals("napa_valley_ava", result.getAreas().get(0).getKey());
    }

    @Test
    void producerByNameGet() {
        when(countryService.findByLowerCaseName(anyString())).thenReturn(us);
        when(regionService.findByLowerCaseName(anyString(), anyLong())).thenReturn(cali);
        when(areaService.findByLowerCaseName(anyString())).thenReturn(napa);
        when(producerService.findByLowerCaseName(anyString())).thenReturn(opusOne);

        MyWineCellar result = rootRestController.producerByNameGet(us.getName(), cali.getName(),
                napa.getName(), opusOne.getName());
        assertNotNull(result);

        assertEquals(1, result.getCountries().size());
        assertEquals(1, result.getRegions().size());
        assertEquals(1, result.getAreas().size());
        assertEquals(1, result.getProducers().size());
        assertEquals("opus_one", result.getProducers().get(0).getKey());
        assertEquals(ProducerDto.class, result.getProducers().get(0).getClass());
    }

    @Test
    void wineByNameGet() {
        when(countryService.findByLowerCaseName(anyString())).thenReturn(us);
        when(regionService.findByLowerCaseName(anyString(), anyLong())).thenReturn(cali);
        when(areaService.findByLowerCaseName(anyString())).thenReturn(napa);
        when(producerService.findByLowerCaseName(anyString())).thenReturn(opusOne);
        when(wineService.findByLowerCaseName(anyString())).thenReturn(opusOne2015);
        when(closureService.findById(anyLong())).thenReturn(closure);
        when(colorService.findById(anyLong())).thenReturn(color);
        when(shapeService.findById(anyLong())).thenReturn(shape);
        when(typeService.findById(anyLong())).thenReturn(type);

        MyWineCellar result = rootRestController.wineByNameGet(us.getName(), cali.getName(), napa.getName(),
                opusOne.getName(), opusOne2015.getName(), opusOne2015.getVintage(), opusOne2015.getSize());
        assertNotNull(result);

        assertEquals(1, result.getCountries().size());
        assertEquals(1, result.getRegions().size());
        assertEquals(1, result.getAreas().size());
        assertEquals(1, result.getProducers().size());
        assertEquals("Opus One", result.getWines().get(0).getName());
        assertEquals("opus_one", result.getWines().get(0).getKey());
        assertEquals(WineDto.class, result.getWines().get(0).getClass());
    }
}
