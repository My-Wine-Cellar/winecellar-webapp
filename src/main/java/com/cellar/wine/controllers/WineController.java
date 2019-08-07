package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.nav.Session;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.WineUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping("/wine")
public class WineController extends AbstractController {

    @Inject
    private WineService wineService;

    public WineController() {
    }

    @InitBinder("wine")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String wineNewGet(Producer producer, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        Wine wine = new Wine();
        wine.setProducer(producer);
        model.addAttribute(Attributes.WINE, wine);
        return Paths.WINE_ADD_EDIT;
    }

    @PostMapping("/new")
    public String wineNewPost(@Valid Wine wine, BindingResult result, ModelMap model,
                              @RequestParam Long producerId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.put(Attributes.WINE, wine);
            return Paths.WINE_ADD_EDIT;
        } else {
            Producer producer = producerService.findById(producerId);
            wine.setProducer(producer);
            producer.getWines().add(wine);

            Wine savedWine = wineService.save(wine);
            WineUI wui = new WineUI(savedWine);

            return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                                    Session.getAreaId(), Session.getProducerId()) +
                "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
        }
    }

    @GetMapping("/{wineId}/edit")
    public String wineEditGet(@PathVariable Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(wineService.findById(wineId));
        return Paths.WINE_ADD_EDIT;
    }

    @PostMapping("/{wineId}/edit")
    public String wineEditPost(@Valid Wine wine, BindingResult result, Model model,
                               @PathVariable Long wineId, @RequestParam Long producerId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.WINE, wine);
            return Paths.WINE_ADD_EDIT;
        } else {
            Producer producer = producerService.findById(producerId);

            wine.setId(wineId);
            wine.setProducer(producer);

            Wine savedWine = wineService.save(wine);
            WineUI wui = new WineUI(savedWine);

            return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                                    Session.getAreaId(), Session.getProducerId()) +
                "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
        }
    }
}
