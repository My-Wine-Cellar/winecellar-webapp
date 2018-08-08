package com.cellar.wine.controllers;

import com.cellar.wine.repositories.ProducerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProducerController {

    private ProducerRepository producerRepository;

    public ProducerController(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @RequestMapping("producers")
    public String getProducers(Model model) {
        model.addAttribute("producers", producerRepository.findAll());
        return "producers";
    }
}
