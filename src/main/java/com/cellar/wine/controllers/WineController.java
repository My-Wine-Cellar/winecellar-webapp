package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/wines")
@Controller
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @RequestMapping("/list")
    public String wine(Model model) {
        model.addAttribute("wines", wineService.findAll());
        return "wines/index";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Wine wine = new Wine();
        model.addAttribute("addwine", wine);
        return "wines/create-wine";
    }

    @PostMapping("/saveWine")
    public String saveForm(@Valid Wine wine, Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "/wines";
        } else {
            producer.isNew();
            producer.getWines().add(wine);
            producerService.save(producer);
            wine.setProducer(producer);
            wineService.save(wine);
            return "redirect:/wines/list";
        }
    }
}