package com.cellar.wine.controllers;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.services.ProducerService;
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
@RequestMapping("/producer/{producerId}/wine/{wineId}/wishlist")
public class WishlistController {

    private WishlistService wishlistService;
    private UserService userService;
    private WineService wineService;
    private ProducerService producerService;

    public WishlistController(WishlistService wishlistService, UserService userService, WineService wineService, ProducerService producerService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @ModelAttribute("wine")
    public Wine findWine(@PathVariable Long wineId) {
        return wineService.findById(wineId);
    }

    @ModelAttribute("producer")
    public Producer findProducer(@PathVariable Long producerId) {
        return producerService.findById(producerId);
    }

    @InitBinder("wine")
    public void initWineBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initAddWishlistForm(Wine wine, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Wishlist wishlist = new Wishlist(new Date(System.currentTimeMillis()), user, wine);
        model.addAttribute("wishlist", wishlist);
        return "wishlist/addEditWishlist";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Wishlist wishlist, BindingResult result, Wine wine, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        wishlist.setWine(wine);
        user.getWishlist().add(wishlist);
        if (result.hasErrors()) {
            return "wishlist/addEditWishlist";
        } else {
            wishlist.setDate(new Date(System.currentTimeMillis()));
            wishlist.setUser(user);
            wishlistService.save(wishlist);
            return "redirect:/wishlist/list";
        }
    }

    @GetMapping("/{wishlistId}/edit")
    public String initEditWishlistForm(@PathVariable Long wishlistId, Model model) {
        model.addAttribute(wishlistService.findById(wishlistId));
        return "wishlist/addEditWishlist";
    }

    @PostMapping("/{wishlistId}/edit")
    public String processEditWishlistForm(@Valid Wishlist wishlist, BindingResult result, Wine wine, Model model, Principal principal) {
        wishlist.setWine(wine);
        if (result.hasErrors()) {
            model.addAttribute("wishlist", wishlist);
            return "wishlist/addEditWishlist";
        } else {
            User user = userService.findByUsername(principal.getName());
            wishlist.setUser(user);
            Wishlist savedWishlist = wishlistService.save(wishlist);
            user.getWishlist().add(savedWishlist);
            return "redirect:/wishlist/list";
        }
    }
}
