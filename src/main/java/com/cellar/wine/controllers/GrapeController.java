package com.cellar.wine.controllers;

import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grape")
public class GrapeController {

    private GrapeService grapeService;

    public GrapeController(GrapeService grapeService) {
        this.grapeService = grapeService;
    }

    @GetMapping("/list")
    public String showAllGrapes(Model model) {
        model.addAttribute("grapes", grapeService.findAll());
        return "grape/grapeList";
    }

    @GetMapping("/{grapeId}")
    public String grapeDetails(@PathVariable Long grapeId, Model model) {
        model.addAttribute("grape", grapeService.findById(grapeId));
        return "grape/grapeDetails";
    }
}
