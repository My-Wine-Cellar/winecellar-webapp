/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.GenericTastingNotes;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.security.model.User;
import info.mywinecellar.ui.ProducerUIFactory;
import info.mywinecellar.ui.TastingNotesUI;
import info.mywinecellar.ui.TastingNotesUIFactory;
import info.mywinecellar.ui.UserUIFactory;
import info.mywinecellar.ui.WineUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping("/tastingnotes")
public class TastingNotesController extends AbstractController {

    public TastingNotesController() {
        super();
    }

    @GetMapping("/list")
    public String tastingNotesListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.NOTES, TastingNotesUIFactory.instance().createList(user.getGenericTastingNotes()));
        return Paths.TASTING_NOTES_LIST;
    }

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

        TastingNotesUI ui = new TastingNotesUI();
        ui.setUser(UserUIFactory.instance().create(user));
        ui.setWine(WineUIFactory.instance().create(wine));
        ui.setProducer(ProducerUIFactory.instance().create(wine.getProducer()));
        ui.setArea(getAreaUI(Session.getAreaId()));
        ui.setRegion(getRegionUI(Session.getRegionId()));
        ui.setCountry(getCountryUI(Session.getCountryId()));

        model.addAttribute(Attributes.NOTE, ui);
        return Paths.TASTING_NOTES_ADD_EDIT;
    }

    @PostMapping("/new")
    public String tastingNotesNewPost(@Valid TastingNotesUI tastingNotesUI, Model model,
                                      BindingResult result, Principal principal,
                                      @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.NOTE, tastingNotesUI);
            return Paths.TASTING_NOTES_ADD_EDIT;
        } else {
            if(action.equals("save")) {
                GenericTastingNotes gtn = prepForSave(principal, tastingNotesUI);
                tastingNotesService.save(gtn);
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            } else {
                return Paths.REDIRECT_WELCOME;
            }

        }
    }

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

        model.addAttribute(Attributes.NOTE, TastingNotesUIFactory.instance().create(gtn));
        model.addAttribute(Attributes.USER, UserUIFactory.instance().create(user));
        return Paths.TASTING_NOTES_VIEW;
    }

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

        model.addAttribute(Attributes.NOTE, TastingNotesUIFactory.instance().create(gtn));
        return Paths.TASTING_NOTES_ADD_EDIT;
    }

    @PostMapping("/{tastedId}/edit")
    public String tastingNotesEditPost(@Valid TastingNotesUI tastingNotesUI, BindingResult result,
                                       @PathVariable Long tastedId, Model model, Principal principal,
                                       @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }
        if (result.hasErrors()) {
            model.addAttribute(Attributes.NOTE, tastingNotesUI);
            return Paths.TASTING_NOTES_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                GenericTastingNotes gtn = prepForSave(principal, tastingNotesUI);
                gtn.setId(tastedId);
                tastingNotesService.save(gtn);
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            } else {
                return Paths.REDIRECT_TASTINGNOTES_LIST;
            }
        }
    }

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

    private GenericTastingNotes prepForSave(Principal principal, TastingNotesUI tastingNotesUI) {
        Date date = new Date(System.currentTimeMillis());
        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(tastingNotesUI.getWineId());
        return new GenericTastingNotes(tastingNotesUI.mapSightNotes(),
                tastingNotesUI.mapNoseNotes(),
                tastingNotesUI.mapPalateNotes(),
                tastingNotesUI.mapConclusionNotes(),
                tastingNotesUI.getShow(),
                date, user, wine);
    }
}
