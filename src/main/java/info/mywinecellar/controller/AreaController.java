/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Grape;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.ui.AreaUI;
import info.mywinecellar.ui.ProducerUI;

import java.security.Principal;

import javax.validation.Valid;

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
@RequestMapping("/area")
public class AreaController extends AbstractController {

    /**
     * Default constructor
     */
    public AreaController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder("area")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param areaId    areaId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{areaId}/edit")
    public String areaEditGet(@PathVariable Long areaId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.AREA, areaConverter.toUI(areaService.findById(areaId)));
        return Paths.AREA_EDIT;
    }

    /**
     * @param areaUI      area
     * @param principal principal
     * @param areaId    areaId
     * @param action    action
     * @return View
     */
    @PostMapping("/{areaId}/edit")
    public String areaEditPost(AreaUI areaUI, Principal principal,
                               @PathVariable Long areaId,
                               @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("save")) {
            Area area = areaService.editArea(areaUI, areaId);
            return redirectArea(Session.getCountryId(), Session.getRegionId(), area);
        } else {
            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
    }

    /**
     * @param model     model
     * @param areaId    areaId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{areaId}/addProducer")
    public String areaAddProducerGet(Model model, @PathVariable Long areaId, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.AREA, areaConverter.toUI(areaService.findById(areaId)));
        model.addAttribute(Attributes.PRODUCER, new ProducerUI());
        return Paths.PRODUCER_ADD_EDIT;
    }

    /**
     * @param producer  producer
     * @param result    result
     * @param model     model
     * @param areaId    areaId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{areaId}/addProducer")
    public String areaAddProducerPost(@Valid ProducerUI producer, BindingResult result, Model model,
                                      @PathVariable Long areaId, Principal principal,
                                      @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("cancel")) {
            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
        if (result.hasErrors()) {
            return Paths.PRODUCER_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                Area area = areaService.areaAddProducer(producer, areaId);
                return redirectArea(Session.getCountryId(), Session.getRegionId(), area);
            }
        }
        return Paths.ERROR_PAGE;
    }

    /**
     * @param model     model
     * @param areaId    areaId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{areaId}/addGrape")
    public String areaAddGrapeGet(Model model, @PathVariable Long areaId, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.AREA, areaConverter.toUI(areaService.findById(areaId)));
        model.addAttribute(Attributes.RED_GRAPES, grapeConverter.toUIs(grapeService.getRedGrapes()));
        model.addAttribute(Attributes.WHITE_GRAPES, grapeConverter.toUIs(grapeService.getWhiteGrapes()));
        return Paths.AREA_ADD_GRAPE;
    }

    /**
     * @param area      area
     * @param principal principal
     * @param areaId    areaId
     * @param action    action
     * @return View
     */
    @PostMapping("/{areaId}/addGrape")
    public String areaAddGrapePost(AreaUI area, Principal principal,
                                   @PathVariable Long areaId,
                                   @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("save")) {
            Area a = areaService.findById(areaId);

            for (Grape g : a.getPrimaryGrapes()) {
                if (!area.getPrimaryGrapes().contains(g.getId())) {
                    g.getAreas().remove(a);
                }
            }

            for (Long grape : area.getPrimaryGrapes()) {
                Grape g = grapeService.findById(grape);
                if (!a.getPrimaryGrapes().contains(g)) {
                    g.getAreas().add(a);
                }
            }

            areaService.save(a);
            return redirectArea(Session.getCountryId(), Session.getRegionId(), a);
        } else {
            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
    }
}
