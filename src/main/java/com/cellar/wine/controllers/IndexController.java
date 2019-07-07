package com.cellar.wine.controllers;

import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private WineService wineService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/wine/list")
    public String showAllWines(Model model) {
        model.addAttribute("wines", wineService.findAll());
        return "wine/wineList";
    }

    @GetMapping("/bottle/list")
    public String showUsersBottles(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "bottle/bottleList";
    }

    @GetMapping("/review/list")
    public String showUsersReviews(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "review/reviewList";
    }

    @GetMapping("/wishlist/list")
    public String showUsersWishlist(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "wishlist/wishlistList";
    }

    @GetMapping("/tasted/list")
    public String showUsersTasted(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "tasted/tastedList";
    }
}
