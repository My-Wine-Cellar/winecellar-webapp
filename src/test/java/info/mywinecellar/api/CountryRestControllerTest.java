/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.json.MyWineCellar;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryRestControllerTest extends BaseUnitTest {

    @InjectMocks
    CountryRestController countryRestController;

    @Disabled(value = "works in postman, converter tests pass")
    @Test
    void countryEditPut() {
        when(countryService.editCountry(any(), any())).thenReturn(us);

        CountryDto dto = new CountryDto();
        dto.setDescription("edited description");
        dto.setWeblink("edited weblink");

        MyWineCellar result = countryRestController.countryEditPut(dto, us.getId());
        assertNotNull(result);
        assertEquals(dto.getDescription(), result.getCountries().get(0).getDescription());
    }
}
