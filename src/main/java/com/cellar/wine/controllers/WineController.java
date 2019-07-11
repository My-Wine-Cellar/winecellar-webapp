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

@Controller
@RequestMapping("/producer/{producerId}/wine")
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String ADD_OR_EDIT_WINE_TEMPLATE = "wine/addEditWine";

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable Long producerId) {
        return producerService.findById(producerId);
    }

    @InitBinder("producer")
    public void initProducerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "description", "name");
    }

    @GetMapping("/{wineId}")
    public String wineDetails(@PathVariable Long wineId, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wineService.findById(wineId));
        return "wine/wineDetails";
    }

    @GetMapping("/new")
    public String initNewWineForm(Producer producer, Model model) {
        Wine wine = new Wine();
        producer.getWines().add(wine);
        wine.setProducer(producer);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/new")
    public String processNewWineForm(Producer producer, @Valid Wine wine, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(wine.getName()) && wine.isNew() && producer.getWine(wine.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "This wine already exists");
        }
        wine.setProducer(producer);
        producer.getWines().add(wine);
        if (result.hasErrors()) {
            model.put(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            wineService.save(wine);
            return "redirect:/producer/" + producer.getId();
        }
    }

    @GetMapping("/{wineId}/edit")
    public String initEditWineForm(@PathVariable Long wineId, Model model) {
        model.addAttribute(wineService.findById(wineId));
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/{wineId}/edit")
    public String processEditWineForm(@Valid Wine wine, BindingResult result, Producer producer, Model model, @PathVariable Long wineId) {
        wine.setProducer(producer);
        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Wine savedWine = wineService.save(wine);
            producer.getWines().add(savedWine);
            return "redirect:/producer/" + producer.getId() + "/wine/" + wineId;
        }
    }

}
