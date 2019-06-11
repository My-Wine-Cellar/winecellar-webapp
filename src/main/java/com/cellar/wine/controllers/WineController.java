package com.cellar.wine.controllers;

import com.cellar.wine.models.Wine;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/wine")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    private static final String MODEL_ATTRIBUTE_WINE = "wine";

    private static final String ADD_OR_EDIT_WINE_TEMPLATE = "wine/addEditWine";

    @GetMapping("/list")
    public String showAllWines(Model model) {
        model.addAttribute("wines", wineService.findAll());
        return "wine/wineList";
    }

    @GetMapping("/{wineId}")
    public String wineDetails(@PathVariable Long wineId, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wineService.findById(wineId));
        return "wine/wineDetails";
    }

    @GetMapping("/new")
    public String initForm(Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_WINE, Wine.builder().build());
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/new")
    public String processForm(@Valid Wine wine, BindingResult result) {
        if (result.hasErrors()) {
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Wine savedWine = wineService.save(wine);
            return "redirect:/wine/" + savedWine.getId();
        }
    }

    @GetMapping("{wineId}/edit")
    public String initEditWineForm(@PathVariable Long wineId, Model model) {
        model.addAttribute(wineService.findById(wineId));
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("{wineId}/edit")
    public String processEditWineForm(@Valid Wine wine, BindingResult result, @PathVariable Long wineId) {
        if(result.hasErrors()) {
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            wine.setId(wineId);
            Wine savedWine = wineService.save(wine);
            return "redirect:/wine/" + savedWine.getId();
        }
    }

}
