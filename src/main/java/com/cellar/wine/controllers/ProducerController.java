package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Producer;
import com.cellar.wine.nav.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/producer")
public class ProducerController extends AbstractController {

    private static final String MODEL_ATTRIBUTE_AREA = "area";
    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";

    private static final String ADD_OR_EDIT_PRODUCER_TEMPLATE = "producer/addEditProducer";

    public ProducerController() {
        super();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{producerId}/edit")
    public String producerEditGet(@PathVariable Long producerId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        Producer producer = producerService.findById(producerId);
        Area area = areaService.findById(Session.getAreaId());

        model.addAttribute(MODEL_ATTRIBUTE_AREA, area);
        model.addAttribute(MODEL_ATTRIBUTE_PRODUCER, producer);

        return ADD_OR_EDIT_PRODUCER_TEMPLATE;
    }

    @PostMapping("/{producerId}/edit")
    public String producerEditPost(@Valid Producer producer, BindingResult result,
                                   @PathVariable Long producerId, @RequestParam Long areaId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return ADD_OR_EDIT_PRODUCER_TEMPLATE;
        } else {
            producer.setId(producerId);
            producerService.save(producer);

            return redirectProducer(Session.getCountryId(), Session.getRegionId(), areaId, producerId);
        }
    }
}
