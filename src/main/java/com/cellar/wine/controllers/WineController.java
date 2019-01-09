package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/producers/{producerId}")
@Controller
public class WineController {

    private static final String CREATE_OR_UPDATE_WINE_TEMPLATE = "wines/createOrUpdateWine";

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

//    //I think this is for the find Producer controller
//    @ModelAttribute("producer")
//    public Producer findProducer(@PathVariable("producerId") Long producerId) {
//        return producerService.findById(producerId);
//    }

    @InitBinder("producer")
    public void initProducerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/wines/new")
    public String initCreationForm(Producer producer, Model model) {
        Wine wine = new Wine();
        producer.getWines().add(wine);
        wine.setProducer(producer);
        model.addAttribute("wine", wine);
        return CREATE_OR_UPDATE_WINE_TEMPLATE;
    }

    @PostMapping("/wines/new")
    public String processCreationForm(Producer producer, @Valid Wine wine, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(wine.getName()) && wine.isNew() && producer.getWine(wine.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        producer.getWines().add(wine);
        if (result.hasErrors()) {
            model.put("wine", wine);
            return CREATE_OR_UPDATE_WINE_TEMPLATE;
        } else {
            wineService.save(wine);
            return "redirect:/producers/" + producer.getId();
        }
    }

    @GetMapping("/wines/{wineId}/edit")
    public String initUpdateForm(@PathVariable Long wineId, Model model) {
        model.addAttribute("wine", wineService.findById(wineId));
        return CREATE_OR_UPDATE_WINE_TEMPLATE;
    }

    @PostMapping("/wines/{wineId}/edit")
    public String processUpdateForm(@Valid Wine wine, BindingResult result, Producer producer, Model model) {
        if (result.hasErrors()) {
            wine.setProducer(producer);
            model.addAttribute("wine", wine);
            return CREATE_OR_UPDATE_WINE_TEMPLATE;
        } else {
            producer.getWines().add(wine);
            wineService.save(wine);
            return "redirect:/producers/" + producer.getId();
        }
    }
}
