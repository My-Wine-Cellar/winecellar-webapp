package com.cellar.wine.controllers;

import com.cellar.wine.models.Region;
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

    private static final String MODEL_ATTRIBUTE_REGION = "region";

    public RegionController() {
    }

    @GetMapping("/{regionId}/edit")
    public String regionEditGet(@PathVariable Long regionId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute(MODEL_ATTRIBUTE_REGION, regionService.findById(regionId));
        return "region/editRegion";
    }

    @PostMapping("/{regionId}/edit")
    public String processEditRegionForm(@Valid Region region, BindingResult result,
                                        @PathVariable Long regionId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "region/editRegion";
        } else {
            region.setId(regionId);
            regionService.save(region);

            return redirectRegion(Session.getCountryId(), region);
        }
    }
}
