package com.cellar.wine.controllers;

import com.cellar.wine.services.impl.ProducerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private ProducerServiceImpl producerService;

    public ProducerController(ProducerServiceImpl producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/list")
    public String showAllProducers(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producer/producerList";
    }

    @GetMapping("/{producerId}")
    public String producerDetails(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findById(producerId));
        return "producer/producerDetails";
    }
}
