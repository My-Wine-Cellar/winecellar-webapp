package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public ModelAndView listProducers(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return new ModelAndView("producers/index");
    }
}
