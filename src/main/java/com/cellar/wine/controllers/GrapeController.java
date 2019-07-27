package com.cellar.wine.controllers;

import com.cellar.wine.models.Grape;
import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
    public String grapeListGet(Model model) {
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
    public String grapeEditGet(@PathVariable Long grapeId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("grape", grapeService.findById(grapeId));
        return "grape/editGrape";
    }

    @PostMapping("/{grapeId}/edit")
    public String grapeEditPost(@Valid Grape grape, BindingResult result, @PathVariable Long grapeId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "grape/editGrape";
        } else {
            grape.setId(grapeId);
            Grape savedGrape = grapeService.save(grape);
            return "redirect:/grape/" + savedGrape.getId();
        }
    }
}
