package com.cellar.wine.controllers;

import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wine")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/list")
    public String showAllWines(Model model) {
        model.addAttribute("wines", wineService.findAll());
        return "wine/wineList";
    }

    @GetMapping("/{wineId}")
    public String wineDetails(@PathVariable Long wineId, Model model) {
        model.addAttribute("wine", wineService.findById(wineId));
        return "wine/wineDetails";
    }

}
