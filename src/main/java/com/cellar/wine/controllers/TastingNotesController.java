package com.cellar.wine.controllers;

import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.TastingNotesService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.TastingNotesUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping("/tastingnotes")
public class TastingNotesController {

    private TastingNotesService tastingNotesService;
    private UserService userService;
    private WineService wineService;

    public TastingNotesController(TastingNotesService tastingNotesService, UserService userService,
                                  WineService wineService) {
        this.tastingNotesService = tastingNotesService;
        this.userService = userService;
        this.wineService = wineService;
    }

    private static final String MODEL_ATTRIBUTE_GENERIC_TASTING_NOTES = "gtn";
    private static final String MODEL_ATTRIBUTE_TASTING_NOTES_UI = "tastingNotesUI";
    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String MODEL_ATTRIBUTE_USER = "user";
    private static final String REDIRECT = "redirect:/";

    @GetMapping("/list")
    public String tastingNotesListGet(Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        model.addAttribute(MODEL_ATTRIBUTE_USER, userService.findByUsername(principal.getName()));
        return "tastingNotes/tastingNotesList";
    }

    @GetMapping("/new")
    public String tastingNotesNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        TastingNotesUI tastingNotesUI = new TastingNotesUI();
        tastingNotesUI.setWineId(wineId);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wineService.findById(wineId));
        model.addAttribute(MODEL_ATTRIBUTE_TASTING_NOTES_UI, tastingNotesUI);
        return "tastingNotes/addEditNotes";
    }

    @PostMapping("/new")
    public String tastingNotesNewPost(@Valid TastingNotesUI tastingNotesUI,
                                      BindingResult result, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }

        if (result.hasErrors()) {
            return "tastingNotes/addEditNotes";
        } else {
            GenericTastingNotes gtn = prepForSave(principal, tastingNotesUI);
            tastingNotesService.save(gtn);
            return "redirect:/tastingnotes/list";
        }
    }

    @GetMapping("/{tastedId}")
    public String tastingNotesDetailsGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        User user = userService.findByUsername(principal.getName());
        GenericTastingNotes gtn = tastingNotesService.findByUser(user.getId(), tastedId);
        model.addAttribute(MODEL_ATTRIBUTE_GENERIC_TASTING_NOTES, gtn);
        TastingNotesUI tastingNotesUI = new TastingNotesUI(gtn);
        model.addAttribute(MODEL_ATTRIBUTE_TASTING_NOTES_UI, tastingNotesUI);
        return "tastingNotes/tastingNotesView";
    }

    @GetMapping("/{tastedId}/edit")
    public String tastingNotesEditGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        User user = userService.findByUsername(principal.getName());
        GenericTastingNotes gtn = tastingNotesService.findByUser(user.getId(), tastedId);
        if (gtn == null) {
            return REDIRECT;
        }
        TastingNotesUI tastingNotesUI = new TastingNotesUI(gtn);
        model.addAttribute(MODEL_ATTRIBUTE_TASTING_NOTES_UI, tastingNotesUI);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, gtn.getWine());
        return "tastingNotes/addEditNotes";
    }

    @PostMapping("/{tastedId}/edit")
    public String tastingNotesEditPost(@Valid TastingNotesUI tastingNotesUI, BindingResult result, @PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        if(result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_TASTING_NOTES_UI, tastingNotesUI);
            return "tastingNotes/addEditNotes";
        }
        GenericTastingNotes gtn = prepForSave(principal, tastingNotesUI);
        gtn.setId(tastedId);
        tastingNotesService.save(gtn);
        return "redirect:/tastingnotes/list";
    }

    private GenericTastingNotes prepForSave(Principal principal, TastingNotesUI tastingNotesUI) {
        Date date = new Date(System.currentTimeMillis());
        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(tastingNotesUI.getWineId());
        return new GenericTastingNotes(tastingNotesUI.mapSightNotes(),
                tastingNotesUI.mapNoseNotes(),
                tastingNotesUI.mapPalleteNotes(),
                tastingNotesUI.mapConclusionNotes(),
                tastingNotesUI.isShow(),
                date, user, wine);
    }
}