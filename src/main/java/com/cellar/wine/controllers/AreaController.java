package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Producer;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/area/{areaId}")
public class AreaController {

    private AreaService areaService;
    private ProducerService producerService;

    public AreaController(AreaService areaService, ProducerService producerService) {
        this.areaService = areaService;
        this.producerService = producerService;
    }

    @InitBinder("area")
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("name");
    }

    @ModelAttribute("area")
    public Area areaModel(@PathVariable Long areaId) {
        return areaService.findById(areaId);
    }

    @GetMapping("/areaDetails")
    public String areaDetails(@PathVariable Long areaId, Model model) {
        model.addAttribute("area", areaService.findById(areaId));
        return "area/areaDetails";
    }

    @GetMapping("/edit")
    public String initAddEditAreaForm(@PathVariable Long areaId, Model model) {
        model.addAttribute("area", areaService.findById(areaId));
        return "area/editArea";
    }

    @PostMapping("/edit")
    public String processAddEditAreaForm(@Valid Area area, BindingResult result, @PathVariable Long areaId) {
        if(result.hasErrors()) {
            return "area/editArea";
        } else {
            area.setId(areaId);
            Area savedArea = areaService.save(area);
            return "redirect:/area/" + savedArea.getId() + "/areaDetails";
        }
    }

    @GetMapping("/addProducer")
    public String initAddProducerForm(Model model, @PathVariable Long areaId) {
        model.addAttribute("area", areaService.findById(areaId));
        model.addAttribute("producer", Producer.builder().build());
        return "producer/addEditProducer";
    }

    @PostMapping("/addProducer")
    public String processAddProducerForm(@Valid Area area, BindingResult result, Producer producer, @PathVariable Long areaId) {
        if(result.hasErrors()) {
            return "producer/addEditProducer";
        } else {
            area.getProducers().add(producer);
            producerService.save(producer);
            return "redirect:/area/" + areaId + "/areaDetails";
        }
    }
}
