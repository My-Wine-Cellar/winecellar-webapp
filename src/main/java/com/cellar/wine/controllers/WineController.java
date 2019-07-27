package com.cellar.wine.controllers;

import com.cellar.wine.models.BarrelComponent;
import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Review;
import com.cellar.wine.models.Wine;
import com.cellar.wine.models.Wishlist;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.ui.AgingUI;
import com.cellar.wine.ui.BarrelUI;
import com.cellar.wine.ui.BarrelUISorter;
import com.cellar.wine.ui.GrapeUI;
import com.cellar.wine.ui.GrapeUISorter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/producer/{producerId}/wine")
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;
    private final UserService userService;
    private final BottleService bottleService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;

    public WineController(WineService wineService, ProducerService producerService,
                          UserService userService, BottleService bottleService,
                          ReviewService reviewService, WishlistService wishlistService) {
        this.wineService = wineService;
        this.producerService = producerService;
        this.userService = userService;
        this.bottleService = bottleService;
        this.reviewService = reviewService;
        this.wishlistService = wishlistService;
    }

    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String MODEL_ATTRIBUTE_WINE_WINEGRAPES = "winegrapes";
    private static final String MODEL_ATTRIBUTE_WINE_BOTTLE = "mybottle";
    private static final String MODEL_ATTRIBUTE_WINE_REVIEW = "myreview";
    private static final String MODEL_ATTRIBUTE_WINE_TASTINGNOTES = "mytastingnotes";
    private static final String MODEL_ATTRIBUTE_WINE_WISHLIST = "mywishlist";
    private static final String ADD_OR_EDIT_WINE_TEMPLATE = "wine/addEditWine";

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable Long producerId) {
        return producerService.findById(producerId);
    }

    @InitBinder("producer")
    public void initProducerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "description", "name");
    }

    @GetMapping("/{wineId}")
    public String wineDetailsGet(@PathVariable Long wineId, Model model, Principal principal) {
        Wine wine = wineService.findById(wineId);
        User user = null;
        Bottle bottle = null;
        Review review = null;
        GenericTastingNotes tastingnotes = null;
        Wishlist wishlist = null;

        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            bottle = bottleService.findByWine(user.getId(), wine.getId());
            review = reviewService.findByWine(user.getId(), wine.getId());
            tastingnotes = null;
            wishlist = wishlistService.findByWine(user.getId(), wine.getId());
        }

        List<GrapeUI> winegrapes = new ArrayList<>();
        for (GrapeComponent gc : wine.getGrapes()) {
            List<BarrelUI> barrels = new ArrayList<>();

            if (gc.getBarrelComponents() != null) {
                for (BarrelComponent bc : gc.getBarrelComponents()) {
                    barrels.add(new BarrelUI(bc.getPercentage(),
                                             bc.getBarrel().getName(), bc.getBarrel().getId(),
                                             bc.getSize(), new AgingUI(bc.getAging())
                                             ));
                }
            }

            Collections.sort(barrels, new BarrelUISorter());

            winegrapes.add(new GrapeUI(gc.getPercentage(),
                                       gc.getGrape().getName(), gc.getGrape().getId(),
                                       gc.getHarvestBegin(), gc.getHarvestEnd(),
                                       gc.getMaceration() != null ? gc.getMaceration().getDays() : null,
                                       gc.getMaceration() != null ? gc.getMaceration().getTemperature() : null,
                                       gc.getFermentation() != null ? gc.getFermentation().getDays() : null,
                                       gc.getFermentation() != null ? gc.getFermentation().getTemperature() : null,
                                       barrels
                                       ));
        }
        Collections.sort(winegrapes, new GrapeUISorter());

        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_WINEGRAPES, winegrapes);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_BOTTLE, bottle);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_REVIEW, review);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_TASTINGNOTES, tastingnotes);
        model.addAttribute(MODEL_ATTRIBUTE_WINE_WISHLIST, wishlist);
        return "wine/wineDetails";
    }

    @GetMapping("/new")
    public String wineNewGet(Producer producer, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        Wine wine = new Wine();
        producer.getWines().add(wine);
        wine.setProducer(producer);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/new")
    public String wineNewPost(Producer producer, @Valid Wine wine, BindingResult result, ModelMap model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (StringUtils.hasLength(wine.getName()) && wine.isNew() && producer.getWine(wine.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "This wine already exists");
        }
        wine.setProducer(producer);
        producer.getWines().add(wine);
        if (result.hasErrors()) {
            model.put(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            wineService.save(wine);
            return "redirect:/producer/" + producer.getId();
        }
    }

    @GetMapping("/{wineId}/edit")
    public String wineEditGet(@PathVariable Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute(wineService.findById(wineId));
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/{wineId}/edit")
    public String wineEditPost(@Valid Wine wine, BindingResult result, Producer producer,
                               Model model, @PathVariable Long wineId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        wine.setProducer(producer);
        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Wine savedWine = wineService.save(wine);
            producer.getWines().add(savedWine);
            return "redirect:/producer/" + producer.getId() + "/wine/" + wineId;
        }
    }
}
