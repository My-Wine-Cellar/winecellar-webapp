package com.cellar.wine.controllers;

import com.cellar.wine.models.Grape;
import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/grape")
public class GrapeController {

    private GrapeService grapeService;

    public GrapeController(GrapeService grapeService) {
        this.grapeService = grapeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/list")
    public String showAllGrapes(Model model) {
        model.addAttribute("whiteGrapes", grapeService.getWhiteGrapes());
        model.addAttribute("redGrapes", grapeService.getRedGrapes());
        return "grape/grapeList";
    }

    @GetMapping("/{grapeId}")
    public String grapeDetails(@PathVariable Long grapeId, Model model) {
        model.addAttribute("grape", grapeService.findById(grapeId));
        return "grape/grapeDetails";
    }

    @GetMapping("/{grapeId}/edit")
    public String initEditGrapeForm(@PathVariable Long grapeId, Model model) {
        model.addAttribute("grape", grapeService.findById(grapeId));
        return "grape/editGrape";
    }

    @PostMapping("/{grapeId}/edit")
    public String processEditGrapeForm(@Valid Grape grape, BindingResult result, @PathVariable Long grapeId) {
        if(result.hasErrors()) {
            return "grape/editGrape";
        } else {
            grape.setId(grapeId);
            grapeService.save(grape);
            return "redirect:/grape/" + grapeId;
        }
    }
}
