package com.cellar.wine.controllers;

import com.cellar.wine.models.Barrel;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.services.BarrelService;
import com.cellar.wine.ui.AbstractKeyUI;
import com.cellar.wine.ui.BarrelUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/barrel")
public class BarrelController {

    @Inject
    private BarrelService barrelService;

    public BarrelController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{barrel}")
    public String barrelDetails(@PathVariable String barrel, Model model) {
        List<Barrel> barrels = barrelService.findByLowerCaseName(AbstractKeyUI.fromKey(barrel) + "%");

        if (barrels == null || barrels.isEmpty())
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.BARREL, getBarrelUI(barrels.get(0)));
        return Paths.BARREL_DETAILS;
    }

    @GetMapping("/{barrelId}/edit")
    public String barrelEditGet(@PathVariable Long barrelId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        Barrel barrel = barrelService.findById(barrelId);

        if (barrel == null)
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.BARREL, getBarrelUI(barrel));
        return Paths.BARREL_EDIT;
    }

    @PostMapping("/{barrelId}/edit")
    public String barrelEditPost(@Valid BarrelUI barrel, BindingResult result, Model model,
                                 @PathVariable Long barrelId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.BARREL, barrel);
            return Paths.BARREL_EDIT;
        } else {

            Barrel b = barrelService.findById(barrelId);
            b.setDescription(barrel.getDescription());
            b.setWeblink(barrel.getWeblink());
            Barrel savedBarrel = barrelService.save(b);

            BarrelUI ui = getBarrelUI(savedBarrel);
            return Paths.REDIRECT_BARREL + ui.getKey();
        }
    }

    private BarrelUI getBarrelUI(Barrel b) {
        return new BarrelUI(b);
    }
}
