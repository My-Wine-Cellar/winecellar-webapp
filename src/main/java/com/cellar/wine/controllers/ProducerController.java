package com.cellar.wine.controllers;

import com.cellar.wine.model.Producer;
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

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    //working
    @RequestMapping("/find")
    public String findProducers(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return "producers/findProducers";
    }

    //close to working...
    @GetMapping("/producersList")
    public String listProducers(Model model, Producer producer) {
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute(producer);
        return "producers/producersList";
    }

    //working
    @GetMapping("/{producerId}")
    //@PathVariable annotation enables controllers to handle requests for parameterized URL's
    public ModelAndView showProducer(@PathVariable Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producerDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }

    //working
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return "producers/createOrUpdateProducerForm";
    }

    //working
    @PostMapping("/new")
    public String processCreationForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "producers/createOrUpdateProducerForm";
        } else {
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    //working
    @GetMapping("/{producerId}/edit")
    //@PathVariable annotation enables controllers to handle requests for parameterized URL's
    public String initUpdateProducerForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return "producers/createOrUpdateProducerForm";
    }

    //working
    @PostMapping("/{producerId}/edit")
    public String processUpdateProducerForm(@Valid Producer producer, BindingResult result, @PathVariable Long producerId) {
        if (result.hasErrors()) {
            return "producers/createOrUpdateProducerForm";
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }
}