package com.cellar.wine.controllers;

import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setAllowedFields("id");
    }

    @RequestMapping("/list")
    public String producer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/index";
    }

    @RequestMapping("/{producerId}")
    public String producerDetails(@PathVariable("producerId") Long producerId, Model model) {
        model.addAttribute("producerId", producerService.findById(producerId));
        return "producers/details";
    }

}


//TODO implement in v2.0

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