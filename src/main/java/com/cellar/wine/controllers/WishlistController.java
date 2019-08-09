package com.cellar.wine.controllers;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.models.Wine;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.WishlistUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Inject
    private WishlistService wishlistService;

    @Inject
    private UserService userService;

    @Inject
    private WineService wineService;

    public WishlistController() {
    }

    @InitBinder("wishlist")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String wishlistNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
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

        return Paths.REDIRECT_WISHLIST_LIST;
    }

    @GetMapping("/{wishlistId}/delete")
    public String wishlistDeleteGet(@PathVariable Long wishlistId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Wishlist wishlist = wishlistService.findByUser(user.getId(), wishlistId);

        if (wishlist != null) {
            wishlistService.delete(wishlist);
        }

        return Paths.REDIRECT_WISHLIST_LIST;
    }

    @GetMapping("/list")
    public String wishlistList(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.WISHLIST, WishlistUIFactory.instance().createList(user.getWishlist()));
        return Paths.WISHLIST_LIST;
    }
}
