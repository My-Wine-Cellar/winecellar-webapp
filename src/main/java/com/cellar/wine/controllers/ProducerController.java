package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.services.impl.ProducerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private ProducerServiceImpl producerService;

    public ProducerController(ProducerServiceImpl producerService) {
        this.producerService = producerService;
    }

    private static final String ADD_OR_EDIT_PRODUCER_TEMPLATE = "producer/addEditProducer";

    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{producerId}")
    public String producerDetails(@PathVariable Long producerId, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, producerService.findById(producerId));
        return "producer/producerDetails";
    }

    @GetMapping("/{producerId}/edit")
    public String initAddEditProducerForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, producerService.findById(producerId));
        return ADD_OR_EDIT_PRODUCER_TEMPLATE;
    }

    @PostMapping("/{producerId}/edit")
    public String processAddEditProducerForm(@Valid Producer producer, BindingResult result, @PathVariable Long producerId) {
        if(result.hasErrors()) {
            return ADD_OR_EDIT_PRODUCER_TEMPLATE;
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producer/" + savedProducer.getId();
        }
    }
}
