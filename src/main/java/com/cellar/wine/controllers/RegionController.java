package com.cellar.wine.controllers;

import com.cellar.wine.models.Region;
import com.cellar.wine.services.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/{regionId}")
    public String regionDetails(@PathVariable Long regionId, Model model) {
        model.addAttribute("region", regionService.findById(regionId));
        return "region/regionDetails";
    }

    @GetMapping("/{regionId}/edit")
    public String initEditRegionForm(@PathVariable Long regionId, Model model) {
        model.addAttribute("region", regionService.findById(regionId));
        return "region/editRegion";
    }

    @PostMapping("/{regionId}/edit")
    public String processEditRegionForm(@Valid Region region, BindingResult result, @PathVariable Long regionId) {
        if(result.hasErrors()) {
            return "region/editRegion";
        } else {
            region.setId(regionId);
            regionService.save(region);
            return "redirect:/region/" + regionId;
        }
    }
}
