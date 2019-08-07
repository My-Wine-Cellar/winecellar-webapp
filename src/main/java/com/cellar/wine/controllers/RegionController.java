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

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/region")
public class RegionController extends AbstractController {

    public RegionController() {
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
                                        @PathVariable Long regionId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.REGION, regionService.findById(regionId));
            return Paths.REGION_EDIT;
        } else {
            region.setId(regionId);
            regionService.save(region);

            return redirectRegion(Session.getCountryId(), region);
        }
    }
}
