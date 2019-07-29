package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Producer;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.RegionUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private static final String MODEL_ATTRIBUTE_AREA = "area";
    private static final String MODEL_ATTRIBUTE_PRODUCER = "producer";

    private static final String ADD_OR_EDIT_PRODUCER_TEMPLATE = "producer/addEditProducer";

    private AreaService areaService;
    private ProducerService producerService;

    public ProducerController(ProducerService producerService, AreaService areaService) {
        this.producerService = producerService;
        this.areaService = areaService;
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
        Area area = producer.getAreas().get(0);

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

        if(result.hasErrors()) {
            return ADD_OR_EDIT_PRODUCER_TEMPLATE;
        } else {
            Area area = areaService.findById(areaId);

            producer.setId(producerId);

            Producer savedProducer = producerService.save(producer);

            ProducerUI pui = new ProducerUI(savedProducer);
            AreaUI aui = new AreaUI(area);
            RegionUI rui = new RegionUI(area.getRegions().get(0));
            CountryUI cui = new CountryUI(area.getRegions().get(0).getCountry());

            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey() + "/" + pui.getKey();
        }
    }
}
