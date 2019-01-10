package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.repositories.ProducerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private static final String CREATE_UPDATE_PRODUCER_FORM = "producers/createOrUpdateProducer";

    private final ProducerRepository producerRepository;

    public ProducerController(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @ModelAttribute("producer")


    @RequestMapping("/list")
    public String producer(Model model) {
        model.addAttribute("producers", producerRepository.findAll());
        return "producers/index";
    }

//    @GetMapping("/{producerId}")
//    public ModelAndView showProducer(@PathVariable Long producerId) {
//        ModelAndView mav = new ModelAndView("producers/producerDetails");
//        mav.addObject("producer", producerRepository.findById(producerId));
//        return mav;
//    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return CREATE_UPDATE_PRODUCER_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_UPDATE_PRODUCER_FORM;
        } else {
            producerRepository.save(producer);
            return "forward:/producers/list";
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateProducerForm(@PathVariable("producerId") Long producerId, Model model) {
        model.addAttribute(producerRepository.findById(producerId));
        return CREATE_UPDATE_PRODUCER_FORM;
    }

    @PostMapping("/{producerId}/edit")
    public String processUpdateProducerForm(@Valid Producer producer, BindingResult result, @PathVariable("producerId") Long producerId) {
        if(result.hasErrors()) {
            return CREATE_UPDATE_PRODUCER_FORM;
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerRepository.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }
}
