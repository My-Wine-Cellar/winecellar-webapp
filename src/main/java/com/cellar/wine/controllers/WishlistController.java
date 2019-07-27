package com.cellar.wine.controllers;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistService wishlistService;
    private UserService userService;
    private WineService wineService;

    private static final String MODEL_ATTRIBUTE_USER = "user";

    public WishlistController(WishlistService wishlistService, UserService userService, WineService wineService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wineService = wineService;
    }

    @InitBinder("wishlist")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String wishlistNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Wishlist wishlist = wishlistService.findByWine(user.getId(), wineId);

        if (wishlist == null) {
            Wine wine = wineService.findById(wineId);
            wishlist = new Wishlist(new Date(System.currentTimeMillis()), user, wine);
            wishlistService.save(wishlist);
        } else {
            wishlist.setDate(new Date(System.currentTimeMillis()));
            wishlistService.save(wishlist);
        }

        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "redirect:/wishlist/list";
    }

    @GetMapping("/{wishlistId}/delete")
    public String wishlistDeleteGet(@PathVariable Long wishlistId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Wishlist wishlist = wishlistService.findByUser(user.getId(), wishlistId);

        if (wishlist != null) {
            wishlistService.delete(wishlist);
        }

        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "wishlist/wishlistList";
    }

    @GetMapping("/list")
    public String wishlistList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "wishlist/wishlistList";
    }
}
