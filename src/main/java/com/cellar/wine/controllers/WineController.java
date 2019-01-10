package com.cellar.wine.controllers;

import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.WineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/wines")
@Controller
public class WineController {

    private static final String CREATE_UPDATE_WINE_FORM = "wines/createOrUpdateWine";

    private final WineRepository wineRepository;

    public WineController(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @RequestMapping("/list")
    public String wine(Model model) {
        model.addAttribute("wines", wineRepository.findAll());
        return "wines/index";
    }

    @GetMapping("/new")
    public String initCreationForm(Wine wine, Model model) {
        model.addAttribute("wine", Wine.builder().build());
        return CREATE_UPDATE_WINE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Wine wine, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_UPDATE_WINE_FORM;
        } else {
            wineRepository.save(wine);
            return "forward:/wines/list";
        }
    }

    @GetMapping("/{wineId}/edit")
    public String initUpdateForm(@PathVariable Long wineId, Model model) {
        model.addAttribute("wine", wineRepository.findById(wineId));
        return CREATE_UPDATE_WINE_FORM;
    }

    @PostMapping("/{wineId}/edit")
    public String processUpdateForm(@Valid Wine wine, @PathVariable Long wineId, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("wine", wine);
            return CREATE_UPDATE_WINE_FORM;
        } else {
            model.addAttribute("wine", wineRepository.findById(wineId));
            return "redirect:/wines/list";
        }
    }
}
