/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.Review;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.security.model.User;
import info.mywinecellar.ui.ReviewUIFactory;

import java.security.Principal;
import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
public class ReviewController extends AbstractController {

    /**
     * Default constructor
     */
    public ReviewController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder("review")
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

    /**
     * @param review    review
     * @param result    result
     * @param model     model
     * @param wineId    wineId
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/new")
    public String reviewNewPost(Review review, BindingResult result, Model model,
                                @RequestParam Long wineId, Principal principal,
                                @RequestParam("action") String action) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.REVIEW, review);
            return Paths.REVIEW_ADD_EDIT;
        } else {
            User user = userService.findByUsername(principal.getName());
            Review r = reviewService.findByWine(user.getId(), wineId);

            if (action.equals("save")) {
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
            } else {
                return Paths.REDIRECT_WELCOME;
            }
        }
    }

    /**
     * @param reviewId  reviewId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{reviewId}")
    public String reviewDetailsGet(@PathVariable Long reviewId, Model model, Principal principal) {

        Review review = reviewService.findById(reviewId);
        if (review == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        model.addAttribute(Attributes.REVIEW, ReviewUIFactory.instance().create(review));
        model.addAttribute(Attributes.USER, user);

        return Paths.REVIEW_VIEW;
    }

    /**
     * @param reviewId  reviewId
     * @param model     model
     * @param principal principal
     * @return View
     */
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

    /**
     * @param review    review
     * @param result    result
     * @param reviewId  reviewId
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @param action    action
     * @return View
     */
    @PostMapping("/{reviewId}/edit")
    public String reviewEditPost(Review review, BindingResult result,
                                 @PathVariable Long reviewId, @RequestParam Long wineId,
                                 Model model, Principal principal,
                                 @RequestParam("action") String action) {
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

                if (action.equals("save")) {
                    reviewService.save(r);
                }
                return Paths.REDIRECT_REVIEW_LIST;
            }
            return Paths.REDIRECT_ROOT;
        }
    }

    /**
     * @param reviewId  reviewId
     * @param model     model
     * @param principal principal
     * @return View
     */
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

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
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
