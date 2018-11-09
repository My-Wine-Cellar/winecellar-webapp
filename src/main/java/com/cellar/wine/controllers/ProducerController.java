package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private static final String FIND_PRODUCER_BY_NAME_VIEW = "producers/findProducers";
    private static final String CREATE_OR_UPDATE_PRODUCER_VIEW = "producers/createOrUpdateProducerForm";
    private static final String SHOW_PRODUCER_BY_ID_VIEW = "producers/producerDetails";
    private static final String PRODUCER_INDEX_VIEW = "producers/index";

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listProducers(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return PRODUCER_INDEX_VIEW;
    }

    /*
    //Not working
    @RequestMapping("/producerName")
    public String findProducerByName(@PathVariable String producerName, Model model) {
        model.addAttribute("producerName", producerService.findByName(producerName));
        return FIND_PRODUCER_BY_NAME_VIEW;
    }

    //Not working
    @GetMapping("/{producerId}")
    public String showProducer(@PathVariable Long producerId, Model model) {
        model.addAttribute("producerId", producerService.findById(producerId));
        return SHOW_PRODUCER_BY_ID_VIEW;
    }

    //Sort of working
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return CREATE_OR_UPDATE_PRODUCER_VIEW;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_VIEW;
        } else {
            producerService.save(producer);
            return "redirect:/producers/" + producer.getId();
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return CREATE_OR_UPDATE_PRODUCER_VIEW;
    }

    @PostMapping("/{producerId}/edit")
    public String processUpdateForm(@Valid Producer producer, BindingResult result, @PathVariable Long producerId) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_VIEW;
        } else {
            producer.setId(producerId);
            producerService.save(producer);
            return "redirect:/producers/{producerId}" + producer.getId();
        }
    }
    */
}
