package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Producer;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.nav.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/producer")
public class ProducerController extends AbstractController {

    public ProducerController() {
        super();
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{producerId}/edit")
    public String producerEditGet(@PathVariable Long producerId, Model model, Principal principal) {
        principalNull(principal);

        Producer producer = producerService.findById(producerId);
        Area area = areaService.findById(Session.getAreaId());

        model.addAttribute(Attributes.AREA, area);
        model.addAttribute(Attributes.PRODUCER, producer);

        return Paths.PRODUCER_ADD_EDIT;
    }

    @PostMapping("/{producerId}/edit")
    public String producerEditPost(@Valid Producer producer, BindingResult result, Principal principal,
                                   @PathVariable Long producerId,
                                   @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("cancel")) {
            return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
        }
        if (result.hasErrors()) {
            return Paths.PRODUCER_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                producer.setId(producerId);
                producerService.save(producer);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
            }
        }
        return Paths.ERROR_PAGE;
    }
}