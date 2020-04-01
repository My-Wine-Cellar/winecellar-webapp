/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.model.Grape;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;

import java.security.Principal;

import javax.validation.Valid;

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
@RequestMapping("/grape")
public class GrapeController extends AbstractController {

    /**
     * Default constructor
     */
    public GrapeController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param model model
     * @return View
     */
    @GetMapping("/list")
    public String grapeListGet(Model model) {
        model.addAttribute(Attributes.RED_GRAPES, grapeConverter
                .toDto(grapeService.getRedGrapes()));
        model.addAttribute(Attributes.WHITE_GRAPES, grapeConverter
                .toDto(grapeService.getWhiteGrapes()));
        return Paths.GRAPE_LIST;
    }

    /**
     * @param grape grape
     * @param model model
     * @return View
     */
    @GetMapping("/{grape}")
    public String grapeDetails(@PathVariable String grape, Model model) {
        Grape entity = grapeService.findByLowerCaseName(AbstractKeyDto.fromKey(grape));

        if (entity == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.GRAPE, grapeConverter.toDto(entity));
        return Paths.GRAPE_DETAILS;
    }

    /**
     * @param grapeId   grapeId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{grapeId}/edit")
    public String grapeEditGet(@PathVariable Long grapeId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.GRAPE, grapeConverter.toDto(grapeService.findById(grapeId)));
        return Paths.GRAPE_EDIT;
    }

    /**
     * @param grapeDto  grape
     * @param result    result
     * @param grapeId   grapeId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{grapeId}/edit")
    public String grapeEditPost(@ModelAttribute(Attributes.GRAPE) @Valid GrapeDto grapeDto, BindingResult result,
                                @PathVariable Long grapeId, Principal principal,
                                @RequestParam("action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            return Paths.GRAPE_EDIT;
        } else {
            Grape entity = grapeService.findById(grapeId);
            entity = grapeConverter.toEntity(entity, grapeDto);
            if (action.equals("save")) {
                grapeService.save(entity);
                GrapeDto save = grapeConverter.toDto(entity);
                return Paths.REDIRECT_GRAPE + save.getKey();
            } else {
                GrapeDto cancel = grapeConverter.toDto(entity);
                return Paths.REDIRECT_GRAPE + cancel.getKey();
            }
        }
    }

}
