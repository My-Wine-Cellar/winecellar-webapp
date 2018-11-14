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

    @RequestMapping("/find")
    public String findProducers(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return "producers/findProducers";
    }

    @GetMapping
    public String processFindForm(Producer producer, BindingResult result, Model model) {
        //allow parameterless GET request for /producers to return all records
        if (producer.getName() == null) {
            producer.setName(""); //empty string signifies broadest possible search
        }

        Producer results = producerService.findByName("%" + producer.getName() + "%");

        if (results == null) {
            result.rejectValue("name", "not found", "not found");
            return "producers/findProducers";
        } else {
            model.addAttribute("selections", results);
            return "producers/producersList";
        }
    }

    @GetMapping("/{producerId}")
    public ModelAndView showProducer(@PathVariable Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producerDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return "producers/createOrUpdateProducerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "producers/createOrUpdateProducerForm";
        } else {
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateProducerForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return "producers/createOrUpdateProducerForm";
    }

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
    //MY OLD CODE
/*
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

    @RequestMapping("/find")
    public String findProducers(Model model) {
        model.addAttribute("producer", Producer.builder());
        return "producers/findProducers";
    }

    @GetMapping("/producers")
    public String processFindForm(Producer producer, BindingResult result, Model model) {
        return "";
    }
*/
