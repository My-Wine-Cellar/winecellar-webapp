package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
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

    @RequestMapping("/list")
    public String producer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/index";
    }

    @GetMapping("/{producerId}")
    public ModelAndView showProducer(@PathVariable Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producerDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute("producer", Producer.builder().build());
        return "producers/createOrUpdateProducer";
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "producers/createOrUpdateProducer";
        } else {
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateProducerForm(@PathVariable("producerId") Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return "producers/createOrUpdateProducer";
    }

    @PostMapping("/{producerId}/edit")
    public String processUpdateProducerForm(@Valid Producer producer, BindingResult result, @PathVariable("producerId") Long producerId) {
        if(result.hasErrors()) {
            return "producers/createOrUpdateProducer";
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }
}


//TODO implement find in v2.0

//    @RequestMapping("/find")
//    public String findProducers(Model model, Producer producer) {
//        model.addAttribute("producer",  producer);
//        return "producers/findProducers";
//    }
//
//    //sort of working, but not really at all...12.19.18
//    @GetMapping
//    public String processFindForm(Producer producer, BindingResult result, Model model) {
//        //allow parameterless GET request for producers to return all records
//        if (producer.getName() == null) {
//            producer.setName(""); //empty string signifies broadest possible search
//        }
//
//        //find producers by name
//        List<Producer> results = producerService.findAllByNameLike("%" + producer.getName() + "%");
//
//        if (results.isEmpty()) {
//            //no producers found
//            result.rejectValue("name", "notFound", "Not Found");
//            return "producers/findProducers";
//        } else if (results.size() == 1) {
//            //1 producer found
//            producer = results.iterator().next();
//            return "redirect:/producers/" + producer.getId();
//        } else {
//            //multiple producers found
//            model.addAttribute("selections", results);
//            return "producers/producersList";
//        }
//    }