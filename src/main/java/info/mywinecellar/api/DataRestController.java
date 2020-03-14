/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Wine;
import info.mywinecellar.ui.AbstractKeyUI;

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
     * GET mapping
     *
     * @return List of Countries that have wine producing regions
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Country> dataApiRootGet() {
        List<Country> countries = countryService.findWithRegions();
        checkObjectListNull(countries);
        log.info("==== Countries that have a wine producing region {} ====", countries);
        return countries;
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
    public Country countryByNameGet(@PathVariable String country) {
        return setupCountry(country);
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
    public Region regionByNameGet(@PathVariable String country, @PathVariable String region) {
        return setupRegion(country, region);
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
    public Area areaByNameGet(@PathVariable String country, @PathVariable String region, @PathVariable String area) {
        return setupArea(country, region, area);
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
    public Producer producerByNameGet(@PathVariable String country, @PathVariable String region,
                                      @PathVariable String area, @PathVariable String producer) {
        return setupProducer(country, region, area, producer);
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
    public Wine wineByNameGet(@PathVariable String country, @PathVariable String region,
                              @PathVariable String area, @PathVariable String producer,
                              @PathVariable String wine, @PathVariable Integer vintage, @PathVariable Float size) {
        return setupWine(country, region, area, producer, wine, vintage, size);
    }

    private Country setupCountry(String countryName) {
        Country country = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(countryName));
        checkObjectNull(country);
        log.info("==== Country {} ====", countryName);
        return country;
    }

    private Region setupRegion(String countryName, String regionName) {
        Country country = setupCountry(countryName);
        Region region = regionService.findByLowerCaseName(AbstractKeyUI.fromKey(regionName), country.getId());
        checkObjectNull(region);
        log.info("==== Region {} ====", regionName);
        return region;
    }

    private Area setupArea(String countryName, String regionName, String areaName) {
        Region region = setupRegion(countryName, regionName);
        Area area = null;
        for (Area ar : region.getAreas()) {
            if (AbstractKeyUI.toKey(ar.getName()).equals(areaName)) {
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
            if (AbstractKeyUI.toKey(pr.getName()).equals(producerName)) {
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
            if (AbstractKeyUI.toKey(wi.getName()).equals(wineName) &&
                    wi.getVintage().equals(vintage) && wi.getSize().equals(size)) {
                wine = wi;
                break;
            }
        }
        log.info("==== Wine {} ====", wineName);
        return wine;
    }

}
