/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.dto.AgingDto;
import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.dto.GrapeComponentDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.Bottle;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.GenericTastingNotes;
import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Review;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.model.Wishlist;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.service.BottleService;
import info.mywinecellar.service.ReviewService;
import info.mywinecellar.service.TastingNotesService;
import info.mywinecellar.service.UserService;
import info.mywinecellar.service.WishlistService;
import info.mywinecellar.util.Image;
import info.mywinecellar.wset.WSET;
import info.mywinecellar.wset.WSETService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/d")
public class DataController extends AbstractController {

    @Inject
    private BottleService bottleService;

    @Inject
    private ReviewService reviewService;

    @Inject
    private TastingNotesService tastingNotesService;

    @Inject
    private WSETService wsetService;

    @Inject
    private WishlistService wishlistService;

    @Inject
    private UserService userService;

    /**
     * Default constructor
     */
    public DataController() {
        super();
    }

    /**
     * @param model model
     * @return View
     */
    @GetMapping("/")
    public String dataRootGet(Model model) {
        Set<Country> countries = countryService.findWithRegions();
        model.addAttribute(Attributes.COUNTRIES, CountryConverter.toDto(countries));

        Session.updateSessionAttributes(null, null, null, null, null);

        return Paths.COUNTRY_LIST;
    }

    /**
     * @param country country
     * @param model   model
     * @return View
     */
    @GetMapping("/{country}")
    public String dataCountryGet(@PathVariable String country, Model model) {
        Country c = null;

        if (guard(country)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        if (c.getRegions() == null || c.getRegions().size() == 0) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, CountryConverter.toDto(c));
        model.addAttribute(Attributes.REGIONS, RegionConverter.toDto(c.getRegions()));

        Session.updateSessionAttributes(c.getId(), null, null, null, null);

        return Paths.COUNTRY_DETAILS;
    }

    /**
     * @param country country
     * @param region  region
     * @param model   model
     * @return View
     */
    @GetMapping("/{country}/{region}")
    public String dataRegionGet(@PathVariable String country, @PathVariable String region, Model model) {
        Country c = null;
        Region r = null;

        if (guard(country) || guard(region)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }

        r = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, CountryConverter.toDto(c));
        model.addAttribute(Attributes.REGION, RegionConverter.toDto(r));
        model.addAttribute(Attributes.AREAS, AreaConverter.toDto(r.getAreas()));

        Session.updateSessionAttributes(c.getId(), r.getId(), null, null, null);

        return Paths.REGION_DETAILS;
    }

    /**
     * @param country country
     * @param region  region
     * @param area    area
     * @param model   model
     * @return View
     */
    @GetMapping("/{country}/{region}/{area}")
    public String dataAreaGet(@PathVariable String country, @PathVariable String region,
                              @PathVariable String area, Model model) {
        Country c = null;
        Region r = null;
        Area a = null;

        if (guard(country) || guard(region) || guard(area)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }

        r = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyDto.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, CountryConverter.toDto(c));
        model.addAttribute(Attributes.REGION, RegionConverter.toDto(r));
        model.addAttribute(Attributes.AREA, AreaConverter.toDto(a));
        model.addAttribute(Attributes.PRODUCERS, ProducerConverter.toDto(a.getProducers()));
        model.addAttribute(Attributes.PRIMARY_GRAPES, GrapeConverter.toDto(a.getPrimaryGrapes()));

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), null, null);

        return Paths.AREA_DETAILS;
    }

    /**
     * @param country  country
     * @param region   region
     * @param area     area
     * @param producer producer
     * @param model    model
     * @return View
     */
    @GetMapping("/{country}/{region}/{area}/{producer}")
    public String dataProducerGet(@PathVariable String country, @PathVariable String region,
                                  @PathVariable String area, @PathVariable String producer, Model model) {
        Country c = null;
        Region r = null;
        Area a = null;
        Producer p = null;

        if (guard(country) || guard(region) || guard(area) || guard(producer)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }

        r = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyDto.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Producer pr : a.getProducers()) {
            if (AbstractKeyDto.toKey(pr.getName()).equals(producer)) {
                p = pr;
                break;
            }
        }
        if (p == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, CountryConverter.toDto(c));
        model.addAttribute(Attributes.REGION, RegionConverter.toDto(r));
        model.addAttribute(Attributes.AREA, AreaConverter.toDto(a));
        model.addAttribute(Attributes.PRODUCER, ProducerConverter.toDto(p));
        model.addAttribute(Attributes.WINES, WineConverter.toDto(p.getWines()));

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), p.getId(), null);

        return Paths.PRODUCER_DETAILS;
    }

    /**
     * @param country   country
     * @param region    region
     * @param area      area
     * @param producer  producer
     * @param wine      wine
     * @param vintage   vintage
     * @param size      size
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{country}/{region}/{area}/{producer}/{wine}/{vintage}/{size}")
    public String dataWineGet(@PathVariable String country, @PathVariable String region,
                              @PathVariable String area, @PathVariable String producer,
                              @PathVariable String wine, @PathVariable Integer vintage, @PathVariable Float size,
                              Model model, Principal principal) {
        Country c = null;
        Region r = null;
        Area a = null;
        Producer p = null;
        Wine w = null;
        User user = null;
        Bottle bottle = null;
        Review review = null;
        GenericTastingNotes tastingnotes = null;
        WSET wset = null;
        Wishlist wishlist = null;

        if (guard(country) || guard(region) || guard(area) || guard(producer) ||
                guard(wine) || guard(vintage) || guard(size)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyDto.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }

        r = regionService.findByLowerCaseName(AbstractKeyDto.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyDto.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Producer pr : a.getProducers()) {
            if (AbstractKeyDto.toKey(pr.getName()).equals(producer)) {
                p = pr;
                break;
            }
        }
        if (p == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Wine wi : p.getWines()) {
            if (AbstractKeyDto.toKey(wi.getName()).equals(wine) &&
                    wi.getVintage().equals(vintage) && wi.getSize().equals(size)) {
                w = wi;
                break;
            }
        }
        if (w == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            bottle = bottleService.findByWine(user.getId(), w.getId());
            review = reviewService.findByWine(user.getId(), w.getId());
            tastingnotes = tastingNotesService.findByWine(user.getId(), w.getId());
            wset = wsetService.findByWine(user.getId(), w.getId());
            wishlist = wishlistService.findByWine(user.getId(), w.getId());
        }

        List<GrapeComponentDto> winegrapes = new ArrayList<>();
        for (GrapeComponent gc : w.getGrapes()) {
            List<BarrelDto> barrels = new ArrayList<>();

            if (gc.getBarrelComponents() != null) {
                for (BarrelComponent bc : gc.getBarrelComponents()) {
                    barrels.add(new BarrelDto(bc.getPercentage(),
                            bc.getBarrel().getName(), bc.getBarrel().getId(),
                            bc.getSize(), new AgingDto(bc.getAging())
                    ));
                }
            }

            barrels.sort(Comparator.comparing(BarrelDto::getPercentage)
                    .thenComparing(BarrelDto::getAging)
                    .thenComparing(BarrelDto::getSize)
                    .thenComparing(BarrelDto::getName));

            winegrapes.add(new GrapeComponentDto(gc.getPercentage(),
                    gc.getGrape().getName(), gc.getGrape().getId(),
                    gc.getHarvestBegin(), gc.getHarvestEnd(),
                    gc.getMaceration() != null ? gc.getMaceration().getDays() : null,
                    gc.getMaceration() != null ? gc.getMaceration().getTemperature() : null,
                    gc.getFermentation() != null ? gc.getFermentation().getDays() : null,
                    gc.getFermentation() != null ? gc.getFermentation().getTemperature() : null,
                    barrels
            ));
        }

        winegrapes.sort(Comparator.comparing(GrapeComponentDto::getPercentage)
                .thenComparing(GrapeComponentDto::getName));

        model.addAttribute(Attributes.COUNTRY, CountryConverter.toDto(c));
        model.addAttribute(Attributes.REGION, RegionConverter.toDto(r));
        model.addAttribute(Attributes.AREA, AreaConverter.toDto(a));
        model.addAttribute(Attributes.PRODUCER, ProducerConverter.toDto(p));
        model.addAttribute(Attributes.WINE, WineConverter.toDetailsDto(w));
        model.addAttribute(Attributes.ENCODED_IMAGE, Image.encode(w.getImage()));
        model.addAttribute(Attributes.WINEGRAPES, winegrapes);
        model.addAttribute(Attributes.MYBOTTLE, bottle);
        model.addAttribute(Attributes.MYREVIEW, review);
        model.addAttribute(Attributes.MYTASTINGNOTES, tastingnotes);
        model.addAttribute(Attributes.MYWSET, wset);
        model.addAttribute(Attributes.MYWISHLIST, wishlist);

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), p.getId(), w.getId());

        return Paths.WINE_DETAILS;
    }

    private boolean guard(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String s = (String) o;
            return "".equals(s);
        }

        return false;
    }
}
