package com.cellar.wine.controllers;

import com.cellar.wine.models.Barrel;
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

    private static final String MODEL_ATTRIBUTE_BARREL = "barrel";

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
            return "redirect:/";

        model.addAttribute(MODEL_ATTRIBUTE_BARREL, getBarrelUI(barrels.get(0)));
        return "barrel/barrelDetails";
    }

    @GetMapping("/{barrelId}/edit")
    public String barrelEditGet(@PathVariable Long barrelId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        Barrel barrel = barrelService.findById(barrelId);

        if (barrel == null)
            return "redirect:/";

        model.addAttribute(MODEL_ATTRIBUTE_BARREL, getBarrelUI(barrel));
        return "barrel/editBarrel";
    }

    @PostMapping("/{barrelId}/edit")
    public String barrelEditPost(@Valid BarrelUI barrel, BindingResult result, @PathVariable Long barrelId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "barrel/editBarrel";
        } else {

            System.out.println(barrel);

            Barrel b = barrelService.findById(barrelId);
            b.setDescription(barrel.getDescription());
            b.setWeblink(barrel.getWeblink());
            Barrel savedBarrel = barrelService.save(b);

            BarrelUI ui = new BarrelUI(savedBarrel);
            return "redirect:/barrel/" + ui.getKey();
        }
    }

    private BarrelUI getBarrelUI(Barrel b) {
        return new BarrelUI(b);
    }
}
