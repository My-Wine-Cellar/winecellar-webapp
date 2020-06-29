/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ClosureDto;
import info.mywinecellar.dto.ColorDto;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.dto.ShapeDto;
import info.mywinecellar.dto.TypeDto;
import info.mywinecellar.dto.WineDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * MyWineCellar
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName(value = "mywinecellar")
public class MyWineCellar implements Serializable {

    @JsonSerialize(using = GenericSerializer.class)
    private List<AreaDto> areas;

    @JsonSerialize(using = GenericSerializer.class)
    private List<CountryDto> countries;

    @JsonSerialize(using = GenericSerializer.class)
    private List<GrapeDto> grapes;

    @JsonSerialize(using = GenericSerializer.class)
    private List<ProducerDto> producers;

    @JsonSerialize(using = GenericSerializer.class)
    private List<RegionDto> regions;

    @JsonSerialize(using = GenericSerializer.class)
    private List<WineDto> wines;

    @JsonSerialize(using = GenericSerializer.class)
    private List<ClosureDto> closures;

    @JsonSerialize(using = GenericSerializer.class)
    private List<ColorDto> colors;

    @JsonSerialize(using = GenericSerializer.class)
    private List<ShapeDto> shapes;

    @JsonSerialize(using = GenericSerializer.class)
    private List<TypeDto> types;

    /**
     * Constructor
     */
    MyWineCellar() {
    }

    /**
     * Get the areas
     *
     * @return The list
     */
    public List<AreaDto> getAreas() {
        return areas;
    }

    /**
     * Get the countries
     *
     * @return The list
     */
    public List<CountryDto> getCountries() {
        return countries;
    }

    /**
     * Get the grapes
     *
     * @return The list
     */
    public List<GrapeDto> getGrapes() {
        return grapes;
    }

    /**
     * Get the producers
     *
     * @return The list
     */
    public List<ProducerDto> getProducers() {
        return producers;
    }

    /**
     * Get the regions
     *
     * @return The list
     */
    public List<RegionDto> getRegions() {
        return regions;
    }

    /**
     * Get the wines
     *
     * @return The list
     */
    public List<WineDto> getWines() {
        return wines;
    }

    /**
     * Get the closures
     *
     * @return The list
     */
    public List<ClosureDto> getClosures() {
        return closures;
    }

    /**
     * Get the colors
     *
     * @return The list
     */
    public List<ColorDto> getColors() {
        return colors;
    }

    /**
     * Get the shapes
     *
     * @return The list
     */
    public List<ShapeDto> getShapes() {
        return shapes;
    }

    /**
     * Get the types
     *
     * @return The list
     */
    public List<TypeDto> getTypes() {
        return types;
    }

    void addArea(AreaDto a) {
        if (areas == null) {
            areas = new ArrayList<>();
        }
        areas.add(a);
    }

    void addAreas(List<AreaDto> dtos) {
        if (areas == null) {
            areas = new ArrayList<>();
        }
        areas.addAll(dtos);
    }

    boolean hasArea(Long id) {
        if (areas != null) {
            for (AreaDto a : areas) {
                if (a.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addCountry(CountryDto c) {
        if (countries == null) {
            countries = new ArrayList<>();
        }
        countries.add(c);
    }

    void addCountries(List<CountryDto> dtos) {
        if (countries == null) {
            countries = new ArrayList<>();
        }
        countries.addAll(dtos);
    }

    boolean hasCountry(Long id) {
        if (countries != null) {
            for (CountryDto c : countries) {
                if (c.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addGrape(GrapeDto g) {
        if (grapes == null) {
            grapes = new ArrayList<>();
        }
        grapes.add(g);
    }

    void addGrapes(List<GrapeDto> dtos) {
        if (grapes == null) {
            grapes = new ArrayList<>();
        }
        grapes.addAll(dtos);
    }

    boolean hasGrape(Long id) {
        if (grapes != null) {
            for (GrapeDto g : grapes) {
                if (g.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addProducer(ProducerDto p) {
        if (producers == null) {
            producers = new ArrayList<>();
        }
        producers.add(p);
    }

    void addProducers(List<ProducerDto> dtos) {
        if (producers == null) {
            producers = new ArrayList<>();
        }
        producers.addAll(dtos);
    }

    boolean hasProducer(Long id) {
        if (producers != null) {
            for (ProducerDto p : producers) {
                if (p.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addRegion(RegionDto r) {
        if (regions == null) {
            regions = new ArrayList<>();
        }
        regions.add(r);
    }

    void addRegions(List<RegionDto> dtos) {
        if (regions == null) {
            regions = new ArrayList<>();
        }
        regions.addAll(dtos);
    }

    boolean hasRegion(Long id) {
        if (regions != null) {
            for (RegionDto r : regions) {
                if (r.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addWine(WineDto w) {
        if (wines == null) {
            wines = new ArrayList<>();
        }
        wines.add(w);
    }

    void addWines(List<WineDto> dtos) {
        if (wines == null) {
            wines = new ArrayList<>();
        }
        wines.addAll(dtos);
    }

    boolean hasWine(Long id) {
        if (wines != null) {
            for (WineDto w : wines) {
                if (w.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addClosure(ClosureDto c) {
        if (closures == null) {
            closures = new ArrayList<>();
        }
        closures.add(c);
    }

    void addClosures(List<ClosureDto> dtos) {
        if (closures == null) {
            closures = new ArrayList<>();
        }
        closures.addAll(dtos);
    }

    boolean hasClosure(Long id) {
        if (closures != null) {
            for (ClosureDto c : closures) {
                if (c.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addColor(ColorDto c) {
        if (colors == null) {
            colors = new ArrayList<>();
        }
        colors.add(c);
    }

    void addColors(List<ColorDto> dtos) {
        if (colors == null) {
            colors = new ArrayList<>();
        }
        colors.addAll(dtos);
    }

    boolean hasColor(Long id) {
        if (colors != null) {
            for (ColorDto c : colors) {
                if (c.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addShape(ShapeDto s) {
        if (shapes == null) {
            shapes = new ArrayList<>();
        }
        shapes.add(s);
    }

    void addShapes(List<ShapeDto> dtos) {
        if (shapes == null) {
            shapes = new ArrayList<>();
        }
        shapes.addAll(dtos);
    }

    boolean hasShape(Long id) {
        if (shapes != null) {
            for (ShapeDto s : shapes) {
                if (s.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addType(TypeDto t) {
        if (types == null) {
            types = new ArrayList<>();
        }
        types.add(t);
    }

    void addTypes(List<TypeDto> dtos) {
        if (types == null) {
            types = new ArrayList<>();
        }
        types.addAll(dtos);
    }

    boolean hasType(Long id) {
        if (types != null) {
            for (TypeDto t : types) {
                if (t.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

}
