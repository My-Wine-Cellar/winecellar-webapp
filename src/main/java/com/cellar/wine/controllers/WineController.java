package com.cellar.wine.controllers;

import com.cellar.wine.model.Wine;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/wines")
@Controller
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listWines(Model model){
        model.addAttribute("wines", wineService.findAll());
        return "wines/index";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Wine wine = new Wine();
        model.addAttribute("addwine", wine);
        return "wines/createwine";
    }

    @PostMapping("/saveWine")
    public String saveForm(@Valid Wine wine, BindingResult result) {
        if (result.hasErrors()) {
            return "/wines";
        } else {
            wineService.save(wine);
            return "redirect:/wines";
        }
    }

}
