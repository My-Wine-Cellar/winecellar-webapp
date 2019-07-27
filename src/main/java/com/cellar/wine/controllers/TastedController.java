package com.cellar.wine.controllers;

import com.cellar.wine.security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tasted")
public class TastedController {

    private UserService userService;

    public TastedController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String tastedList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "tasted/tastedList";
    }
}
