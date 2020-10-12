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
import info.mywinecellar.dto.UserDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.wset.WSETDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * MyWineCellar
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName(value = "mywinecellar")
public class MyWineCellar implements Serializable {

    private List<AreaDto> areas;

    private List<CountryDto> countries;

    private List<GrapeDto> grapes;

    private List<ProducerDto> producers;

    private List<RegionDto> regions;

    private List<WineDto> wines;

    private List<UserDto> users;

    private List<WSETDto> wset;

    private List<ClosureDto> closures;

    private List<ColorDto> colors;

    private List<ShapeDto> shapes;

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
     * Get the users
     * @return The list
     */
    public List<UserDto> getUsers() {
        return users;
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

    /**
     * Get the WSET instances
     * @return The list
     */
    public List<WSETDto> getWset() {
        return wset;
    }

    /**
     * Find
     * @param what What to find
     * @param from The from object
     * @return The result
     */
    public Serializable find(String what, Serializable from) {
        Serializable result = null;

        if ("country".equals(what)) {
            if (from instanceof WSETDto) {
                WSETDto ws = (WSETDto) from;
                from = findWine(ws.getWineId());
            }
            if (from instanceof WineDto) {
                WineDto wine = (WineDto) from;
                from = findProducer(wine.getProducerId());
            }
            if (from instanceof ProducerDto) {
                ProducerDto producer = (ProducerDto) from;
                from = findArea(producer.getAreas().iterator().next());
            }
            if (from instanceof AreaDto) {
                AreaDto area = (AreaDto) from;
                from = findRegion(area.getRegions().iterator().next());
            }
            if (from instanceof RegionDto) {
                RegionDto region = (RegionDto) from;
                from = findCountry(region.getCountryId());
            }
            if (from instanceof CountryDto) {
                result = from;
            }
        } else if ("region".equals(what)) {
            if (from instanceof WSETDto) {
                WSETDto ws = (WSETDto) from;
                from = findWine(ws.getWineId());
            }
            if (from instanceof WineDto) {
                WineDto wine = (WineDto) from;
                from = findProducer(wine.getProducerId());
            }
            if (from instanceof ProducerDto) {
                ProducerDto producer = (ProducerDto) from;
                from = findArea(producer.getAreas().iterator().next());
            }
            if (from instanceof AreaDto) {
                AreaDto area = (AreaDto) from;
                from = findRegion(area.getRegions().iterator().next());
            }
            if (from instanceof RegionDto) {
                result = from;
            }
        } else if ("area".equals(what)) {
            if (from instanceof WSETDto) {
                WSETDto ws = (WSETDto) from;
                from = findWine(ws.getWineId());
            }
            if (from instanceof WineDto) {
                WineDto wine = (WineDto) from;
                from = findProducer(wine.getProducerId());
            }
            if (from instanceof ProducerDto) {
                ProducerDto producer = (ProducerDto) from;
                from = findArea(producer.getAreas().iterator().next());
            }
            if (from instanceof AreaDto) {
                result = from;
            }
        } else if ("producer".equals(what)) {
            if (from instanceof WSETDto) {
                WSETDto ws = (WSETDto) from;
                from = findWine(ws.getWineId());
            }
            if (from instanceof WineDto) {
                WineDto wine = (WineDto) from;
                from = findProducer(wine.getProducerId());
            }
            if (from instanceof ProducerDto) {
                result = from;
            }
        } else if ("wine".equals(what)) {
            if (from instanceof WSETDto) {
                WSETDto ws = (WSETDto) from;
                from = findWine(ws.getWineId());
            }
            if (from instanceof WineDto) {
                result = from;
            }
        } else {
            System.out.println("Unknown " + what + " from " + from.getClass().getName());
        }

        return result;
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
        return findArea(id) != null;
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
        return findCountry(id) != null;
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
        return findGrape(id) != null;
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
        return findProducer(id) != null;
    }

    void addUser(UserDto u) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(u);
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
        return findRegion(id) != null;
    }

    boolean hasUser(int id) {
        return findUser(id) != null;
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
        return findWine(id) != null;
    }

    void addWSET(WSETDto w) {
        if (wset == null) {
            wset = new ArrayList<>();
        }

        wset.add(w);
    }

    boolean hasWSET(Long id) {
        return findWSET(id) != null;
    }

    private UserDto findUser(int id) {
        if (users != null) {
            for (UserDto u : users) {
                if (u.getId() == id) {
                    return u;
                }
            }
        }

        return null;
    }

    private WineDto findWine(Long id) {
        if (wines != null) {
            for (WineDto w : wines) {
                if (w.getId().equals(id)) {
                    return w;
                }
            }
        }

        return null;
    }

    private ProducerDto findProducer(Long id) {
        if (producers != null) {
            for (ProducerDto p : producers) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
        }

        return null;
    }

    private AreaDto findArea(Long id) {
        if (areas != null) {
            for (AreaDto a : areas) {
                if (a.getId().equals(id)) {
                    return a;
                }
            }
        }

        return null;
    }

    private RegionDto findRegion(Long id) {
        if (regions != null) {
            for (RegionDto r : regions) {
                if (r.getId().equals(id)) {
                    return r;
                }
            }
        }

        return null;
    }

    private CountryDto findCountry(Long id) {
        if (countries != null) {
            for (CountryDto c : countries) {
                if (c.getId().equals(id)) {
                    return c;
                }
            }
        }

        return null;
    }

    private WSETDto findWSET(Long id) {
        if (wset != null) {
            for (WSETDto w : wset) {
                if (w.getId().equals(id)) {
                    return w;
                }
            }
        }
        return null;
    }

    private GrapeDto findGrape(Long id) {
        if (grapes != null) {
            for (GrapeDto g : grapes) {
                if (g.getId().equals(id)) {
                    return g;
                }
            }
        }

        return null;
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
