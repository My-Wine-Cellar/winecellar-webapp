package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Grape;
import com.cellar.wine.models.Producer;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.GrapeService;
import com.cellar.wine.services.ProducerService;
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
@RequestMapping("/area/{areaId}")
public class AreaController {

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
        dataBinder.setDisallowedFields("name");
    }

    @ModelAttribute("area")
    public Area areaModel(@PathVariable Long areaId) {
        return areaService.findById(areaId);
    }

    @GetMapping
    public String areaDetails(@PathVariable Long areaId, Model model) {
        model.addAttribute("area", areaService.findById(areaId));
        return "area/areaDetails";
    }

    @GetMapping("/edit")
    public String areaEditGet(@PathVariable Long areaId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("area", areaService.findById(areaId));
        return "area/editArea";
    }

    @PostMapping("/edit")
    public String areaEditPost(@Valid Area area, BindingResult result, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "area/editArea";
        } else {
            area.setId(areaId);
            Area savedArea = areaService.save(area);
            return "redirect:/area/" + savedArea.getId();
        }
    }

    @GetMapping("/addProducer")
    public String areaAddProducerGet(Model model, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("area", areaService.findById(areaId));
        model.addAttribute("producer", new Producer());
        return "producer/addEditProducer";
    }

    @PostMapping("/addProducer")
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
            return "redirect:/area/" + areaId;
        }
    }

    @RequestMapping("/addGrape")
    public String areaAddGrapeGet(Model model, @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("area", areaService.findById(areaId));
        model.addAttribute("redGrapes", grapeService.getRedGrapes());
        model.addAttribute("whiteGrapes", grapeService.getWhiteGrapes());
        return "grape/addGrapeToArea";
    }

    @PostMapping("/addGrape")
    public String areaAddGrapePost(@Valid Area area, BindingResult result,
                                   @PathVariable Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "grape/addGrapeToArea";
        } else {
            List<Grape> grapes = area.getPrimaryGrapes();
            grapes.forEach(grape -> grape.getAreas().add(area));
            areaService.save(area);
            return "redirect:/area/" + areaId;
        }
    }
}
