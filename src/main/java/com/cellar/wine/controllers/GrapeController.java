package com.cellar.wine.controllers;

import com.cellar.wine.models.Grape;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.services.GrapeService;
import com.cellar.wine.ui.AbstractKeyUI;
import com.cellar.wine.ui.GrapeUI;
import com.cellar.wine.ui.GrapeUISorter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/grape")
public class GrapeController {

    @Inject
    private GrapeService grapeService;

    public GrapeController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/list")
    public String grapeListGet(Model model) {
        model.addAttribute(Attributes.RED_GRAPES, getGrapeUIs(grapeService.getRedGrapes()));
        model.addAttribute(Attributes.WHITE_GRAPES, getGrapeUIs(grapeService.getWhiteGrapes()));
        return Paths.GRAPE_LIST;
    }

    @GetMapping("/{grape}")
    public String grapeDetails(@PathVariable String grape, Model model) {
        Grape g = grapeService.findByLowerCaseName(AbstractKeyUI.fromKey(grape));

        if (g == null)
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.GRAPE, getGrapeUI(g));
        return Paths.GRAPE_DETAILS;
    }

    @GetMapping("/{grapeId}/edit")
    public String grapeEditGet(@PathVariable Long grapeId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        Grape grape = grapeService.findById(grapeId);

        if (grape == null)
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.GRAPE, grape);
        return Paths.GRAPE_EDIT;
    }

    @PostMapping("/{grapeId}/edit")
    public String grapeEditPost(@Valid Grape grape, BindingResult result, Model model,
                                @PathVariable Long grapeId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.GRAPE, grape);
            return Paths.GRAPE_EDIT;
        } else {
            grape.setId(grapeId);
            Grape savedGrape = grapeService.save(grape);
            GrapeUI ui = getGrapeUI(savedGrape);
            return Paths.REDIRECT_GRAPE + ui.getKey();
        }
    }

    private List<GrapeUI> getGrapeUIs(Set<Grape> grapes) {
        List<GrapeUI> result = new ArrayList<>();
        for (Grape g : grapes) {
            result.add(getGrapeUI(g));
        }
        Collections.sort(result, new GrapeUISorter());
        return result;
    }

    private GrapeUI getGrapeUI(Grape g) {
        return new GrapeUI(g);
    }
}
