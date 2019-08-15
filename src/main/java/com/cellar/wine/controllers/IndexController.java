package com.cellar.wine.controllers;

import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import javax.inject.Inject;

@Controller
public class IndexController {

    @Inject
    private UserService userService;

    public IndexController() {
    }

    @RequestMapping("/")
    public String landingPage() {
        return Paths.LANDING_PAGE;
    }

    @RequestMapping("/welcome")
    public String index(Model model, Principal principal) {
        if (model != null && principal != null)
            model.addAttribute(Attributes.USER, userService.findByUsername(principal.getName()));

        return Paths.WELCOME_PAGE;
    }

    @RequestMapping("/login")
    public String login() {
        return Paths.SECURITY_LOGIN;
    }

    @RequestMapping("/errorpage")
    public String error() {
        return Paths.ERROR_PAGE;
    }
}
