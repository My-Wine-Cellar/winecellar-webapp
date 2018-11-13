package com.cellar.wine.controllers;

import com.cellar.wine.model.Producer;
import com.cellar.wine.model.Wine;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Producer producer = new Producer();
        model.addAttribute("addproducer", producer);
        return "producers/createproducer";
    }

    @PostMapping("/saveProducer")
    public String saveForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "/producers";
        } else {
            producerService.save(producer);
            return "redirect:/producers";
        }
    }
}
