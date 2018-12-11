package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @RequestMapping("/{producerId}")
//    public String producerDetails(@PathVariable Long producerId, Wine wine, Producer producer, Model model) {
//        model.addAttribute("producerId", producerService.findById(producerId));
//        producer.getWines().add(wine);
//        wine.setProducer(Producer.builder().build());
//        model.addAttribute("producer", producer.getWines().add(wine));
//        return "producers/producer-details";
//    }

}