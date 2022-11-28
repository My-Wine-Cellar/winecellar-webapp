/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.json.Builder;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.UserService;
import info.mywinecellar.service.WineService;

import java.security.Principal;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wset")
public class WSETController {

    @Inject
    private WSETConverter wsetConverter;

    @Inject
    private WSETService wsetService;

    @Inject
    private UserService userService;

    @Inject
    private WineService wineService;

    /**
     * Default constructor
     */
    public WSETController() {
        super();
    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/list")
    public String wsetListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());

        Builder builder = new Builder();

        for (WSET w : user.getWset()) {
            Wine wine = w.getWine();
            Producer producer = wine.getProducer();
            Area area = producer.getAreas().iterator().next();
            Region region = area.getRegions().iterator().next();
            Country country = region.getCountry();

            builder.wset(w);
            builder.wine(wine);
            builder.producer(producer);
            builder.area(area);
            builder.region(region);
            builder.country(country);
        }

        model.addAttribute(Attributes.DATA, builder.build());

        return Paths.WSET_LIST;
    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/new")
    public String wsetNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(wineId);

        if (user == null || wine == null) {
            return Paths.REDIRECT_ROOT;
        }

        WSETDto wset = new WSETDto(wine, user);

        Builder builder = new Builder();

        Producer producer = wine.getProducer();
        Area area = producer.getAreas().iterator().next();
        Region region = area.getRegions().iterator().next();
        Country country = region.getCountry();

        builder.wine(wine);
        builder.producer(producer);
        builder.area(area);
        builder.region(region);
        builder.country(country);

        model.addAttribute(Attributes.DATA, builder.build());
        model.addAttribute(Attributes.WSET, wset);

        return Paths.WSET_ADD_EDIT;
    }

    /**
     * @param wsetDto wsetDto
     * @param model model
     * @param result result
     * @param principal principal
     * @param action action
     * @return View
     */
    @PostMapping("/new")
    public String wsetNewPost(@Valid WSETDto wsetDto, Model model,
                              BindingResult result, Principal principal,
                              @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.WSET, wsetDto);
            return Paths.WSET_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                WSET wset = wsetConverter.toEntity(null, wsetDto);
                wsetService.save(wset);
                return Paths.REDIRECT_WSET_LIST;
            } else {
                return Paths.REDIRECT_WELCOME;
            }
        }
    }

    /**
     * @param wsetId  wsetId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{wsetId}")
    public String wsetDetailsGet(@PathVariable Long wsetId, Model model, Principal principal) {
        User user = null;

        if (principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        WSET wset = wsetService.findById(wsetId);
        if (wset == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (!wset.getShow()) {
            if (user == null) {
                return Paths.REDIRECT_ROOT;
            }
            if (wset.getUser().getId() != user.getId()) {
                return Paths.REDIRECT_ROOT;
            }
        }

        Builder builder = new Builder();

        Wine wine = wset.getWine();
        Producer producer = wine.getProducer();
        Area area = producer.getAreas().iterator().next();
        Region region = area.getRegions().iterator().next();
        Country country = region.getCountry();

        User author = wset.getUser();

        builder.user(author);
        builder.wset(wset);
        builder.wine(wine);
        builder.producer(producer);
        builder.area(area);
        builder.region(region);
        builder.country(country);

        if (user != null) {
            builder.user(user);
        }

        model.addAttribute(Attributes.DATA, builder.build());
        return Paths.WSET_VIEW;
    }

    /**
     * @param wsetId  wsetId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{wsetId}/edit")
    public String wsetEditGet(@PathVariable Long wsetId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        WSET wset = wsetService.findByUser(user.getId(), wsetId);

        if (user == null || wset == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (wset.getUser().getId() != user.getId()) {
            return Paths.REDIRECT_ROOT;
        }

        Builder builder = new Builder();

        Wine wine = wset.getWine();
        Producer producer = wine.getProducer();
        Area area = producer.getAreas().iterator().next();
        Region region = area.getRegions().iterator().next();
        Country country = region.getCountry();

        builder.wine(wine);
        builder.producer(producer);
        builder.area(area);
        builder.region(region);
        builder.country(country);

        model.addAttribute(Attributes.DATA, builder.build());
        model.addAttribute(Attributes.WSET, wsetConverter.toDto(wset));
        return Paths.WSET_ADD_EDIT;
    }

    /**
     * @param wsetDto wsetDto
     * @param result         result
     * @param wsetId       wsetId
     * @param model          model
     * @param principal      principal
     * @param action         action
     * @return View
     */
    @PostMapping("/{wsetId}/edit")
    public String wsetEditPost(@Valid WSETDto wsetDto, BindingResult result,
                               @PathVariable Long wsetId, Model model, Principal principal,
                               @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }
        if (result.hasErrors()) {
            model.addAttribute(Attributes.WSET, wsetDto);
            return Paths.WSET_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                WSET wset = wsetService.findById(wsetId);
                wset = wsetConverter.toEntity(wset, wsetDto);
                wsetService.save(wset);
                return Paths.REDIRECT_WSET_LIST;
            } else {
                return Paths.REDIRECT_WSET_LIST;
            }
        }
    }

    /**
     * @param wsetId  wsetId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{wsetId}/delete")
    public String wsetDeleteGet(@PathVariable Long wsetId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        WSET wset = wsetService.findByUser(user.getId(), wsetId);

        if (user == null || wset == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (wset.getUser().getId() != user.getId()) {
            return Paths.REDIRECT_ROOT;
        }

        if (wset != null) {
            wsetService.delete(wset);
        }

        return Paths.REDIRECT_WSET_LIST;
    }
}
