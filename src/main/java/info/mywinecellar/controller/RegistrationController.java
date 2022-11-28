/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.UserRegisterDto;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController extends AbstractController {

    @Inject
    private UserService userService;

    /**
     * @param model model
     * @return View
     */
    @GetMapping
    public String userRegistrationGet(Model model) {
        model.addAttribute(Attributes.USER, new UserRegisterDto());
        return Paths.SECURITY_REGISTER;
    }

    /**
     * @param user   user
     * @param result result
     * @return View
     */
    @PostMapping
    public String userRegistrationPost(@ModelAttribute(Attributes.USER) @Valid UserRegisterDto user,
                                       BindingResult result) {
        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.email");
            return Paths.SECURITY_REGISTER;
        } else if (userService.usernameExists(user.getUserName())) {
            result.rejectValue("userName", "error.username");
            return Paths.SECURITY_REGISTER;
        } else if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "error.password");
            return Paths.SECURITY_REGISTER;
        } else if (result.hasErrors()) {
            return Paths.SECURITY_REGISTER;
        }
        userService.registerNewUserAccount(user);
        return Paths.REDIRECT_REGISTER_SUCCESS;
    }

    /**
     * @return View
     */
    @GetMapping("/success")
    public String userRegistrationSuccess() {
        return Paths.SECURITY_SUCCESS;
    }

}
