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
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Grape;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.Wine;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RootRestController extends AbstractRestController {

    /**
     * @return MyWineCellar JSON envelope
     */
    @GetMapping("/json")
    public MyWineCellar getJsonEnvelope() {
        Set<Country> countries = countryService.findWithRegions();
        Set<Region> regions = regionService.findAll();
        Set<Area> areas = areaService.findAll();
        Set<Producer> producers = producerService.findAll();
        Set<Wine> wines = wineService.findAll();
        Set<Grape> grapes = grapeService.findAll();
        Set<Closure> closures = closureService.findAll();
        Set<Color> colors = colorService.findAll();
        Set<Shape> shapes = shapeService.findAll();
        Set<Type> types = typeService.findAll();

        Builder builder = new Builder();
        builder.envelope(countries, regions, areas, producers, wines, grapes);
        builder.closures(closures);
        builder.colors(colors);
        builder.shapes(shapes);
        builder.types(types);
        return builder.build();
    }

    /**
     * Countries that have wine producing regions
     *
     * @return MyWineCellar JSON envelope and the countries
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/countries")
    public MyWineCellar dataApiRootGet() {
        Set<Country> countries = countryService.findWithRegions();
        Builder builder = new Builder();
        builder.countries(countries);
        return builder.build();
    }

    /**
     * A country and the regions, or states
     * <p>
     * A country's name will be interpreted as lowercase
     * Country's like 'United States' and 'New Zealand' will be separated by an underscore(_)
     * ie united_states and new_zealand
     *
     * @param country The name of country
     * @return MyWineCellar JSON envelope and the country and it's regions
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}")
    public MyWineCellar countryByNameGet(@PathVariable String country) {
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));

        Builder builder = new Builder();
        builder.country(cntry);
        builder.regions(cntry.getRegions());
        return builder.build();
    }

    /**
     * A country, region, and areas
     * <p>
     * A region's name will be interpreted as lowercase and seaprated by underscore
     *
     * @param country The name of the country
     * @param region  The name of the region
     * @return MyWineCellar JSON envelope and the country, region, and areas
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}")
    public MyWineCellar regionByNameGet(@PathVariable String country, @PathVariable String region) {
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        Region rgn = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), cntry.getId());

        Builder builder = new Builder();
        builder.country(cntry);
        builder.region(rgn);
        builder.areas(rgn.getAreas());
        return builder.build();
    }

    /**
     * A country, region, area and it's producers and grapes
     * <p>
     * Area's name will be interpreted as lowercase
     * Need to account for ava, doc, aoc
     * ie napa_valley_ava or abruzzo_doc
     *
     * @param country The name of the country
     * @param region  The name of the region
     * @param area    The name of the area
     * @return MyWineCellar JSON envelope and the country, region, area and it's producers and grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}")
    public MyWineCellar areaByNameGet(@PathVariable String country, @PathVariable String region,
                                      @PathVariable String area) {
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        Region rgn = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), cntry.getId());
        Area ar = areaService.findByLowerCaseName(AbstractKeyDto.fromKey(area));

        Builder builder = new Builder();
        builder.country(cntry);
        builder.region(rgn);
        builder.area(ar);
        builder.producers(ar.getProducers());
        builder.grapes(ar.getPrimaryGrapes());
        return builder.build();
    }

    /**
     * A country, region, area, and the producer
     * <p>
     * Producer's name will be interpreted as lowercase
     * ie Opus One Winery will be opus_one_winery
     *
     * @param country  The name of the country
     * @param region   The name of the region
     * @param area     The name of the area
     * @param producer The name of the producer
     * @return MyWineCellar JSON envelope and the country, region, area, producer and it's wines
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}/{producer}")
    public MyWineCellar producerByNameGet(@PathVariable String country, @PathVariable String region,
                                          @PathVariable String area, @PathVariable String producer) {
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        Region rgn = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), cntry.getId());
        Area ar = areaService.findByLowerCaseName(AbstractKeyDto.fromKey(area));
        Producer prdcr = producerService.findByLowerCaseName(AbstractKeyDto.fromKey(producer));

        Builder builder = new Builder();
        builder.country(cntry);
        builder.region(rgn);
        builder.area(ar);
        builder.producer(prdcr);
        builder.wines(prdcr.getWines());
        return builder.build();
    }

    /**
     * A country, region, area, producer, and wine
     * <p>
     * The wine's name will be interpreted as lowercase
     * ie Opus One will be opus_one
     *
     * @param country  The name of the country
     * @param region   The name of the region
     * @param area     The name of the area
     * @param producer The name of the producer
     * @param wine     The name of the wine
     * @param vintage  The vintage year
     * @param size     The size of the wine bottle or box
     * @return MyWineCellar JSON envelope and the country, region, area, producer, and wine
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{region}/{area}/{producer}/{wine}/{vintage}/{size}")
    public MyWineCellar wineByNameGet(@PathVariable String country, @PathVariable String region,
                                      @PathVariable String area, @PathVariable String producer,
                                      @PathVariable String wine, @PathVariable Integer vintage,
                                      @PathVariable Float size) {
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        Region rgn = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), cntry.getId());
        Area ar = areaService.findByLowerCaseName(AbstractKeyDto.fromKey(area));
        Producer prdcr = producerService.findByLowerCaseName(AbstractKeyDto.fromKey(producer));
        Wine wn = wineService.findByLowerCaseName(AbstractKeyDto.fromKey(wine));
        Closure closure = closureService.findById(wn.getClosure().getId());
        Color color = colorService.findById(wn.getColor().getId());
        Shape shape = shapeService.findById(wn.getShape().getId());
        Type type = typeService.findById(wn.getType().getId());

        Builder builder = new Builder();
        builder.country(cntry);
        builder.region(rgn);
        builder.area(ar);
        builder.producer(prdcr);
        if (wn.getVintage().equals(vintage) && wn.getSize().equals(size)) {
            builder.wine(wn);
        }
        builder.closure(closure);
        builder.color(color);
        builder.shape(shape);
        builder.type(type);
        return builder.build();
    }


}
