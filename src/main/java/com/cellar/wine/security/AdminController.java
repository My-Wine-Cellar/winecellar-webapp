package com.cellar.wine.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private static final String USER_REGISTRATION_TEMPLATE = "security/registerUser";

    @GetMapping("/registeruser")
    public String registerUser(Model model) {
        model.addAttribute("user", User.builder().build());
        return USER_REGISTRATION_TEMPLATE;
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
            modelAndView.setViewName(USER_REGISTRATION_TEMPLATE);
        } else {
            userService.createUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName(USER_REGISTRATION_TEMPLATE);
        }
        return modelAndView;
    }

    @GetMapping("/userlist")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "security/adminConsole";
    }

    @GetMapping("/userlist/{id}/delete")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/userlist";
    }
}