package com.cellar.wine.controllers;

import com.cellar.wine.services.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping("/list")
    public String showAllRegions(Model model) {
        model.addAttribute("regions", regionService.findAll());
        return "region/regionList";
    }
}
