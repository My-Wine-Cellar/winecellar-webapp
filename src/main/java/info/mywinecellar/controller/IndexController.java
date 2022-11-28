/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.UserService;

import java.security.Principal;

import jakarta.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Inject
    private UserService userService;

    /**
     * Default constructor
     */
    public IndexController() {
    }

    /**
     * @return View
     */
    @RequestMapping("/")
    public String landingPage() {
        return Paths.LANDING_PAGE;
    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @RequestMapping("/welcome")
    public String index(Model model, Principal principal) {
        if (model != null && principal != null) {
            model.addAttribute(Attributes.USER, userService.findByUsername(principal.getName()));
        }
        return Paths.WELCOME_PAGE;
    }

    /**
     * @return View
     */
    @RequestMapping("/login")
    public String login() {
        return Paths.SECURITY_LOGIN;
    }

    /**
     * @return View
     */
    @RequestMapping("/register")
    public String register() {
        return Paths.SECURITY_REGISTER;
    }

    /**
     * @return View
     */
    @RequestMapping("/errorpage")
    public String error() {
        return Paths.ERROR_PAGE;
    }
}
