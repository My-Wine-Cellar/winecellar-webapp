/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.ClosureConverter;
import info.mywinecellar.converter.ColorConverter;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.converter.ShapeConverter;
import info.mywinecellar.converter.TypeConverter;
import info.mywinecellar.converter.UserConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Grape;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.wset.WSET;
import info.mywinecellar.wset.WSETConverter;

import java.util.Set;

/**
 * Builder
 */
public class Builder {

    private final WSETConverter wsetConverter;

    private final MyWineCellar json;

    /**
     * Constructor
     */
    public Builder() {
        wsetConverter = new WSETConverter();

        json = new MyWineCellar();
    }

    /**
     * Add an area
     *
     * @param a The area
     * @return The builder
     */
    public Builder area(Area a) {
        if (!json.hasArea(a.getId())) {
            json.addArea(AreaConverter.toDto(a));
        }
        return this;
    }

    /**
     * Add areas
     *
     * @param areas Areas
     * @return The builder
     */
    public Builder areas(Set<Area> areas) {
        json.addAreas(AreaConverter.toDto(areas));
        return this;
    }

    /**
     * Add a country
     *
     * @param c The country
     * @return The builder
     */
    public Builder country(Country c) {
        if (!json.hasCountry(c.getId())) {
            json.addCountry(CountryConverter.toDto(c));
        }
        return this;
    }

    /**
     * Add countries
     *
     * @param countries Countries
     * @return The builder
     */
    public Builder countries(Set<Country> countries) {
        json.addCountries(CountryConverter.toDto(countries));
        return this;
    }

    /**
     * Add a grape
     *
     * @param g The grape
     * @return The builder
     */
    public Builder grape(Grape g) {
        if (!json.hasGrape(g.getId())) {
            json.addGrape(GrapeConverter.toDto(g));
        }
        return this;
    }

    /**
     * Add grapes
     *
     * @param grapes Grapes
     * @return The builder
     */
    public Builder grapes(Set<Grape> grapes) {
        json.addGrapes(GrapeConverter.toDto(grapes));
        return this;
    }

    /**
     * Add a producer
     *
     * @param p The producer
     * @return The builder
     */
    public Builder producer(Producer p) {
        if (!json.hasProducer(p.getId())) {
            json.addProducer(ProducerConverter.toDto(p));
        }
        return this;
    }

    /**
     * Add producers
     *
     * @param producers Producers
     * @return The builder
     */
    public Builder producers(Set<Producer> producers) {
        json.addProducers(ProducerConverter.toDto(producers));
        return this;
    }

    /**
     * Add a region
     *
     * @param r The region
     * @return The builder
     */
    public Builder region(Region r) {
        if (!json.hasRegion(r.getId())) {
            json.addRegion(RegionConverter.toDto(r));
        }
        return this;
    }

    /**
     * Add regions
     *
     * @param regions Regions
     * @return The builder
     */
    public Builder regions(Set<Region> regions) {
        json.addRegions(RegionConverter.toDto(regions));
        return this;
    }

    /**
     * Add a user
     * @param u The user
     * @return The builder
     */
    public Builder user(User u) {
        if (!json.hasUser(u.getId())) {
            json.addUser(UserConverter.toDto(u));
        }
        return this;
    }

    /**
     * Add a wine
     *
     * @param w The wine
     * @return The builder
     */
    public Builder wine(Wine w) {
        if (!json.hasWine(w.getId())) {
            json.addWine(WineConverter.toDto(w));
        }
        return this;
    }

    /**
     * Add wines
     *
     * @param wines Wines
     * @return The builder
     */
    public Builder wines(Set<Wine> wines) {
        json.addWines(WineConverter.toDto(wines));
        return this;
    }

    /**
     * Add a closure
     *
     * @param c The closure
     * @return The builder
     */
    public Builder closure(Closure c) {
        if (!json.hasClosure(c.getId())) {
            json.addClosure(ClosureConverter.toDto(c));
        }
        return this;
    }

    /**
     * Add closures
     *
     * @param closures Closures
     * @return The builder
     */
    public Builder closures(Set<Closure> closures) {
        json.addClosures(ClosureConverter.toDto(closures));
        return this;
    }

    /**
     * Add a color
     *
     * @param c The color
     * @return The builder
     */
    public Builder color(Color c) {
        if (!json.hasColor(c.getId())) {
            json.addColor(ColorConverter.toDto(c));
        }
        return this;
    }

    /**
     * Add colors
     *
     * @param colors The colors
     * @return The builder
     */
    public Builder colors(Set<Color> colors) {
        json.addColors(ColorConverter.toDto(colors));
        return this;
    }

    /**
     * Add a shape
     *
     * @param s The shape
     * @return The builder
     */
    public Builder shape(Shape s) {
        if (!json.hasShape(s.getId())) {
            json.addShape(ShapeConverter.toDto(s));
        }
        return this;
    }

    /**
     * Add a WSET
     * @param w The WSET
     * @return The builder
     */
    public Builder wset(WSET w) {
        if (!json.hasWSET(w.getId())) {
            json.addWSET(wsetConverter.toDto(w));
        }
        return this;
    }

    /**
     * Add shapes
     *
     * @param shapes The shapes
     * @return The builder
     */
    public Builder shapes(Set<Shape> shapes) {
        json.addShapes(ShapeConverter.toDto(shapes));
        return this;
    }

    /**
     * Add a type
     *
     * @param t The type
     * @return The builder
     */
    public Builder type(Type t) {
        if (!json.hasType(t.getId())) {
            json.addType(TypeConverter.toDto(t));
        }
        return this;
    }

    /**
     * Add types
     *
     * @param types The types
     * @return The builder
     */
    public Builder types(Set<Type> types) {
        json.addTypes(TypeConverter.toDto(types));
        return this;
    }

    /**
     * Build the MyWineCellar instance
     *
     * @return The result
     */
    public MyWineCellar build() {
        return json;
    }
}

