/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.TastingNotesConverter;
import info.mywinecellar.converter.UserConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.dto.GenericTastingNotesDto;
import info.mywinecellar.dto.GenericTastingNotesDtoSorter;
import info.mywinecellar.model.GenericTastingNotes;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.service.TastingNotesService;
import info.mywinecellar.service.UserService;

import java.security.Principal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import jakarta.validation.Valid;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tastingnotes")
public class TastingNotesController extends AbstractController {

    @Inject
    TastingNotesService tastingNotesService;

    @Inject
    private UserService userService;

    /**
     * Default constructor
     */
    public TastingNotesController() {
        super();
    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/list")
    public String tastingNotesListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        List<GenericTastingNotesDto> notes = TastingNotesConverter.toDto(user.getGenericTastingNotes());
        Collections.sort(notes, new GenericTastingNotesDtoSorter());
        model.addAttribute(Attributes.NOTES, notes);
        return Paths.TASTING_NOTES_LIST;
    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/new")
    public String tastingNotesNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(wineId);

        if (wine == null) {
            return Paths.REDIRECT_ROOT;
        }

        GenericTastingNotesDto dto = new GenericTastingNotesDto();
        dto.setUser(UserConverter.toDto(user));
        dto.setWine(WineConverter.toDto(wine));
        dto.setProducer(ProducerConverter.toDto(wine.getProducer()));
        dto.setArea(getAreaDto(Session.getAreaId()));
        dto.setRegion(getRegionDto(Session.getRegionId()));
        dto.setCountry(getCountryDto(Session.getCountryId()));

        model.addAttribute(Attributes.NOTE, dto);
        return Paths.TASTING_NOTES_ADD_EDIT;
    }

    /**
     * @param genericTastingNotesDto tastingNotesDto
     * @param model          model
     * @param result         result
     * @param principal      principal
     * @param action         action
     * @return View
     */
    @PostMapping("/new")
    public String tastingNotesNewPost(@Valid GenericTastingNotesDto genericTastingNotesDto, Model model,
                                      BindingResult result, Principal principal,
                                      @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.NOTE, genericTastingNotesDto);
            return Paths.TASTING_NOTES_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                GenericTastingNotes gtn = prepForSave(principal, genericTastingNotesDto);
                tastingNotesService.save(gtn);
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            } else {
                return Paths.REDIRECT_WELCOME;
            }

        }
    }

    /**
     * @param tastedId  tastedId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{tastedId}")
    public String tastingNotesDetailsGet(@PathVariable Long tastedId, Model model, Principal principal) {
        User user = null;

        if (principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        GenericTastingNotes gtn = tastingNotesService.findById(tastedId);
        if (gtn == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (!gtn.getShow()) {
            if (user == null) {
                return Paths.REDIRECT_ROOT;
            }
            if (gtn.getUser().getId() != user.getId()) {
                return Paths.REDIRECT_ROOT;
            }
        }

        model.addAttribute(Attributes.NOTE, TastingNotesConverter.toDto(gtn));
        model.addAttribute(Attributes.USER, UserConverter.toDto(user));
        return Paths.TASTING_NOTES_VIEW;
    }

    /**
     * @param tastedId  tastedId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{tastedId}/edit")
    public String tastingNotesEditGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }
        User user = userService.findByUsername(principal.getName());
        GenericTastingNotes gtn = tastingNotesService.findByUser(user.getId(), tastedId);
        if (gtn == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.NOTE, TastingNotesConverter.toDto(gtn));
        return Paths.TASTING_NOTES_ADD_EDIT;
    }

    /**
     * @param genericTastingNotesDto tastingNotesDto
     * @param result         result
     * @param tastedId       tastedId
     * @param model          model
     * @param principal      principal
     * @param action         action
     * @return View
     */
    @PostMapping("/{tastedId}/edit")
    public String tastingNotesEditPost(@Valid GenericTastingNotesDto genericTastingNotesDto, BindingResult result,
                                       @PathVariable Long tastedId, Model model, Principal principal,
                                       @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }
        if (result.hasErrors()) {
            model.addAttribute(Attributes.NOTE, genericTastingNotesDto);
            return Paths.TASTING_NOTES_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                GenericTastingNotes gtn = prepForSave(principal, genericTastingNotesDto);
                gtn.setId(tastedId);
                tastingNotesService.save(gtn);
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            } else {
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            }
        }
    }

    /**
     * @param tastedId  tastedId
     * @param principal principal
     * @return View
     */
    @GetMapping("/{tastedId}/delete")
    public String tastingNotesDeleteGet(@PathVariable Long tastedId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        GenericTastingNotes gtn = tastingNotesService.findByUser(user.getId(), tastedId);

        if (gtn != null) {
            tastingNotesService.delete(gtn);
        }

        return Paths.REDIRECT_TASTINGNOTES_LIST;
    }

    private GenericTastingNotes prepForSave(Principal principal, GenericTastingNotesDto genericTastingNotesDto) {
        Date date = new Date(System.currentTimeMillis());
        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(genericTastingNotesDto.getWineId());
        return new GenericTastingNotes(genericTastingNotesDto.mapSightNotes(),
                genericTastingNotesDto.mapNoseNotes(),
                genericTastingNotesDto.mapPalateNotes(),
                genericTastingNotesDto.mapConclusionNotes(),
                genericTastingNotesDto.getShow(),
                date, user, wine);
    }
}
