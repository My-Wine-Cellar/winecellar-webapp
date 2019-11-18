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
import info.mywinecellar.security.model.User;
import info.mywinecellar.security.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController extends AbstractController {

    @GetMapping
    public String userRegistrationGet(Model model) {
        model.addAttribute(Attributes.USER, new UserDto());
        return Paths.SECURITY_REGISTER;
    }

    @PostMapping
    public String userRegistrationPost(@ModelAttribute("user") @Valid UserDto user, BindingResult result) throws Exception {
        if (emailExists(user.getEmail())) {
            result.rejectValue("email", "error.email");
            return Paths.SECURITY_REGISTER;
        } else if (usernameExists(user.getUserName())) {
            result.rejectValue("userName", "error.username");
            return Paths.SECURITY_REGISTER;
        } else if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "error.password");
            result.rejectValue("matchingPassword", "error.password");
            return Paths.SECURITY_REGISTER;
        } else if (result.hasErrors()) {
            return Paths.SECURITY_REGISTER;
        }
        userService.registerNewUserAccount(user);
        return Paths.REDIRECT_REGISTER_SUCCESS;
    }

    @GetMapping("/success")
    public String userRegistrationSuccess() {
        return Paths.SECURITY_SUCCESS;
    }

    @GetMapping("/confirm")
    public String userRegistrationVerificationGet(@RequestParam("token") String token) {

        String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            return Paths.SECURITY_LOGIN;
        } else if (result.equals("invalidToken") || result.equals("expired")) {
            return Paths.SECURITY_BAD_TOKEN;
        }
        return Paths.ERROR_PAGE;
    }

    private boolean emailExists(String email) {
        User user = userService.findUserByEmail(email);
        return user != null;
    }

    private boolean usernameExists(String username) {
        User user = userService.findByUsername(username);
        return user != null;
    }

}
