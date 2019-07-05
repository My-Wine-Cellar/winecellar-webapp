package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/area")
public class AreaController {

    private AreaService areaService;
    private ProducerService producerService;

    public AreaController(AreaService areaService, ProducerService producerService) {
        this.areaService = areaService;
        this.producerService = producerService;
    }

    @GetMapping("/{areaId}")
    public String areaDetails(@PathVariable Long areaId, Model model) {
        model.addAttribute("area", areaService.findById(areaId));
        return "area/areaDetails";
    }

    @GetMapping("/{areaId}/edit")
    public String initAddEditAreaForm(@PathVariable Long areaId, Model model) {
        model.addAttribute("area", areaService.findById(areaId));
        model.addAttribute("producer", producerService.findAll());
        return "area/editArea";
    }

    @PostMapping("/{areaId}/edit")
    public String processAddEditAreaForm(@Valid Area area, BindingResult result, @PathVariable Long areaId) {
        if(result.hasErrors()) {
            return "area/editArea";
        } else {
            area.setId(areaId);
            Area savedArea = areaService.save(area);
            return "redirect:/area/" + savedArea.getId();
        }
    }
}
