package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Grape;
import com.cellar.wine.models.Producer;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.nav.Session;
import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController {

    @Inject
    private GrapeService grapeService;

    public AreaController() {
        super();
    }

    @InitBinder("area")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{areaId}/edit")
    public String areaEditGet(@PathVariable Long areaId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.AREA, areaService.findById(areaId));
        return Paths.AREA_EDIT;
    }

    @PostMapping("/{areaId}/edit")
    public String areaEditPost(@Valid Area area, BindingResult result, Model model,
                               @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.AREA, area);
            return Paths.AREA_EDIT;
        } else {
            area.setId(areaId);
            areaService.save(area);

            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
    }

    @GetMapping("/{areaId}/addProducer")
    public String areaAddProducerGet(Model model, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.AREA, areaService.findById(areaId));
        model.addAttribute(Attributes.PRODUCER, new Producer());
        return Paths.PRODUCER_ADD_EDIT;
    }

    @PostMapping("/{areaId}/addProducer")
    public String areaAddProducerPost(@Valid Producer producer, BindingResult result, Model model,
                                      @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.PRODUCER, producer);
            return Paths.PRODUCER_ADD_EDIT;
        } else {
            Area area = areaService.findById(areaId);
            area.getProducers().add(producer);
            producerService.save(producer);

            return redirectArea(Session.getCountryId(), Session.getRegionId(), area);
        }
    }

    @RequestMapping("/{areaId}/addGrape")
    public String areaAddGrapeGet(Model model, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.AREA, areaService.findById(areaId));
        model.addAttribute(Attributes.RED_GRAPES, grapeService.getRedGrapes());
        model.addAttribute(Attributes.WHITE_GRAPES, grapeService.getWhiteGrapes());
        return Paths.GRAPE_ADD_TO_AREA;
    }

    @PostMapping("/{areaId}/addGrape")
    public String areaAddGrapePost(@Valid Area area, BindingResult result, Model model,
                                   @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.AREA, area);
            return Paths.GRAPE_ADD_TO_AREA;
        } else {
            Area a = areaService.findById(areaId);

            List<Grape> grapes = area.getPrimaryGrapes();
            grapes.forEach(grape -> grape.getAreas().add(a));

            areaService.save(a);

            return redirectArea(Session.getCountryId(), Session.getRegionId(), a);
        }
    }
}
