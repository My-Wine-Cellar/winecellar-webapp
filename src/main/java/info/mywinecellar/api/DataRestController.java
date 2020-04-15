/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Wine;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataRestController extends AbstractRestController {

    /**
     * GET Mapping
     *
     * @return MyWineCellar
     */
    @GetMapping("/json")
    public MyWineCellar getJsonEnvelope() {
        List<Country> countries = countryService.findWithRegions();
        List<Region> regions = regionService.findAll();
        List<Area> areas = areaService.findAll();
        List<Producer> producers = producerService.findAll();
        List<Wine> wines = wineService.findAll();

        Builder builder = new Builder();

        countries.forEach(builder::country);
        regions.forEach(builder::region);
        areas.forEach(builder::area);
        producers.forEach(builder::producer);
        wines.forEach(builder::wine);

        return builder.build();
    }

    /**
     * GET mapping
     *
     * @return List of Countries that have wine producing regions
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MyWineCellar dataApiRootGet() {
        List<Country> countries = countryService.findWithRegions();
        checkObjectListNull(countries);
        log.info("==== Countries that have a wine producing region {} ====", countries);
        Builder builder = new Builder();
        countries.forEach(builder::country);
        return builder.build();
    }

    /**
     * GET mapping
     * <p>
     * A country's name will be interpreted as lowercase
     * Country's like 'United States' and 'New Zealand' will be separated by an underscore(_)
     * ie united_states and new_zealand
     *
     * @param country String country lowercase name
     * @return Country
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}")
    public MyWineCellar countryByNameGet(@PathVariable String country) {
        Builder builder = new Builder();
        builder.country(setupCountry(country));
        return builder.build();
    }

    /**
     * GET Mapping
     * A region's name will be interpreted as lowercase
     *
     * @param country String country
     * @param region  String region
     * @return Region
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}")
    public MyWineCellar regionByNameGet(@PathVariable String country, @PathVariable String region) {
        Builder builder = new Builder();
        builder.region(setupRegion(country, region));
        return builder.build();
    }

    /**
     * GET mapping
     * Area's name will be interpreted as lowercase
     * Need to account for things like ava, doc, aoc
     *
     * @param country String country
     * @param region  String region
     * @param area    String area
     * @return Area
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}")
    public MyWineCellar areaByNameGet(@PathVariable String country, @PathVariable String region,
                                      @PathVariable String area) {
        Builder builder = new Builder();
        builder.area(setupArea(country, region, area));
        return builder.build();
    }

    /**
     * GET mapping
     * Gives us a bookmarkable url for a producer's name
     *
     * @param country  String country
     * @param region   String region
     * @param area     String area
     * @param producer String producer
     * @return Producer
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}/{producer}")
    public MyWineCellar producerByNameGet(@PathVariable String country, @PathVariable String region,
                                          @PathVariable String area, @PathVariable String producer) {
        Builder builder = new Builder();
        builder.producer(setupProducer(country, region, area, producer));
        return builder.build();
    }

    /**
     * GET Mapping
     * Gives us a bookmarkable url for a wine
     *
     * @param country  String country
     * @param region   String region
     * @param area     String area
     * @param producer String producer
     * @param wine     String wine
     * @param vintage  Integer vintage
     * @param size     Float size
     * @return Wine
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}/{producer}/{wine}/{vintage}/{size}")
    public MyWineCellar wineByNameGet(@PathVariable String country, @PathVariable String region,
                                      @PathVariable String area, @PathVariable String producer,
                                      @PathVariable String wine, @PathVariable Integer vintage,
                                      @PathVariable Float size) {
        Builder builder = new Builder();
        builder.wine(setupWine(country, region, area, producer, wine, vintage, size));
        return builder.build();
    }

    private Country setupCountry(String countryName) {
        Country country = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(countryName));
        checkObjectNull(country);
        log.info("==== Country {} ====", countryName);
        return country;
    }

    private Region setupRegion(String countryName, String regionName) {
        Country country = setupCountry(countryName);
        Region region = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(regionName), country.getId());
        checkObjectNull(region);
        log.info("==== Region {} ====", regionName);
        return region;
    }

    private Area setupArea(String countryName, String regionName, String areaName) {
        Region region = setupRegion(countryName, regionName);
        Area area = null;
        for (Area ar : region.getAreas()) {
            if (AbstractKeyDto.toKey(ar.getName()).equals(areaName)) {
                area = ar;
                break;
            }
        }
        checkObjectNull(area);
        log.info("==== Area {} ====", areaName);
        return area;
    }

    private Producer setupProducer(String countryName, String regionName, String areaName, String producerName) {
        Area area = setupArea(countryName, regionName, areaName);
        Producer producer = null;
        for (Producer pr : area.getProducers()) {
            if (AbstractKeyDto.toKey(pr.getName()).equals(producerName)) {
                producer = pr;
                break;
            }
        }
        log.info("==== Producer {} ====", producerName);
        return producer;
    }

    private Wine setupWine(String countryName, String regionName, String areaName, String producerName,
                           String wineName, Integer vintage, Float size) {
        Producer p = setupProducer(countryName, regionName, areaName, producerName);
        Wine wine = null;
        for (Wine wi : p.getWines()) {
            if (AbstractKeyDto.toKey(wi.getName()).equals(wineName) &&
                    wi.getVintage().equals(vintage) && wi.getSize().equals(size)) {
                wine = wi;
                break;
            }
        }
        log.info("==== Wine {} ====", wineName);
        return wine;
    }

}
