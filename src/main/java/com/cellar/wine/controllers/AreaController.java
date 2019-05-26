package com.cellar.wine.controllers;

import com.cellar.wine.services.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/area")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping("/list")
    public String showAllAreas(Model model) {
        model.addAttribute("areas", areaService.findAll());
        return "area/areaList";
    }
}
