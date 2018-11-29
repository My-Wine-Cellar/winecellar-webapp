package com.cellar.wine.controllers;

import com.cellar.wine.model.Producer;
import com.cellar.wine.model.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/producers/{producerId}")
@Controller
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable("producerId") Long producerId) {
        return producerService.findById(producerId);
    }

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
        return "wines/createOrUpdateWineForm";
    }

    @PostMapping("/wines/new")
    public String processCreationForm(Producer producer, @Valid Wine wine, BindingResult result, ModelMap model) {
//        if (StringUtils.hasLength(wine.getName()) && wine.isNew() && producer.getWines(wine.getName(), true) != null){
//            result.rejectValue("name", "duplicate", "already exists");
//        }
        producer.getWines().add(wine);
        if (result.hasErrors()) {
            model.put("wine", wine);
            return "wines/createOrUpdateWineForm";
        } else {
            wineService.save(wine);
            return "redirect:/producers/" + producer.getId();
        }
    }

    @GetMapping("/wines/{wineId}/edit")
    public String initUpdateForm(@PathVariable Long wineId, Model model) {
        model.addAttribute("wine", wineService.findById(wineId));
        return "wines/createOrUpdateWineForm";
    }

    @PostMapping("/wines/{wineId}/edit")
    public String processUpdateForm(@Valid Wine wine, BindingResult result, Producer producer, Model model) {
        if (result.hasErrors()) {
            wine.setProducer(producer);
            model.addAttribute("wine", wine);
            return "wines/createOrUpdateWineForm";
        } else {
            producer.getWines().add(wine);
            wineService.save(wine);
            return "redirect:/producers/" + producer.getId();
        }
    }



}

//MY OLD CODE
/*
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listWines(Model model){
        model.addAttribute("wines", wineService.findAll());
        return "wines/index";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Wine wine = new Wine();
        model.addAttribute("addwine", wine);
        return "wines/createwine";
    }

    @PostMapping("/saveWine")
    public String saveForm(@Valid Wine wine, BindingResult result) {
        if (result.hasErrors()) {
            return "/wines";
        } else {
            wineService.save(wine);
            return "redirect:/wines";
        }
    }
 */
