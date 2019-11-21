/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.Grape;
import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Actions;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.ui.WineUI;
import info.mywinecellar.ui.WineUIFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@SessionAttributes({"wine", "grapeComponent", "barrelComponent", "grapes", "barrels"})
@RequestMapping("/wine")
public class WineController extends AbstractController {

    @InitBinder("wine")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder
    public void allowEmptyDates(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/new")
    public String wineAddRequiredGet(Producer producer, Model model,
                                     SessionStatus status, Principal principal) {
        principalNull(principal);

        Session.clear(status);

        Wine wine = new Wine();
        wine.setProducer(producer);
        model.addAttribute(Attributes.WINE, wine);
        model.addAttribute(Attributes.COLOR, colorService.findAll());
        model.addAttribute(Attributes.CLOSURE, closureService.findAll());
        model.addAttribute(Attributes.SHAPE, shapeService.findAll());
        return Paths.WINE_ADD_EDIT_DETAILS;
    }

    @PostMapping(value = "/new")
    public String wineAddRequiredPost(@Valid Wine wine, BindingResult result,
                                      Principal principal, SessionStatus status,
                                      @RequestParam Long producerId,
                                      @RequestParam(value = "action") String action) {
        principalNull(principal);

        if (action.equals(Actions.CANCEL)) {
            Session.clear(status);
            return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                    Session.getAreaId(), Session.getProducerId());
        }
        if (result.hasErrors()) {
            return Paths.REDIRECT_WINE_NEW + "?id=" + producerId;
        } else {
            Producer producer = producerService.findById(producerId);
            wine.setProducer(producer);
            producer.getWines().add(wine);
            switch (action) {
                case Actions.SAVE_WINE:
                    Wine savedWine = wineService.save(wine);
                    WineUI wui = WineUIFactory.instance().create(savedWine);

                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
                case Actions.ADD_GRAPE:
                    return Paths.REDIRECT_WINE_GRAPE;
                default:
                    Session.clear(status);
                    return Paths.ERROR_PAGE;
            }
        }
    }

    @GetMapping("/grape")
    public String wineAddGrapeComponentsGet(Wine wine, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.WINE, wine);
        GrapeComponent grapeComponent = new GrapeComponent();
        model.addAttribute(Attributes.GRAPE_COMPONENT, grapeComponent);
        model.addAttribute(Attributes.WINEGRAPES, grapeService.findAll());
        return Paths.WINE_ADD_GRAPE;
    }

    @PostMapping("/grape")
    public String wineAddGrapeComponentsPost(SessionStatus status, Principal principal, Model model,
                                             @Valid GrapeComponent grapeComponent, BindingResult result,
                                             @RequestParam(value = "action") String action) {
        principalNull(principal);

        Wine wine = Session.getWine();

        if (result.hasErrors()) {
            model.addAttribute(Attributes.WINEGRAPES, grapeService.findAll());
            return Paths.WINE_ADD_GRAPE;
        }

        Session.setGrapeComponent(grapeComponent);
        List<GrapeComponent> grapes = Session.getGrapeComponents();
        List<BarrelComponent> barrels = Session.getBarrelComponents();

        grapeComponent.setWine(wine);

        switch (action) {
            case Actions.ADD_ANOTHER_GRAPE:
                return Paths.REDIRECT_WINE_GRAPE;
            case Actions.ADD_BARREL:
                return Paths.REDIRECT_WINE_GRAPE + "/" + grapeComponent.getGrape().getId() + "/barrel";
            case Actions.SAVE_WINE:
                Wine savedWine = wineService.save(wine);

                saveOrNullMaceration(grapes);
                saveOrNullFermentation(grapes);

                grapeComponentService.saveAll(grapes);

                if (barrels != null) {
                    barrelComponentService.saveAll(barrels);
                }

                WineUI wui = WineUIFactory.instance().create(savedWine);
                Session.clear(status);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                        Session.getAreaId(), Session.getProducerId()) +
                        "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
            default:
                Session.clear(status);
                return Paths.ERROR_PAGE;
        }
    }

    @GetMapping("/grape/{grapeId}/barrel")
    public String wineAddGrapeBarrelGet(@PathVariable Long grapeId, Wine wine,
                                        Principal principal, Model model) {
        principalNull(principal);

        model.addAttribute(Attributes.WINE, wine);
        Grape grape = grapeService.findById(grapeId);
        model.addAttribute(Attributes.GRAPE, grape);
        BarrelComponent barrelComponent = new BarrelComponent();
        model.addAttribute(Attributes.BARREL_COMPONENT, barrelComponent);
        model.addAttribute(Attributes.BARRELS, barrelService.findAll());
        return Paths.WINE_ADD_GRAPE_BARREL;
    }

    @PostMapping("/grape/{grapeId}/barrel")
    public String wineAddGrapeBarrelPost(Principal principal, SessionStatus status,
                                         @PathVariable Long grapeId, Model model,
                                         @Valid BarrelComponent barrelComponent, BindingResult result,
                                         @RequestParam(value = "action") String action) {
        principalNull(principal);

        Wine wine = Session.getWine();
        GrapeComponent grapeComponent = Session.getGrapeComponent();

        Session.setBarrelComponent(barrelComponent);
        List<BarrelComponent> barrels = Session.getBarrelComponents();
        List<GrapeComponent> grapes = Session.getGrapeComponents();

        barrelComponent.setGrapeComponent(grapeComponent);

        if (result.hasErrors()) {
            Grape grape = grapeService.findById(grapeId);
            model.addAttribute(Attributes.GRAPE, grape);
            return Paths.WINE_ADD_GRAPE_BARREL;
        } else {
            switch (action) {
                case Actions.ADD_ANOTHER_GRAPE:
                    return Paths.REDIRECT_WINE_GRAPE;
                case Actions.ADD_ANOTHER_BARREL:
                    return Paths.REDIRECT_WINE_GRAPE + "/" + grapeComponent.getGrape().getId() + "/barrel";
                case Actions.SAVE_WINE:
                    Wine savedWine = wineService.save(wine);

                    saveOrNullMaceration(grapes);
                    saveOrNullFermentation(grapes);

                    grapeComponentService.saveAll(grapes);
                    barrelComponentService.saveAll(barrels);

                    WineUI wui = WineUIFactory.instance().create(savedWine);

                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
                default:
                    Session.clear(status);
                    return Paths.ERROR_PAGE;
            }
        }

    }

    @GetMapping("/{wineId}/edit")
    public String wineEditGet(@PathVariable Long wineId, Model model, Principal principal) {
        principalNull(principal);
        model.addAttribute(Attributes.WINE, wineService.findById(wineId));
        model.addAttribute(Attributes.COLOR, colorService.findAll());
        model.addAttribute(Attributes.CLOSURE, closureService.findAll());
        model.addAttribute(Attributes.SHAPE, shapeService.findAll());
        return Paths.WINE_ADD_EDIT_DETAILS;
    }

    @PostMapping("/{wineId}/edit")
    public String wineEditPost(@Valid Wine wine, BindingResult result,
                               Model model, Principal principal, SessionStatus status,
                               @RequestParam(value = "action") String action,
                               @PathVariable Long wineId, @RequestParam Long producerId) {
        principalNull(principal);

        if (result.hasErrors()) {
            model.addAttribute(Attributes.WINE, wine);
            return Paths.WINE_ADD_EDIT_DETAILS;
        } else {
            Producer producer = producerService.findById(producerId);

            wine.setId(wineId);
            wine.setProducer(producer);

            switch (action) {
                case Actions.SAVE_WINE:
                    Wine savedWine = wineService.save(wine);
                    WineUI wui = WineUIFactory.instance().create(savedWine);

                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
                case Actions.ADD_GRAPE:
                    return Paths.REDIRECT_WINE_GRAPE;
                case Actions.CANCEL:
                    WineUI wineUI = WineUIFactory.instance().create(wine);
                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + wineUI.getKey() + "/" + wineUI.getVintage() + "/" + wineUI.getSize();
                default:
                    Session.clear(status);
                    return Paths.ERROR_PAGE;
            }
        }
    }

    private void saveOrNullMaceration(List<GrapeComponent> grapes) {
        grapes.forEach(grapeComponent -> {
            if (grapeComponent.getMaceration().getDays() != null) {
                macerationService.save(grapeComponent.getMaceration());
            } else {
                grapeComponent.setMaceration(null);
            }
        });
    }

    private void saveOrNullFermentation(List<GrapeComponent> grapes) {
        grapes.forEach(grapeComponent -> {
            if (grapeComponent.getFermentation().getDays() != null) {
                fermentationService.save(grapeComponent.getFermentation());
            } else {
                grapeComponent.setFermentation(null);
            }
        });
    }
}
