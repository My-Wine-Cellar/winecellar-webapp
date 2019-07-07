package com.cellar.wine.controllers;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ReviewService;
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
@RequestMapping("/producer/{producerId}/wine/{wineId}/review")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private WineService wineService;
    private ProducerService producerService;

    public ReviewController(ReviewService reviewService, UserService userService, WineService wineService, ProducerService producerService) {
        this.reviewService = reviewService;
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
    public String initAddReviewForm(Wine wine, Model model) {
        Review review = new Review();
        wine.getReviews().add(review);
        review.setWine(wine);
        model.addAttribute("review", review);
        return "review/addEditReview";
    }

    @PostMapping("/new")
    public String processAddForm(@Valid Review review, BindingResult result, Wine wine, Principal principal) {
        review.setWine(wine);
        wine.getReviews().add(review);
        if (result.hasErrors()) {
            return "review/addEditReview";
        } else {
            User user = userService.findByUsername(principal.getName());
            review.setDate(new Date(System.currentTimeMillis()));
            review.setUser(user);
            reviewService.save(review);
            return "redirect:/review/list";
        }
    }

    @GetMapping("/{reviewId}/edit")
    public String initEditReviewForm(@PathVariable Long reviewId, Model model) {
        model.addAttribute(reviewService.findById(reviewId));
        return "review/addEditReview";
    }

    @PostMapping("/{reviewId}/edit")
    public String processEditReviewForm(@Valid Review review, BindingResult result, Wine wine, Model model, Principal principal) {
        review.setWine(wine);
        if (result.hasErrors()) {
            model.addAttribute("review", review);
            return "review/addEditReview";
        } else {
            User user = userService.findByUsername(principal.getName());
            review.setUser(user);
            Review savedReview = reviewService.save(review);
            wine.getReviews().add(savedReview);
            return "redirect:/review/list";
        }
    }
}
