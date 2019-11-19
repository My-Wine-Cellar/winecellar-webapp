/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.Grape;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.ui.AbstractKeyUI;
import info.mywinecellar.ui.GrapeUI;
import info.mywinecellar.ui.GrapeUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/grape")
public class GrapeController extends AbstractController {

    public GrapeController() {
        super();
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/list")
    public String grapeListGet(Model model) {
        model.addAttribute(Attributes.RED_GRAPES, GrapeUIFactory.instance().createList(grapeService.getRedGrapes()));
        model.addAttribute(Attributes.WHITE_GRAPES, GrapeUIFactory.instance().createList(grapeService.getWhiteGrapes()));
        return Paths.GRAPE_LIST;
    }

    @GetMapping("/{grape}")
    public String grapeDetails(@PathVariable String grape, Model model) {
        Grape g = grapeService.findByLowerCaseName(AbstractKeyUI.fromKey(grape));

        if (g == null)
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.GRAPE, GrapeUIFactory.instance().create(g));
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
                                @PathVariable Long grapeId, Principal principal,
                                @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.GRAPE, grape);
            return Paths.GRAPE_EDIT;
        } else {
            if (action.equals("save")) {
                grape.setId(grapeId);
                Grape savedGrape = grapeService.save(grape);
                GrapeUI ui = GrapeUIFactory.instance().create(savedGrape);
                return Paths.REDIRECT_GRAPE + ui.getKey();
            } else {
                Grape g = grapeService.findByLowerCaseName(AbstractKeyUI.fromKey(grape.getName()));
                GrapeUI grapeUI = GrapeUIFactory.instance().create(g);
                return Paths.REDIRECT_GRAPE + grapeUI.getKey();
            }
        }
    }

}
