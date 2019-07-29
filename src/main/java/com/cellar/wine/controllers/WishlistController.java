package com.cellar.wine.controllers;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.WishlistUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistService wishlistService;
    private UserService userService;
    private WineService wineService;

    private static final String MODEL_ATTRIBUTE_USER = "user";
    private static final String MODEL_ATTRIBUTE_WISHLIST = "wishlist";

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

        return "redirect:/wishlist/list";
    }

    @GetMapping("/list")
    public String wishlistList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(MODEL_ATTRIBUTE_WISHLIST, getWishlistUIs(user.getWishlist()));
        return "wishlist/wishlistList";
    }

    private List<WishlistUI> getWishlistUIs(List<Wishlist> wishlist) {
        List<WishlistUI> result = new ArrayList<>();
        if (wishlist != null) {
            for (Wishlist w : wishlist) {
                result.add(getWishlistUI(w));
            }
        }
        return result;
    }

    private WishlistUI getWishlistUI(Wishlist w) {
        return new WishlistUI(w);
    }
}
