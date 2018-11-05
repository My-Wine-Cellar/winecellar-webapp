package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String listProducers(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/index";
    }

    @RequestMapping("/find")
    public ModelAndView findProducers(@PathVariable("producersName") String producerName) {
        ModelAndView mav = new ModelAndView("producers/findProducers");
        mav.addObject(producerService.findByName(producerName));
        return mav;
    }

    @GetMapping("/{producersId}")
    public ModelAndView showProducer(@PathVariable("producersId") Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producersDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }
}
