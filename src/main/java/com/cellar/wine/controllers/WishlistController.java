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

    public WishlistController(WishlistService wishlistService, UserService userService, WineService wineService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wineService = wineService;
    }

    @InitBinder("wishlist")
    public void initWineBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initAddWishlistForm(@RequestParam Long wineId, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Wine wine = wineService.findById(wineId);
        Wishlist wishlist = new Wishlist(new Date(System.currentTimeMillis()), user, wine);
        wishlistService.save(wishlist);

        model.addAttribute("user", user);
        return "redirect:/wishlist/list";
    }

    @GetMapping("/{wishlistId}/delete")
    public String initEditWishlistForm(@PathVariable Long wishlistId, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Wishlist wishlist = wishlistService.findById(wishlistId);

        if (wishlist.getUser().getId() == user.getId()) {
            wishlistService.delete(wishlist);
        }

        model.addAttribute("user", user);
        return "wishlist/wishlistList";
    }

    @GetMapping("/list")
    public String wishlistList(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "wishlist/wishlistList";
    }
}
