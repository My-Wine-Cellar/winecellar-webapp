/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.service.GrapeService;

import java.security.Principal;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController {

    @Inject
    GrapeService grapeService;

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

        model.addAttribute(Attributes.AREA, AreaConverter.toDto(areaService.findById(areaId)));
        return Paths.AREA_EDIT;
    }

    /**
     * @param areaDto   area
     * @param result    result
     * @param principal principal
     * @param areaId    areaId
     * @param action    action
     * @return View
     */
    @PostMapping("/{areaId}/edit")
    public String areaEditPost(@ModelAttribute(Attributes.AREA) @Valid AreaDto areaDto, BindingResult result,
                               Principal principal,
                               @PathVariable Long areaId,
                               @RequestParam("action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            return Paths.AREA_EDIT;
        } else {
            if (action.equals("save")) {
                Area edit = areaService.editArea(areaDto, areaId);
                return redirectArea(Session.getCountryId(), Session.getRegionId(), edit);
            } else {
                return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
            }
        }
    }

    /**
     * @param areaId    areaId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{areaId}/addProducer")
    public String areaAddProducerGet(@PathVariable Long areaId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.AREA, AreaConverter.toDto(areaService.findById(areaId)));
        model.addAttribute(Attributes.PRODUCER, new ProducerDto());
        return Paths.PRODUCER_ADD_EDIT;
    }

    /**
     * @param areaId      areaId
     * @param producerDto producer
     * @param result      result
     * @param principal   principal
     * @param action      action
     * @return View
     */
    @PostMapping("/{areaId}/addProducer")
    public String areaAddProducerPost(@PathVariable Long areaId,
                                      @ModelAttribute(Attributes.PRODUCER) @Valid ProducerDto producerDto,
                                      BindingResult result, Principal principal,
                                      @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("cancel")) {
            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
        if (result.hasErrors()) {
            return Paths.PRODUCER_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                Area area = areaService.addProducer(areaId, producerDto);
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

        model.addAttribute(Attributes.AREA, AreaConverter.toDto(areaService.findById(areaId)));
        model.addAttribute(Attributes.RED_GRAPES, GrapeConverter.toDto(grapeService.getRedGrapes()));
        model.addAttribute(Attributes.WHITE_GRAPES, GrapeConverter.toDto(grapeService.getWhiteGrapes()));
        return Paths.AREA_ADD_GRAPE;
    }

    /**
     * @param dto       area
     * @param principal principal
     * @param areaId    areaId
     * @param action    action
     * @return View
     */
    @PostMapping("/{areaId}/addGrape")
    public String areaAddGrapePost(AreaDto dto, Principal principal,
                                   @PathVariable Long areaId,
                                   @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("save")) {
            Area entity = areaService.findById(areaId);

            entity.getPrimaryGrapes().stream()
                    .filter(grape -> !dto.getPrimaryGrapes().contains(grape.getId()))
                    .forEach(grape -> grape.getAreas().remove(entity));

            dto.getPrimaryGrapes().stream()
                    .map(grape -> grapeService.findById(grape))
                    .filter(grape -> !entity.getPrimaryGrapes().contains(grape))
                    .forEach(grape -> grape.getAreas().add(entity));

            areaService.save(entity);
            return redirectArea(Session.getCountryId(), Session.getRegionId(), entity);
        } else {
            return redirectArea(Session.getCountryId(), Session.getRegionId(), areaId);
        }
    }
}
