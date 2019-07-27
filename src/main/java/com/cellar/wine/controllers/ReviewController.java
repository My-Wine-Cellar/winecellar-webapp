package com.cellar.wine.controllers;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ReviewService;
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
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private WineService wineService;

    private static final String MODEL_ATTRIBUTE_REVIEW = "review";
    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String MODEL_ATTRIBUTE_USER = "user";

    public ReviewController(ReviewService reviewService, UserService userService, WineService wineService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.wineService = wineService;
    }

    @InitBinder("review")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String reviewNewGet(@RequestParam Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByWine(user.getId(), wineId);

        if (review == null) {
            Wine wine = wineService.findById(wineId);
            review = new Review();
            review.setWine(wine);
        }

        model.addAttribute(MODEL_ATTRIBUTE_REVIEW, review);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, review.getWine());

        return "review/addEditReview";
    }

    @PostMapping("/new")
    public String reviewNewPost(@Valid Review review, BindingResult result, Model model,
                                @RequestParam Long wineId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "review/addEditReview";
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

            model.addAttribute(MODEL_ATTRIBUTE_USER, user);
            return "redirect:/review/list";
        }
    }

    @GetMapping("/{reviewId}")
    public String reviewDetailsGet(@PathVariable Long reviewId, Model model, Principal principal) {
        Review review = reviewService.findById(reviewId);

        if (review == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());

        model.addAttribute(MODEL_ATTRIBUTE_REVIEW, review);
        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, review.getWine());

        return "review/reviewView";
    }

    @GetMapping("/{reviewId}/edit")
    public String reviewEditGet(@PathVariable Long reviewId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByUser(user.getId(), reviewId);
        
        if (review == null) {
            return "redirect:/";
        }
                
        model.addAttribute(MODEL_ATTRIBUTE_REVIEW, review);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, review.getWine());

        return "review/addEditReview";
    }

    @PostMapping("/{reviewId}/edit")
    public String reviewEditPost(@Valid Review review, BindingResult result,
                                 @PathVariable Long reviewId, @RequestParam Long wineId,
                                 Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_REVIEW, review);
            return "review/addEditReview";
        } else {
            User user = userService.findByUsername(principal.getName());
            Review r = reviewService.findByUser(user.getId(), reviewId);

            if (r != null) {
                r.setStars(review.getStars());
                r.setComment(review.getComment());
                r.setDate(new Date(System.currentTimeMillis()));
                reviewService.save(r);

                model.addAttribute(MODEL_ATTRIBUTE_USER, user);
                return "redirect:/review/list";
            }

            return "redirect:/";
        }
    }

    @GetMapping("/{reviewId}/delete")
    public String reviewDeleteGet(@PathVariable Long reviewId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        Review review = reviewService.findByUser(user.getId(), reviewId);

        if (review != null) {
            reviewService.delete(review);

            model.addAttribute(MODEL_ATTRIBUTE_USER, user);
            return "review/reviewList";
        }

        return "redirect:/";
    }

    @GetMapping("/list")
    public String reviewListGet(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(MODEL_ATTRIBUTE_USER, user);
        return "review/reviewList";
    }
}
