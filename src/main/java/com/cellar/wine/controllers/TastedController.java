package com.cellar.wine.controllers;

import com.cellar.wine.models.Tasted;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.TastedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tasted")
public class TastedController {

    private TastedService tastedService;
    private UserService userService;

    private static final String MODEL_ATTRIBUTE_USER = "user";

    public TastedController(TastedService tastedService, UserService userService) {
        this.tastedService = tastedService;
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

        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "tasted/tastedList";
    }

    @GetMapping("/list")
    public String tastedList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "tasted/tastedList";
    }
}
