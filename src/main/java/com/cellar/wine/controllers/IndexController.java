package com.cellar.wine.controllers;

import com.cellar.wine.security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

    private UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model, Principal principal) {
        if (model != null && principal != null)
            model.addAttribute("user", userService.findByUsername(principal.getName()));

        return "index";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "security/login";
    }

    @RequestMapping("/errorpage")
    public String error() {
        return "error";
    }
}
