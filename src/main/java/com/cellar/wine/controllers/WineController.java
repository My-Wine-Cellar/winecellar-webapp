package com.cellar.wine.controllers;

import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
