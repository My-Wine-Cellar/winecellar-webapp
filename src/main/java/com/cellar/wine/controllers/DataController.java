package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.BarrelComponent;
import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.Country;
import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Region;
import com.cellar.wine.models.Review;
import com.cellar.wine.models.Wine;
import com.cellar.wine.models.Wishlist;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.nav.Session;
import com.cellar.wine.security.model.User;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.TastingNotesService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.ui.AbstractKeyUI;
import com.cellar.wine.ui.AgingUI;
import com.cellar.wine.ui.AreaUIFactory;
import com.cellar.wine.ui.BarrelUI;
import com.cellar.wine.ui.BarrelUISorter;
import com.cellar.wine.ui.CountryUIFactory;
import com.cellar.wine.ui.GrapeUI;
import com.cellar.wine.ui.GrapeUIFactory;
import com.cellar.wine.ui.GrapeUISorter;
import com.cellar.wine.ui.ProducerUIFactory;
import com.cellar.wine.ui.RegionUIFactory;
import com.cellar.wine.ui.WineUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private WishlistService wishlistService;

    public DataController() {
    }

    @GetMapping("/")
    public String dataRootGet(Model model) {
        List<Country> countries = countryService.findWithRegions();
        model.addAttribute(Attributes.COUNTRIES, CountryUIFactory.instance().createList(countries));

        Session.updateSessionAttributes(null, null, null, null, null);

        return Paths.COUNTRY_LIST;
    }

    @GetMapping("/{country}")
    public String dataCountryGet(@PathVariable String country, Model model) {
        Country c = null;

        if (guard(country)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        if (c.getRegions() == null || c.getRegions().size() == 0) {
            return Paths.REDIRECT_ROOT;
        }
        
        model.addAttribute(Attributes.COUNTRY, CountryUIFactory.instance().create(c));
        model.addAttribute(Attributes.REGIONS, RegionUIFactory.instance().createList(c.getRegions()));

        Session.updateSessionAttributes(c.getId(), null, null, null, null);

        return Paths.COUNTRY_DETAILS;
    }

    @GetMapping("/{country}/{region}")
    public String dataRegionGet(@PathVariable String country, @PathVariable String region, Model model) {
        Country c = null;
        Region r = null;

        if (guard(country) || guard(region)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        r = regionService.findByLowerCaseName(AbstractKeyUI.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, CountryUIFactory.instance().create(c));
        model.addAttribute(Attributes.REGION, RegionUIFactory.instance().create(r));
        model.addAttribute(Attributes.AREAS, AreaUIFactory.instance().createList(r.getAreas()));

        Session.updateSessionAttributes(c.getId(), r.getId(), null, null, null);

        return Paths.REGION_DETAILS;
    }

    @GetMapping("/{country}/{region}/{area}")
    public String dataAreaGet(@PathVariable String country, @PathVariable String region,
                              @PathVariable String area, Model model) {
        Country c = null;
        Region r = null;
        Area a = null;

        if (guard(country) || guard(region) || guard(area)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        r = regionService.findByLowerCaseName(AbstractKeyUI.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyUI.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        model.addAttribute(Attributes.COUNTRY, CountryUIFactory.instance().create(c));
        model.addAttribute(Attributes.REGION, RegionUIFactory.instance().create(r));
        model.addAttribute(Attributes.AREA, AreaUIFactory.instance().create(a));
        model.addAttribute(Attributes.PRODUCERS, ProducerUIFactory.instance().createList(a.getProducers()));
        model.addAttribute(Attributes.PRIMARY_GRAPES, GrapeUIFactory.instance().createList(a.getPrimaryGrapes()));

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), null, null);

        return Paths.AREA_DETAILS;
    }

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

        c = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        r = regionService.findByLowerCaseName(AbstractKeyUI.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyUI.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Producer pr : a.getProducers()) {
            if (AbstractKeyUI.toKey(pr.getName()).equals(producer)) {
                p = pr;
                break;
            }
        }
        if (p == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        model.addAttribute(Attributes.COUNTRY, CountryUIFactory.instance().create(c));
        model.addAttribute(Attributes.REGION, RegionUIFactory.instance().create(r));
        model.addAttribute(Attributes.AREA, AreaUIFactory.instance().create(a));
        model.addAttribute(Attributes.PRODUCER, ProducerUIFactory.instance().create(p));
        model.addAttribute(Attributes.WINES, WineUIFactory.instance().createList(p.getWines()));

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), p.getId(), null);

        return Paths.PRODUCER_DETAILS;
    }

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
        Wishlist wishlist = null;

        if (guard(country) || guard(region) || guard(area) || guard(producer) ||
            guard(wine) || guard(vintage) || guard(size)) {
            return Paths.REDIRECT_ROOT;
        }

        c = countryService.findByLowerCaseName(AbstractKeyUI.fromKey(country));
        if (c == null) {
            return Paths.REDIRECT_ROOT;
        }
        
        r = regionService.findByLowerCaseName(AbstractKeyUI.fromKey(region), c.getId());
        if (r == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Area ar : r.getAreas()) {
            if (AbstractKeyUI.toKey(ar.getName()).equals(area)) {
                a = ar;
                break;
            }
        }
        if (a == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Producer pr : a.getProducers()) {
            if (AbstractKeyUI.toKey(pr.getName()).equals(producer)) {
                p = pr;
                break;
            }
        }
        if (p == null) {
            return Paths.REDIRECT_ROOT;
        }

        for (Wine wi : p.getWines()) {
            if (AbstractKeyUI.toKey(wi.getName()).equals(wine) &&
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
            wishlist = wishlistService.findByWine(user.getId(), w.getId());
        }

        List<GrapeUI> winegrapes = new ArrayList<>();
        for (GrapeComponent gc : w.getGrapes()) {
            List<BarrelUI> barrels = new ArrayList<>();

            if (gc.getBarrelComponents() != null) {
                for (BarrelComponent bc : gc.getBarrelComponents()) {
                    barrels.add(new BarrelUI(bc.getPercentage(),
                                             bc.getBarrel().getName(), bc.getBarrel().getId(),
                                             bc.getSize(), new AgingUI(bc.getAging())
                                             ));
                }
            }

            Collections.sort(barrels, new BarrelUISorter());

            winegrapes.add(new GrapeUI(gc.getPercentage(),
                                       gc.getGrape().getName(), gc.getGrape().getId(),
                                       gc.getHarvestBegin(), gc.getHarvestEnd(),
                                       gc.getMaceration() != null ? gc.getMaceration().getDays() : null,
                                       gc.getMaceration() != null ? gc.getMaceration().getTemperature() : null,
                                       gc.getFermentation() != null ? gc.getFermentation().getDays() : null,
                                       gc.getFermentation() != null ? gc.getFermentation().getTemperature() : null,
                                       barrels
                                       ));
        }
        Collections.sort(winegrapes, new GrapeUISorter());

        model.addAttribute(Attributes.COUNTRY, CountryUIFactory.instance().create(c));
        model.addAttribute(Attributes.REGION, RegionUIFactory.instance().create(r));
        model.addAttribute(Attributes.AREA, AreaUIFactory.instance().create(a));
        model.addAttribute(Attributes.PRODUCER, ProducerUIFactory.instance().create(p));
        model.addAttribute(Attributes.WINE, w);
        model.addAttribute(Attributes.WINEGRAPES, winegrapes);
        model.addAttribute(Attributes.MYBOTTLE, bottle);
        model.addAttribute(Attributes.MYREVIEW, review);
        model.addAttribute(Attributes.MYTASTINGNOTES, tastingnotes);
        model.addAttribute(Attributes.MYWISHLIST, wishlist);

        Session.updateSessionAttributes(c.getId(), r.getId(), a.getId(), p.getId(), w.getId());

        return Paths.WINE_DETAILS;
    }
    
    private boolean guard(Object o) {
        if (o == null)
            return true;

        if (o instanceof String) {
            String s = (String)o;
            return "".equals(s);
        }

        return false;
    }
}
