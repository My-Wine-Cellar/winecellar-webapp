package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/wines")
@Controller
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @RequestMapping("/winelist")
    public String wine(Model model) {
        model.addAttribute("wines", wineService.findAll());
        return "wines/index";
    }

    @RequestMapping("/{wineId}")
    public String wineDetails(@PathVariable Long wineId, Wine wine, Model model) {
        model.addAttribute("wineId", wineService.findById(wineId));
        wine.setProducer(Producer.builder().build());
        return "wines/wine-details";
    }


}