package com.cellar.wine.controllers;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Wine;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.ReviewUI;
import com.cellar.wine.ui.ReviewUIFactory;
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
@RequestMapping("/review")
public class ReviewController {

    @Inject
    private ReviewService reviewService;

    @Inject
    private UserService userService;

    @Inject
    private WineService wineService;

    public ReviewController() {
    }

    @InitBinder("review")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String reviewNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByWine(user.getId(), wineId);

        if (review == null) {
            Wine wine = wineService.findById(wineId);
            review = new Review();
            review.setWine(wine);
        }

        model.addAttribute(Attributes.REVIEW, review);
        model.addAttribute(Attributes.WINE, review.getWine());

        return Paths.REVIEW_ADD_EDIT;
    }

    @PostMapping("/new")
    public String reviewNewPost(@Valid Review review, BindingResult result, Model model,
                                @RequestParam Long wineId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.REVIEW, review);
            return Paths.REVIEW_ADD_EDIT;
        } else {
            User user = userService.findByUsername(principal.getName());
            Review r = reviewService.findByWine(user.getId(), wineId);

            if (r == null) {
                Wine wine = wineService.findById(wineId);

                review.setDate(new Date(System.currentTimeMillis()));
                review.setUser(user);
                review.setWine(wine);

                user.getReviews().add(review);

                reviewService.save(review);
            } else {
                r.setStars(review.getStars());
                r.setComment(review.getComment());
                r.setDate(new Date(System.currentTimeMillis()));
                reviewService.save(r);
            }

            return Paths.REDIRECT_REVIEW_LIST;
        }
    }

    @GetMapping("/{reviewId}")
    public String reviewDetailsGet(@PathVariable Long reviewId, Model model, Principal principal) {

        Review review = reviewService.findById(reviewId);
        if (review == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = null;
        if(principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        model.addAttribute(Attributes.REVIEW, ReviewUIFactory.instance().create(review));
        model.addAttribute(Attributes.USER, user);

        return Paths.REVIEW_VIEW;
    }

    @GetMapping("/{reviewId}/edit")
    public String reviewEditGet(@PathVariable Long reviewId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByUser(user.getId(), reviewId);
        
        if (review == null) {
            return Paths.REDIRECT_ROOT;
        }
                
        model.addAttribute(Attributes.REVIEW, review);
        model.addAttribute(Attributes.WINE, review.getWine());

        return Paths.REVIEW_ADD_EDIT;
    }

    @PostMapping("/{reviewId}/edit")
    public String reviewEditPost(@Valid Review review, BindingResult result,
                                 @PathVariable Long reviewId, @RequestParam Long wineId,
                                 Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.REVIEW, review);
            return Paths.REVIEW_ADD_EDIT;
        } else {
            User user = userService.findByUsername(principal.getName());
            Review r = reviewService.findByUser(user.getId(), reviewId);

            if (r != null) {
                r.setStars(review.getStars());
                r.setComment(review.getComment());
                r.setDate(new Date(System.currentTimeMillis()));
                reviewService.save(r);

                return Paths.REDIRECT_REVIEW_LIST;
            }

            return Paths.REDIRECT_ROOT;
        }
    }

    @GetMapping("/{reviewId}/delete")
    public String reviewDeleteGet(@PathVariable Long reviewId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByUser(user.getId(), reviewId);

        if (review != null) {
            reviewService.delete(review);

            return Paths.REDIRECT_REVIEW_LIST;
        }

        return Paths.REDIRECT_ROOT;
    }

    @GetMapping("/list")
    public String reviewListGet(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.REVIEWS, ReviewUIFactory.instance().createList(user.getReviews()));
        return Paths.REVIEW_LIST;
    }
}
