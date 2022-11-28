/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.WishlistConverter;
import info.mywinecellar.dto.WishlistDto;
import info.mywinecellar.dto.WishlistDtoSorter;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.model.Wishlist;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.UserService;
import info.mywinecellar.service.WineService;
import info.mywinecellar.service.WishlistService;

import java.security.Principal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wishlist")
public class WishlistController extends AbstractController {

    @Inject
    WishlistService wishlistService;
    @Inject
    UserService userService;

    @Inject
    WineService wineService;



    /**
     * Default constructor
     */
    public WishlistController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder("wishlist")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
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

    /**
     * @param wishlistId wishlistId
     * @param model      model
     * @param principal  principal
     * @return View
     */
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

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/list")
    public String wishlistList(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        List<WishlistDto> list = WishlistConverter.toDto(user.getWishlist());
        Collections.sort(list, new WishlistDtoSorter());
        model.addAttribute(Attributes.WISHLIST, list);
        return Paths.WISHLIST_LIST;
    }
}
