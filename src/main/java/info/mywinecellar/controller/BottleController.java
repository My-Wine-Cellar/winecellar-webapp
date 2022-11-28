/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.BottleConverter;
import info.mywinecellar.dto.BottleDto;
import info.mywinecellar.dto.BottleDtoSorter;
import info.mywinecellar.model.Bottle;
import info.mywinecellar.model.Tasted;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.BottleService;
import info.mywinecellar.service.TastedService;
import info.mywinecellar.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import jakarta.inject.Inject;

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

@Controller
@RequestMapping("/bottle")
public class BottleController extends AbstractController {

    @Inject
    BottleService bottleService;

    @Inject
    TastedService tastedService;

    @Inject
    private UserService userService;

    /**
     * Default constructor
     */
    public BottleController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder("bottle")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
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

    /**
     * @param bottle    bottle
     * @param result    result
     * @param model     model
     * @param wineId    wineId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/new")
    public String bottleNewPost(Bottle bottle, BindingResult result, Model model,
                                @RequestParam Long wineId, Principal principal,
                                @RequestParam("action") String action) {
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

            if (action.equals("save")) {
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

                if (tasted != null) {
                    tastedService.delete(tasted);
                }
                return Paths.REDIRECT_BOTTLE_LIST;
            } else {
                return Paths.REDIRECT_WELCOME;
            }
        }
    }

    /**
     * @param bottleId  bottleId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{bottleId}/edit")
    public String bottleEditGet(@PathVariable Long bottleId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Bottle bottle = bottleService.findByUser(user.getId(), bottleId);

        if (bottle == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.BOTTLE, bottle);
        return Paths.BOTTLE_ADD_EDIT;
    }

    /**
     * @param bottle    bottle
     * @param result    result
     * @param model     model
     * @param bottleId  bottleId
     * @param wineId    wineId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{bottleId}/edit")
    public String bottleEditPost(Bottle bottle, BindingResult result, Model model,
                                 @PathVariable Long bottleId, @RequestParam Long wineId, Principal principal,
                                 @RequestParam("action") String action) {
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

                    if (action.equals("save")) {
                        bottleService.save(b);
                    }
                    return Paths.REDIRECT_BOTTLE_LIST;

                } else {
                    user.getBottles().remove(b);
                    bottleService.delete(b);

                    Wine wine = wineService.findById(wineId);
                    Tasted tasted = new Tasted(user, wine);
                    user.getTasted().add(tasted);
                    tastedService.save(tasted);

                    return Paths.REDIRECT_TASTED_LIST;
                }
            }
            return Paths.REDIRECT_ROOT;
        }
    }

    /**
     * @param bottle    bottle
     * @param result    result
     * @param model     model
     * @param bottleId  bottleId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{bottleId}/up")
    public String bottleUpPost(Bottle bottle, BindingResult result, Model model,
                               @PathVariable Long bottleId, Principal principal) {
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
               b.setNumber(b.getNumber() + 1);
               bottleService.save(b);

               return Paths.REDIRECT_BOTTLE_LIST;
            }
            return Paths.REDIRECT_ROOT;
        }
    }

    /**
     * @param bottle    bottle
     * @param result    result
     * @param model     model
     * @param bottleId  bottleId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{bottleId}/down")
    public String bottleDownPost(Bottle bottle, BindingResult result, Model model,
                                 @PathVariable Long bottleId, Principal principal) {
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
                if (b.getNumber() > 1) {
                    b.setNumber(b.getNumber() - 1);

                    bottleService.save(b);

                    return Paths.REDIRECT_BOTTLE_LIST;

                } else {
                    user.getBottles().remove(b);
                    bottleService.delete(b);

                    Wine wine = wineService.findById(b.getWine().getId());
                    Tasted tasted = new Tasted(user, wine);
                    user.getTasted().add(tasted);
                    tastedService.save(tasted);

                    return Paths.REDIRECT_TASTED_LIST;
                }
            }
            return Paths.REDIRECT_ROOT;
        }
    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/list")
    public String bottleListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        List<BottleDto> bottles = BottleConverter.toDto(user.getBottles());
        Collections.sort(bottles, new BottleDtoSorter());
        model.addAttribute(Attributes.BOTTLES, bottles);
        return Paths.BOTTLE_LIST;
    }
}
