package com.cellar.wine.controllers;

import com.cellar.wine.models.Wine;
import com.cellar.wine.models.Wishlist;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.model.User;
import com.cellar.wine.ui.WishlistUIFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping("/wishlist")
public class WishlistController extends AbstractController {

    public WishlistController() {
        super();
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
