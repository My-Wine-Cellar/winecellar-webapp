package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Grape;
import com.cellar.wine.models.Producer;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.GrapeService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.RegionUI;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Log
@Controller
@RequestMapping("/area")
public class AreaController {

    private static final String MODEL_ATTRIBUTE_AREA = "area";
    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";
    private static final String MODEL_ATTRIBUTE_WHITE_GRAPES = "whiteGrapes";
    private static final String MODEL_ATTRIBUTE_RED_GRAPES = "redGrapes";

    private AreaService areaService;
    private ProducerService producerService;
    private GrapeService grapeService;

    public AreaController(AreaService areaService, ProducerService producerService, GrapeService grapeService) {
        this.areaService = areaService;
        this.producerService = producerService;
        this.grapeService = grapeService;
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
            Area savedArea = areaService.save(area);
            AreaUI aui = new AreaUI(savedArea);
            RegionUI rui = new RegionUI(savedArea.getRegions().get(0));
            CountryUI cui = new CountryUI(savedArea.getRegions().get(0).getCountry());

            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey();
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
            Producer savedProducer = producerService.save(producer);
            AreaUI aui = new AreaUI(area);
            RegionUI rui = new RegionUI(area.getRegions().get(0));
            CountryUI cui = new CountryUI(area.getRegions().get(0).getCountry());

            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey();
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

            AreaUI aui = new AreaUI(a);
            RegionUI rui = new RegionUI(a.getRegions().get(0));
            CountryUI cui = new CountryUI(a.getRegions().get(0).getCountry());

            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey();
        }
    }
}
