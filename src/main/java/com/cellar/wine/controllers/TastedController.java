package com.cellar.wine.controllers;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.TastedService;
import com.cellar.wine.ui.TastedUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Controller
@RequestMapping("/tasted")
public class TastedController {

    @Inject
    private TastedService tastedService;

    @Inject
    private ReviewService reviewService;

    @Inject
    private UserService userService;

    public TastedController() {
    }

    @GetMapping("/{tastedId}/delete")
    public String tastedDeleteGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Tasted tasted = tastedService.findByUser(user.getId(), tastedId);

        if (tasted != null) {
            tastedService.delete(tasted);
        }

        model.addAttribute(Attributes.TASTED, TastedUIFactory.instance().createList(reviewService, user, user.getTasted()));
        return Paths.TASTED_LIST;
    }

    @GetMapping("/list")
    public String tastedList(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.TASTED, TastedUIFactory.instance().createList(reviewService, user, user.getTasted()));
        return Paths.TASTED_LIST;
    }
}
