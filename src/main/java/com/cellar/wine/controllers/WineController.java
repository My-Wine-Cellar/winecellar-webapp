package com.cellar.wine.controllers;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.WineGrapeUI;
import com.cellar.wine.ui.WineGrapeUISorter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/producer/{producerId}/wine")
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;
    private final UserService userService;

    public WineController(WineService wineService, ProducerService producerService, UserService userService) {
        this.wineService = wineService;
        this.producerService = producerService;
        this.userService = userService;
    }

    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String MODEL_ATTRIBUTE_WINE_BOTTLE = "bottle";
    private static final String MODEL_ATTRIBUTE_WINE_WINEGRAPES = "winegrapes";
    private static final String ADD_OR_EDIT_WINE_TEMPLATE = "wine/addEditWine";

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable Long producerId) {
        return producerService.findById(producerId);
    }

    @InitBinder("producer")
    public void initProducerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "description", "name");
    }

    @GetMapping("/{wineId}")
    public String wineDetails(@PathVariable Long wineId, Model model, Principal principal) {
        Wine wine = wineService.findById(wineId);
        User user = userService.findByUsername(principal.getName());
        Bottle bottle = null;

        for (Bottle b : user.getBottles())
        {
            if (b.getWine().getId().equals(wine.getId()))
                bottle = b;
        }

        List<WineGrapeUI> winegrapes = new ArrayList<>();
        for (GrapeComponent gc : wine.getGrapes()) {
            winegrapes.add(new WineGrapeUI(gc.getPercentage(), gc.getGrape().getName(), gc.getGrape().getId()));
        }
        Collections.sort(winegrapes, new WineGrapeUISorter());

        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_BOTTLE, bottle);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_WINEGRAPES, winegrapes);
        return "wine/wineDetails";
    }

    @GetMapping("/new")
    public String initNewWineForm(Producer producer, Model model) {
        Wine wine = new Wine();
        producer.getWines().add(wine);
        wine.setProducer(producer);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/new")
    public String processNewWineForm(Producer producer, @Valid Wine wine, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(wine.getName()) && wine.isNew() && producer.getWine(wine.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "This wine already exists");
        }
        wine.setProducer(producer);
        producer.getWines().add(wine);
        if (result.hasErrors()) {
            model.put(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            wineService.save(wine);
            return "redirect:/producer/" + producer.getId();
        }
    }

    @GetMapping("/{wineId}/edit")
    public String initEditWineForm(@PathVariable Long wineId, Model model) {
        model.addAttribute(wineService.findById(wineId));
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/{wineId}/edit")
    public String processEditWineForm(@Valid Wine wine, BindingResult result, Producer producer, Model model, @PathVariable Long wineId) {
        wine.setProducer(producer);
        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Wine savedWine = wineService.save(wine);
            producer.getWines().add(savedWine);
            return "redirect:/producer/" + producer.getId() + "/wine/" + wineId;
        }
    }

}
