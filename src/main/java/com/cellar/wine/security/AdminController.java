package com.cellar.wine.security;

import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Inject
    private UserService userService;

    public AdminController() {
    }

    @GetMapping("/registeruser")
    public String registerUser(Model model) {
        model.addAttribute(Attributes.USER, User.builder().build());
        return Paths.SECURITY_USER_REGISTRATION;
    }

    @PostMapping("/registeruser")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Paths.SECURITY_USER_REGISTRATION);
        } else {
            userService.createUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject(Attributes.USER, new User());
            modelAndView.setViewName(Paths.SECURITY_USER_REGISTRATION);
        }
        return modelAndView;
    }

    @GetMapping("/userlist")
    public String getAllUsers(Model model) {
        model.addAttribute(Attributes.USERS, userService.findAll());
        return Paths.SECURITY_ADMIN_CONSOLE;
    }

    @GetMapping("/userlist/{id}/delete")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return Paths.REDIRECT_ADMIN_USER_LIST;
    }
}
