/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.controller;

import info.mywinecellar.controller.AbstractController;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.security.model.UserDto;

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

    /**
     * @param model model
     * @return View
     */
    @GetMapping
    public String userPasswordResetGet(Model model) {
        model.addAttribute(Attributes.USER, new UserDto());
        return Paths.SECURITY_PASSWORD_RESET;
    }

    /**
     * @param user   userDto
     * @param result result
     * @return View
     */
    @PostMapping
    public String userPasswordResetPost(@ModelAttribute("user") UserDto user, BindingResult result) {

        if (!userService.usernameExists(user.getUserName())) {
            result.rejectValue("userName", "error.reset");
            return Paths.SECURITY_PASSWORD_RESET;
        } else if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "error.password");
            result.rejectValue("matchingPassword", "error.password");
            return Paths.SECURITY_PASSWORD_RESET;
        }
        userService.update(user);
        return Paths.SECURITY_SUCCESS;
    }
}
