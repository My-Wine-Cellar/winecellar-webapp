package com.cellar.wine.controllers;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/{wineId}/bottle")
public class BottleController {

    private BottleService bottleService;
    private UserService userService;
    private WineService wineService;

    public BottleController(BottleService bottleService, UserService userService, WineService wineService) {
        this.bottleService = bottleService;
        this.userService = userService;
        this.wineService = wineService;
    }

    @ModelAttribute("wine")
    public Wine findWine(@PathVariable Long wineId) {
        return wineService.findById(wineId);
    }

    @GetMapping("/list")
    public String showUsersBottles(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        //model.addAttribute("bottles", bottleService.findAll());
        return "bottle/bottleList";
    }

    @GetMapping("/new")
    public String initAddForm(Model model, Principal principal) {
        model.addAttribute("bottle", Bottle.builder().build());
        return "bottle/addEditBottle";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Bottle bottle, BindingResult result, Principal principal) {
        if(result.hasErrors()) {
            return "bottle/addEditBottle";
        } else {
            User user = userService.findByUsername(principal.getName());
            bottle.setShow(true);
            bottle.setUser(user);
            bottleService.save(bottle);
            return "redirect:/bottle/list";
        }
    }
}
