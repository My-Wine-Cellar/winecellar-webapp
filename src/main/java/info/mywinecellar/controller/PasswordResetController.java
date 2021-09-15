/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.UserResetDto;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password-reset")
public class PasswordResetController extends AbstractController {

    @Inject
    private UserService userService;

    /**
     * @param model model
     * @return View
     */
    @GetMapping
    public String userPasswordResetGet(Model model) {
        model.addAttribute(Attributes.USER, new UserResetDto());
        return Paths.SECURITY_PASSWORD_RESET;
    }

    /**
     * @param user   userPwResetDto
     * @param result result
     * @return View
     */
    @PostMapping
    public String userPasswordResetPost(@ModelAttribute(Attributes.USER) @Valid UserResetDto user,
                                        BindingResult result) {

        if (!userService.usernameExists(user.getUserName())) {
            result.rejectValue("userName", "error.reset");
            return Paths.SECURITY_PASSWORD_RESET;
        } else if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "error.password");
            return Paths.SECURITY_PASSWORD_RESET;
        } else if (result.hasErrors()) {
            return Paths.SECURITY_PASSWORD_RESET;
        }
        userService.resetPassword(user);
        return Paths.REDIRECT_PASSWORD_RESET_SUCCESS;
    }

    /**
     * @return View
     */
    @GetMapping("/success")
    public String userRegistrationSuccess() {
        return Paths.SECURITY_SUCCESS;
    }
}
