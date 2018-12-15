package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping("/producerlist")
    public String producer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/index";
    }

    @RequestMapping("/{producerId}")
    public String producerDetails(@PathVariable("producerId") Long id, Model model) {
        model.addAttribute("producerId", producerService.findById(new Long(id)));
        return "producers/details";
    }

}