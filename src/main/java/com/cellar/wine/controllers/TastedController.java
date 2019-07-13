package com.cellar.wine.controllers;

import com.cellar.wine.models.Tasted;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.TastedService;
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
@RequestMapping("/producer/{producerId}/wine/{wineId}/tasted")
public class TastedController {

    private TastedService tastedService;
    private UserService userService;
    private WineService wineService;
    private ProducerService producerService;

    public TastedController(TastedService tastedService, UserService userService, WineService wineService, ProducerService producerService) {
        this.tastedService = tastedService;
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
    public String initAddTastedForm(Wine wine, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Tasted tasted = new Tasted(user, wine);
        model.addAttribute("tasted", tasted);
        return "tasted/addEditTasted";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Tasted tasted, BindingResult result, Wine wine, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        tasted.setWine(wine);
        user.getTasted().add(tasted);
        if (result.hasErrors()) {
            return "tasted/addEditTasted";
        } else {
            tasted.setUser(user);
            tastedService.save(tasted);
            return "redirect:/tasted/list";
        }
    }

    @GetMapping("/{tastedId}/edit")
    public String initEditTastedForm(@PathVariable Long tastedId, Model model) {
        model.addAttribute(tastedService.findById(tastedId));
        return "tasted/addEditTasted";
    }

    @PostMapping("/{tastedId}/edit")
    public String processEditTastedForm(@Valid Tasted tasted, BindingResult result, Wine wine, Model model, Principal principal) {
        tasted.setWine(wine);
        if (result.hasErrors()) {
            model.addAttribute("tasted", tasted);
            return "tasted/addEditTasted";
        } else {
            User user = userService.findByUsername(principal.getName());
            tasted.setUser(user);
            Tasted savedTasted = tastedService.save(tasted);
            user.getTasted().add(savedTasted);
            return "redirect:/tasted/list";
        }
    }
}
