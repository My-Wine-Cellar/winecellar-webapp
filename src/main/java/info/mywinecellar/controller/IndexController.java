/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.security.service.UserService;

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

    @RequestMapping("/register")
    public String register() {
        return Paths.SECURITY_REGISTER;
    }

    @RequestMapping("/errorpage")
    public String error() {
        return Paths.ERROR_PAGE;
    }
}
