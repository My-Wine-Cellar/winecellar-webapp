/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.service.CountryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @InjectMocks
    CountryController controller;

    @Mock
    CountryService countryService;

    CountryDto country;

    @BeforeEach
    void setUp() {
    }

    @Test
    void countryEditGet() throws Exception {

    }

    @Test
    void countryEditPost() {
    }
}
