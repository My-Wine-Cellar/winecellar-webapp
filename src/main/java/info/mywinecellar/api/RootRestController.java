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
     * GET Mapping
     *
     * @return MyWineCellar
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
     * GET mapping
     *
     * @return List of Countries that have wine producing regions
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
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));

        Builder builder = new Builder();
        builder.country(cntry);
        builder.regions(cntry.getRegions());
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
        Country cntry = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        Region rgn = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), cntry.getId());

        Builder builder = new Builder();
        builder.country(cntry);
        builder.region(rgn);
        builder.areas(rgn.getAreas());
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
