package com.cellar.wine.controllers;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.TastedService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/bottle")
public class BottleController {

    private BottleService bottleService;
    private UserService userService;
    private WineService wineService;
    private TastedService tastedService;

    public BottleController(BottleService bottleService, UserService userService,
                            WineService wineService, TastedService tastedService) {
        this.bottleService = bottleService;
        this.userService = userService;
        this.wineService = wineService;
        this.tastedService = tastedService;
    }

    @InitBinder("bottle")
    public void initWineBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initAddBottleForm(@RequestParam Long wineId, Model model) {
        Bottle bottle = new Bottle();
        Wine wine = wineService.findById(wineId);
        bottle.setWine(wine);
        model.addAttribute("bottle", bottle);
        return "bottle/addEditBottle";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Bottle bottle, BindingResult result,
                                 @RequestParam Long wineId, Principal principal) {
        if (result.hasErrors()) {
            return "bottle/addEditBottle";
        } else {
            User user = userService.findByUsername(principal.getName());
            Wine wine = wineService.findById(wineId);
            Tasted tasted = tastedService.findByUser(wine.getId(), user.getId());

            bottle.setWine(wine);
            bottle.setShow(true);
            bottle.setUser(user);
            wine.getBottles().add(bottle);
            bottleService.save(bottle);

            if (tasted != null)
                tastedService.delete(tasted);

            return "redirect:/bottle/list";
        }
    }

    @GetMapping("/{bottleId}/edit")
    public String initEditBottleForm(@PathVariable Long bottleId, Model model) {
        Bottle bottle = bottleService.findById(bottleId);
        model.addAttribute("bottle", bottle);
        return "bottle/addEditBottle";
    }

    @PostMapping("/{bottleId}/edit")
    public String processEditBottleForm(@Valid Bottle bottle, BindingResult result, Model model,
                                        @PathVariable Long bottleId, @RequestParam Long wineId, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("bottle", bottle);
            return "bottle/addEditBottle";
        } else {
            User user = userService.findByUsername(principal.getName());
            Wine wine = wineService.findById(wineId);
            bottle.setId(bottleId);
            bottle.setUser(user);
            bottle.setWine(wine);

            if (bottle.getNumber() > 0) {
                bottleService.save(bottle);
                return "redirect:/bottle/list";
            } else {
                user.getBottles().remove(bottle);
                bottleService.delete(bottle);
                Tasted tasted = new Tasted(user, wine);
                user.getTasted().add(tasted);
                Tasted savedTasted = tastedService.save(tasted);
                return "redirect:/tasted/list";
            }
        }
    }

    @GetMapping("/list")
    public String bottleList(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "bottle/bottleList";
    }
}
