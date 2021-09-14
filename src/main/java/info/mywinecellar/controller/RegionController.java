/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/region")
public class RegionController extends AbstractController {

    /**
     * Default constructor
     */
    public RegionController() {
        super();
    }

    /**
     * @param regionId  regionId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{regionId}/edit")
    public String regionEditGet(@PathVariable Long regionId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.REGION, RegionConverter.toDto(regionService.findById(regionId)));
        return Paths.REGION_EDIT;
    }

    /**
     * @param regionDto region
     * @param result    result
     * @param regionId  regionId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{regionId}/edit")
    public String processEditRegionForm(@ModelAttribute(Attributes.REGION) @Valid RegionDto regionDto,
                                        BindingResult result, Principal principal,
                                        @PathVariable Long regionId,
                                        @RequestParam("action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            return Paths.REGION_EDIT;
        } else {
            if (action.equals("save")) {
                Region region = regionService.editRegion(regionDto, regionId);
                return redirectRegion(Session.getCountryId(), region);
            } else {
                return redirectRegion(Session.getCountryId(), regionId);
            }
        }
    }
}
