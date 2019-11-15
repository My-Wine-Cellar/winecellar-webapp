/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.controllers;

import com.cellar.wine.models.Region;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.nav.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/region")
public class RegionController extends AbstractController {

    public RegionController() {
        super();
    }

    @GetMapping("/{regionId}/edit")
    public String regionEditGet(@PathVariable Long regionId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }
        model.addAttribute(Attributes.REGION, regionService.findById(regionId));
        return Paths.REGION_EDIT;
    }

    @PostMapping("/{regionId}/edit")
    public String processEditRegionForm(@Valid Region region, BindingResult result, Model model,
                                        @PathVariable Long regionId, Principal principal,
                                        @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.REGION, regionService.findById(regionId));
            return Paths.REGION_EDIT;
        } else {
            if (action.equals("save")) {
                region.setId(regionId);
                regionService.save(region);
                return redirectRegion(Session.getCountryId(), region);
            } else {
                return redirectRegion(Session.getCountryId(), region);
            }
        }
    }
}
