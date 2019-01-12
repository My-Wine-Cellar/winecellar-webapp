package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private static final String CREATE_OR_UPDATE_PRODUCER_TEMPLATE = "producers/createOrUpdateProducer";

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/list")
    public String producer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/producersList";
    }

    @GetMapping("/{producerId}")
    public ModelAndView showProducer(@PathVariable Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producerDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
        } else {
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateProducerForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
    }

    @PostMapping("/{producerId}/edit")
    public String processUpdateProducerForm(@Valid Producer producer, BindingResult result, @PathVariable Long producerId) {
        if(result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }
}
