package com.cellar.wine.controllers;

import com.cellar.wine.nav.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    public AboutController() {
    }

    @RequestMapping("/about")
    public String about() {
        return Paths.ABOUT;
    }

    @RequestMapping("/about/credits")
    public String credits() {
        return Paths.ABOUT_CREDITS;
    }

    @RequestMapping("/about/people")
    public String people() {
        return Paths.ABOUT_PEOPLE;
    }

    @RequestMapping("/about/license")
    public String license() {
        return Paths.ABOUT_LICENSE;
    }

    @RequestMapping("/about/contactus")
    public String contactUs() {
        return Paths.ABOUT_CONTACT_US;
    }
}