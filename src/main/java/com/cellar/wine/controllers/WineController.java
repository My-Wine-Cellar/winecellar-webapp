package com.cellar.wine.controllers;

import com.cellar.wine.repositories.WineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WineController {

    private WineRepository wineRepository;

    public WineController(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @RequestMapping("wines")
    public String getWine(Model model){
        model.addAttribute("wines", wineRepository.findAll());
        return "wines";
    }

}
