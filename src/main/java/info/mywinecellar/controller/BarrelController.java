/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.BarrelConverter;
import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.model.Barrel;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.BarrelService;

import java.security.Principal;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

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

@Controller
@RequestMapping("/barrel")
public class BarrelController extends AbstractController {

    @Inject
    BarrelService barrelService;

    /**
     * Default constructor
     */
    public BarrelController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param barrel barrel
     * @param model  model
     * @return View
     */
    @GetMapping("/{barrel}")
    public String barrelDetails(@PathVariable String barrel, Model model) {
        List<Barrel> barrels = barrelService.findByLowerCaseName(AbstractKeyDto.fromKey(barrel) + "%");

        if (barrels == null || barrels.isEmpty()) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.BARREL, BarrelConverter.toDto(barrels.get(0)));
        return Paths.BARREL_DETAILS;
    }

    /**
     * @param barrelId  barrelId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{barrelId}/edit")
    public String barrelEditGet(@PathVariable Long barrelId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        Barrel barrel = barrelService.findById(barrelId);

        if (barrel == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.BARREL, BarrelConverter.toDto(barrel));
        return Paths.BARREL_EDIT;
    }

    /**
     * @param barrel    barrel
     * @param result    result
     * @param model     model
     * @param barrelId  barrelId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{barrelId}/edit")
    public String barrelEditPost(@Valid BarrelDto barrel, BindingResult result, Model model,
                                 @PathVariable Long barrelId, Principal principal,
                                 @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.BARREL, barrel);
            return Paths.BARREL_EDIT;
        } else {

            Barrel b = barrelService.findById(barrelId);
            b.setDescription(barrel.getDescription());
            b.setWeblink(barrel.getWeblink());

            if (action.equals("save")) {
                barrelService.save(b);
                BarrelDto barrelDto = BarrelConverter.toDto(b);
                return Paths.REDIRECT_BARREL + barrelDto.getKey();
            } else {
                BarrelDto cancelBarrel = BarrelConverter.toDto(b);
                return Paths.REDIRECT_BARREL + cancelBarrel.getKey();
            }
        }
    }
}
