package info.mywinecellar.converter;

import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.model.Barrel;
import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.GrapeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BarrelComponentConverterTest {

    BarrelComponentConverter converter;

    GrapeComponent grapeComponent = new GrapeComponent();

    @BeforeEach
    void setup(){
        converter = new BarrelComponentConverter();

        grapeComponent = new GrapeComponent();
        grapeComponent.setId(1L);
    }

    @Test
    void componentToDTO_Throws(){
        BarrelComponent barrelComponent = null;
        String exceptionMessage = "BarrelComponent is null";
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> converter.toDto(barrelComponent), exceptionMessage);

        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void componentToDTO_ReturnsBarrelDTO(){
        BarrelComponent barrelComponent = new BarrelComponent();
        barrelComponent.setId(1L);

        barrelComponent.setGrapeComponent(grapeComponent);

        Barrel barrel = new Barrel();
        barrel.setId(1L);
        barrelComponent.setBarrel(barrel);

        BarrelDto dto = converter.toDto(barrelComponent);

        assertEquals(barrelComponent.getGrapeComponent().getId(), dto.getGrapeComponentId());
        assertEquals(barrelComponent.getBarrel().getId(), dto.getBarrelId());
    }

    @Test
    void barrelSetToDTO_Throws(){
        Set<BarrelComponent> barrels = null;

        String exceptionMessage = "Barrel list is null";
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> converter.toDto(barrels), exceptionMessage);

        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void barrelToDTO_ReturnsList(){
        BarrelComponent barrelComponent1 = new BarrelComponent();
        barrelComponent1.setId(1L);
        barrelComponent1.setGrapeComponent(grapeComponent);
        byte percentage1 = 25;
        barrelComponent1.setPercentage(percentage1);

        Barrel barrel1 = new Barrel();
        barrel1.setId(1L);
        barrelComponent1.setBarrel(barrel1);

        BarrelComponent barrelComponent2 = new BarrelComponent();
        barrelComponent2.setId(2L);
        barrelComponent2.setGrapeComponent(grapeComponent);
        byte percentage2 = 40;
        barrelComponent2.setPercentage(percentage2);

        Barrel barrel2 = new Barrel();
        barrel2.setId(2L);
        barrelComponent2.setBarrel(barrel2);

        Set<BarrelComponent> barrels = new HashSet<>();
        barrels.add(barrelComponent1);
        barrels.add(barrelComponent2);

        List<BarrelDto> result = converter.toDto(barrels);

        assertEquals(result.size(), barrels.size());
        assertEquals(barrelComponent1.getId(), result.get(1).getBarrelId());
        assertEquals(barrelComponent2.getId(), result.get(0).getBarrelId());
    }

    @Test
    void componentToEntity_EntityIsNull(){
        byte percentage = 25;
        BarrelDto barrelDto = new BarrelDto();

        barrelDto.setGrapeComponentId(1L);

        barrelDto.setPercentage(percentage);
        barrelDto.setSize(2);
        barrelDto.setAging(10);
        barrelDto.setBarrelId(1L);

        BarrelComponent result = converter.toEntity(null, barrelDto);

        assertEquals(barrelDto.getGrapeComponentId(), result.getGrapeComponent().getId());
        assertEquals(barrelDto.getPercentage(), result.getPercentage());
        assertEquals(barrelDto.getSize(), result.getSize());
        assertEquals(barrelDto.getBarrelId(), result.getBarrel().getId());
        assertEquals(barrelDto.getAging(), result.getAging());
    }

    @Test
    void componentToEntity_EntityIsNotNull(){
        byte percentage = 25;
        BarrelDto barrelDto = new BarrelDto();

        barrelDto.setGrapeComponentId(1L);

        barrelDto.setPercentage(percentage);
        barrelDto.setSize(2);
        barrelDto.setAging(10);
        barrelDto.setBarrelId(1L);

        BarrelComponent entity = new BarrelComponent();

        BarrelComponent result = converter.toEntity(entity, barrelDto);

        assertEquals(barrelDto.getGrapeComponentId(), result.getGrapeComponent().getId());
        assertEquals(barrelDto.getPercentage(), result.getPercentage());
        assertEquals(barrelDto.getSize(), result.getSize());
        assertEquals(barrelDto.getBarrelId(), result.getBarrel().getId());
        assertEquals(barrelDto.getAging(), result.getAging());
    }

    @Test
    void barrelDTOListToEntity_ReturnsList(){
        byte percentage = 25;
        BarrelDto barrelDto = new BarrelDto();

        barrelDto.setGrapeComponentId(1L);

        barrelDto.setPercentage(percentage);
        barrelDto.setSize(2);
        barrelDto.setAging(10);
        barrelDto.setBarrelId(1L);

        List<BarrelDto> barrelDtos = new ArrayList<>();
        barrelDtos.add(barrelDto);

        List<BarrelComponent> barrelComponents = converter.toEntity(barrelDtos);

        assertEquals(barrelDto.getId(), barrelComponents.get(0).getId());
    }
}