package com.cellar.wine.controllers;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.TastedService;
import com.cellar.wine.ui.TastedUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasted")
public class TastedController {

    private TastedService tastedService;
    private ReviewService reviewService;
    private UserService userService;

    private static final String MODEL_ATTRIBUTE_TASTED = "tasted";

    public TastedController(TastedService tastedService, ReviewService reviewService, UserService userService) {
        this.tastedService = tastedService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/{tastedId}/delete")
    public String tastedDeleteGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Tasted tasted = tastedService.findByUser(user.getId(), tastedId);

        if (tasted != null) {
            tastedService.delete(tasted);
        }

        model.addAttribute(MODEL_ATTRIBUTE_TASTED, getUI(user));
        return "tasted/tastedList";
    }

    @GetMapping("/list")
    public String tastedList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(MODEL_ATTRIBUTE_TASTED, getUI(user));
        return "tasted/tastedList";
    }

    private List<TastedUI> getUI(User user) {
        List<TastedUI> tasted = new ArrayList<>();

        if (user.getTasted() != null) {
            for (Tasted t : user.getTasted()) {
                Review review = reviewService.findByWine(user.getId(), t.getWine().getId());
                TastedUI ui = new TastedUI(t.getId(),
                                           t.getWine().getProducer().getId(), t.getWine().getProducer().getName(),
                                           t.getWine().getId(), t.getWine().getName(),
                                           t.getWine().getVintage(), t.getWine().getSize(),
                                           (review != null ? review.getId() : null));
                tasted.add(ui);
            }
        }

        return tasted;
    }
}
