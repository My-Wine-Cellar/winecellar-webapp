package com.cellar.wine.controllers;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/producer/{producerId}/wine/{wineId}/bottle")
public class BottleController {

    private BottleService bottleService;
    private UserService userService;
    private WineService wineService;
    private ProducerService producerService;

    public BottleController(BottleService bottleService, UserService userService, WineService wineService, ProducerService producerService) {
        this.bottleService = bottleService;
        this.userService = userService;
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @ModelAttribute("wine")
    public Wine findWine(@PathVariable Long wineId) {
        return wineService.findById(wineId);
    }

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable Long producerId) {
        return producerService.findById(producerId);
    }

    @InitBinder("wine")
    public void initWineBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initAddBottleForm(Wine wine, Model model) {
        Bottle bottle = new Bottle();
        wine.getBottles().add(bottle);
        bottle.setWine(wine);
        model.addAttribute("bottle", bottle);
        return "bottle/addEditBottle";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Bottle bottle, BindingResult result, Wine wine, Principal principal) {
        bottle.setWine(wine);
        wine.getBottles().add(bottle);
        if (result.hasErrors()) {
            return "bottle/addEditBottle";
        } else {
            User user = userService.findByUsername(principal.getName());
            bottle.setShow(true);
            bottle.setUser(user);
            bottleService.save(bottle);
            return "redirect:/bottle/list";
        }
    }

    @GetMapping("/{bottleId}/edit")
    public String initEditBottleForm(@PathVariable Long bottleId, Model model) {
        model.addAttribute(bottleService.findById(bottleId));
        return "bottle/addEditBottle";
    }

    @PostMapping("/{bottleId}/edit")
    public String processEditBottleForm(@Valid Bottle bottle, BindingResult result, Wine wine, Model model, Principal principal) {
        bottle.setWine(wine);
        if (result.hasErrors()) {
            model.addAttribute("bottle", bottle);
            return "bottle/addEditBottle";
        } else {
            User user = userService.findByUsername(principal.getName());
            bottle.setUser(user);
            Bottle savedBottle = bottleService.save(bottle);
            wine.getBottles().add(savedBottle);
            return "redirect:/bottle/list";
        }
    }
}
