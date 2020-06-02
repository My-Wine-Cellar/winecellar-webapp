/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class RegionTest {

    Region regionWithCountry;
    Region regionWithoutCountry;
    Country country;

    @BeforeEach
    void setUp() {
        regionWithCountry = new Region("testname", "teststring", "testurl", country);
        regionWithoutCountry = new Region("testname", "teststring", "testurl");
    }

    @Test()
    void equalsReturnsFalseIfCountryMissing() throws Exception {
        Assert.assertFalse(regionWithCountry.equals(regionWithoutCountry));
    }

    @Test()
    void equalsReturnsTrueIfSame() throws Exception {
        Assert.assertTrue(regionWithCountry.equals(regionWithCountry));
    }
}
