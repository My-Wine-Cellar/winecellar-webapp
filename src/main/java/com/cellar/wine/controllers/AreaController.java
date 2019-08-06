package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Grape;
import com.cellar.wine.models.Producer;
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

    private static final String MODEL_ATTRIBUTE_AREA = "area";
    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";
    private static final String MODEL_ATTRIBUTE_WHITE_GRAPES = "whiteGrapes";
    private static final String MODEL_ATTRIBUTE_RED_GRAPES = "redGrapes";

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
            return "redirect:/";
        }

        model.addAttribute(MODEL_ATTRIBUTE_AREA, areaService.findById(areaId));
        return "area/editArea";
    }

    @PostMapping("/{areaId}/edit")
    public String areaEditPost(@Valid Area area, BindingResult result, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "area/editArea";
        } else {
            area.setId(areaId);
            areaService.save(area);

            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
    }

    @GetMapping("/{areaId}/addProducer")
    public String areaAddProducerGet(Model model, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute(MODEL_ATTRIBUTE_AREA, areaService.findById(areaId));
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, new Producer());
        return "producer/addEditProducer";
    }

    @PostMapping("/{areaId}/addProducer")
    public String areaAddProducerPost(@Valid Producer producer, BindingResult result,
                                      @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "producer/addEditProducer";
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
            return "redirect:/";
        }

        model.addAttribute(MODEL_ATTRIBUTE_AREA, areaService.findById(areaId));
        model.addAttribute(MODEL_ATTRIBUTE_RED_GRAPES, grapeService.getRedGrapes());
        model.addAttribute(MODEL_ATTRIBUTE_WHITE_GRAPES, grapeService.getWhiteGrapes());
        return "grape/addGrapeToArea";
    }

    @PostMapping("/{areaId}/addGrape")
    public String areaAddGrapePost(@Valid Area area, BindingResult result,
                                   @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "grape/addGrapeToArea";
        } else {
            Area a = areaService.findById(areaId);

            List<Grape> grapes = area.getPrimaryGrapes();
            grapes.forEach(grape -> grape.getAreas().add(a));

            areaService.save(a);

            return redirectArea(Session.getCountryId(), Session.getRegionId(), a);
        }
    }
}
