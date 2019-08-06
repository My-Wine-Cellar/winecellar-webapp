package com.cellar.wine.controllers;

import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.models.Wine;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Session;
import com.cellar.wine.security.User;
import com.cellar.wine.services.TastingNotesService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.RegionUI;
import com.cellar.wine.ui.TastingNotesUI;
import com.cellar.wine.ui.UserUI;
import com.cellar.wine.ui.WineUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tastingnotes")
public class TastingNotesController extends AbstractController {

    private static final String REDIRECT = "redirect:/";

    @Inject
    private TastingNotesService tastingNotesService;

    @Inject
    private WineService wineService;

    public TastingNotesController() {
    }

    @GetMapping("/list")
    public String tastingNotesListGet(Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.NOTES, getTastingNotesUIs(user.getGenericTastingNotes()));
        return "tastingNotes/tastingNotesList";
    }

    @GetMapping("/new")
    public String tastingNotesNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }

        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(wineId);

        if (wine == null) {
            return REDIRECT;
        }

        TastingNotesUI ui = new TastingNotesUI();
        ui.setUser(new UserUI(user));
        ui.setWine(new WineUI(wine));
        ui.setProducer(getProducerUI(wine.getProducer()));
        ui.setArea(getAreaUI(Session.getAreaId()));
        ui.setRegion(getRegionUI(Session.getRegionId()));
        ui.setCountry(getCountryUI(Session.getCountryId()));

        model.addAttribute(Attributes.NOTE, ui);
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
        User user = null;
        
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        GenericTastingNotes gtn = tastingNotesService.findById(tastedId);
        if (gtn == null) {
            return REDIRECT;
        }

        if (!gtn.getShow()) {
           if (user == null) {
               return REDIRECT;
           }
           if (gtn.getUser().getId() != user.getId()) {
               return REDIRECT;
           }
        }

        model.addAttribute(Attributes.NOTE, getTastingNotesUI(gtn));
        model.addAttribute(Attributes.USER, new UserUI(user));
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
        model.addAttribute(Attributes.NOTE, tastingNotesUI);
        return "tastingNotes/addEditNotes";
    }

    @PostMapping("/{tastedId}/edit")
    public String tastingNotesEditPost(@Valid TastingNotesUI tastingNotesUI, BindingResult result, @PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return REDIRECT;
        }
        if (result.hasErrors()) {
            model.addAttribute(Attributes.NOTE, tastingNotesUI);
            return "tastingNotes/addEditNotes";
        }
        GenericTastingNotes gtn = prepForSave(principal, tastingNotesUI);
        gtn.setId(tastedId);
        tastingNotesService.save(gtn);
        return "redirect:/tastingnotes/list";
    }

    @GetMapping("/{tastedId}/delete")
    public String tastingNotesDeleteGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        GenericTastingNotes gtn = tastingNotesService.findByUser(user.getId(), tastedId);

        if (gtn != null) {
            tastingNotesService.delete(gtn);
        }

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
                tastingNotesUI.getShow(),
                date, user, wine);
    }

    private List<TastingNotesUI> getTastingNotesUIs(List<GenericTastingNotes> gtns) {
        List<TastingNotesUI> result = new ArrayList<>();
        if (gtns != null) {
            for (GenericTastingNotes g : gtns) {
                result.add(getTastingNotesUI(g));
            }
        }
        return result;
    }

    private TastingNotesUI getTastingNotesUI(GenericTastingNotes g) {
        return new TastingNotesUI(g);
    }
}
