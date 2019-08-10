package com.cellar.wine.controllers;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.models.Wine;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.TastedService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.BottleUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bottle")
public class BottleController {

    @Inject
    private BottleService bottleService;

    @Inject
    private UserService userService;

    @Inject
    private WineService wineService;

    @Inject
    private TastedService tastedService;

    public BottleController() {
    }

    @InitBinder("bottle")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String bottleNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        Bottle bottle = new Bottle();
        Wine wine = wineService.findById(wineId);
        bottle.setWine(wine);
        model.addAttribute(Attributes.BOTTLE, bottle);
        return Paths.BOTTLE_ADD_EDIT;
    }

    @PostMapping("/new")
    public String bottleNewPost(@Valid Bottle bottle, BindingResult result, Model model,
                                @RequestParam Long wineId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.BOTTLE, bottle);
            return Paths.BOTTLE_ADD_EDIT;
        } else {
            User user = userService.findByUsername(principal.getName());
            Bottle b = bottleService.findByWine(user.getId(), wineId);
            Tasted tasted = tastedService.findByWine(user.getId(), wineId);

            if (b == null) {
                Wine wine = wineService.findById(wineId);
                bottle.setWine(wine);
                bottle.setShow(true);
                bottle.setUser(user);
                user.getBottles().add(bottle);
                bottleService.save(bottle);
            } else {
                b.setNumber(bottle.getNumber());
                b.setLocation(bottle.getLocation());
                b.setShow(bottle.getShow());
                bottleService.save(b);
            }

            if (tasted != null)
                tastedService.delete(tasted);

            return Paths.REDIRECT_BOTTLE_LIST;
        }
    }

    @GetMapping("/{bottleId}/edit")
    public String bottleEditGet(@PathVariable Long bottleId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Bottle bottle = bottleService.findByUser(user.getId(), bottleId);

        if (bottle == null)
            return Paths.REDIRECT_ROOT;

        model.addAttribute(Attributes.BOTTLE, bottle);
        return Paths.BOTTLE_ADD_EDIT;
    }

    @PostMapping("/{bottleId}/edit")
    public String bottleEditPost(@Valid Bottle bottle, BindingResult result, Model model,
                                 @PathVariable Long bottleId, @RequestParam Long wineId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.BOTTLE, bottle);
            return Paths.BOTTLE_ADD_EDIT;
        } else {
            User user = userService.findByUsername(principal.getName());
            Bottle b = bottleService.findByUser(user.getId(), bottleId);

            if (b != null) {
                if (bottle.getNumber() > 0) {
                    b.setNumber(bottle.getNumber());
                    b.setLocation(bottle.getLocation());
                    b.setShow(bottle.getShow());

                    bottleService.save(b);

                    return Paths.REDIRECT_BOTTLE_LIST;
                } else {
                    user.getBottles().remove(b);
                    bottleService.delete(b);

                    Wine wine = wineService.findById(wineId);
                    Tasted tasted = new Tasted(user, wine);
                    user.getTasted().add(tasted);
                    Tasted savedTasted = tastedService.save(tasted);

                    return Paths.REDIRECT_TASTED_LIST;
                }
            }
            return Paths.REDIRECT_ROOT;
        }
    }

    @GetMapping("/list")
    public String bottleListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.BOTTLES, BottleUIFactory.instance().createList(user.getBottles()));
        return Paths.BOTTLE_LIST;
    }
}
