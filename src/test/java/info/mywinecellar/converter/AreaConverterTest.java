/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.BaseUnitTest;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.model.Area;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AreaConverterTest extends BaseUnitTest {

    private AreaDto areaDto;

    @BeforeEach
    void setUp() {
        areaDto = new AreaDto();
        areaDto.setName("Area Dto");
        areaDto.setDescription("dto description");
        areaDto.setWeblink("dto weblink");

        Area california = new Area("California", "", "");
        california.setRegions(Set.of());
        california.setPrimaryGrapes(Set.of());
        caliAreas.add(california);
    }

    @Test
    void toDto() {
        areaDto = AreaConverter.toDto(napa);
        assertThat(areaDto).isNotNull();

        assertThat(napa.getName()).isEqualTo(areaDto.getName());
        assertThat(napa.getName()).isEqualTo(areaDto.getName());
        assertThat(areaDto.getKey()).isEqualTo("napa_valley_ava");
        assertThat(areaDto.getPrimaryGrapes()).hasSize(2);
        assertThat(areaDto.getRegions()).hasSize(1);
    }

    @Test
    void toDtoNull() {
        assertThat(AreaConverter.toDto((Area) null)).isNull();
    }

    @Test
    void toDtoList() {
        List<AreaDto> result = AreaConverter.toDto(caliAreas);
        assertThat(result).isNotNull().hasSize(3); 

        AreaDto dto = result.get(0);
        assertThat(dto).isNotNull(); 
        assertThat(dto.getName()).isNotNull().isEqualTo("California").isNotEqualTo("Napa Valley AVA"); 
    }

    @Test
    void toEntity() {
        Area result = AreaConverter.toEntity(napa, areaDto);
        assertThat(result).isNotNull();

        assertThat(areaDto.getName()).isNotEqualTo(result.getName());
        assertThat(areaDto.getDescription()).isEqualTo(result.getDescription());
        assertThat(areaDto.getWeblink()).isEqualTo(result.getWeblink());
    }

    @Test
    void toEntityNull() {
        Area result = AreaConverter.toEntity(null, areaDto);
        assertThat(result).isNotNull();

        assertThat(areaDto.getName()).isEqualTo(result.getName());
        assertThat(areaDto.getDescription()).isEqualTo(result.getDescription());
        assertThat(areaDto.getWeblink()).isEqualTo(result.getWeblink());
    }

}
