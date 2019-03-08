package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/producers")
@Controller
public class ProducerController {

    private static final String CREATE_OR_UPDATE_PRODUCER_TEMPLATE = "producers/createOrUpdateProducer";

    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";

    private final ProducerService producerService;
    private final UserService userService;

    public ProducerController(ProducerService producerService, UserService userService) {
        this.producerService = producerService;
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/list")
    public String producer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/producersList";
    }

    @GetMapping("/{producerId}")
    public ModelAndView showProducer(@PathVariable Long producerId) {
        ModelAndView mav = new ModelAndView("producers/producerDetails");
        mav.addObject(producerService.findById(producerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, Producer.builder().build());
        return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Producer producer, Principal principal, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
        } else {
            User user = userService.findByUsername(principal.getName());
            producer.setUser(user);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateProducerForm(@PathVariable Long producerId, Model model) {
        model.addAttribute(producerService.findById(producerId));
        return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
    }

    @PostMapping("/{producerId}/edit")
    public String processUpdateProducerForm(@Valid Producer producer, BindingResult result, @PathVariable Long producerId) {
        if(result.hasErrors()) {
            return CREATE_OR_UPDATE_PRODUCER_TEMPLATE;
        } else {
            producer.setId(producerId);
            Producer savedProducer = producerService.save(producer);
            return "redirect:/producers/" + savedProducer.getId();
        }
    }

    @RequestMapping("/find")
    public String findProducers(Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, Producer.builder().build());
        return "producers/findProducers";
    }

    @GetMapping
    public String processFindForm(Producer producer, BindingResult result, Model model) {

        Producer find = producerService.findByName(producer.getName());

        if(find == null) {
            result.rejectValue("name", "notFound", "Not found, try again, this searches exact names only");
            return "producers/findProducers";
        } else {
            model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, find);
            return "producers/producerDetails";
        }
    }
}
