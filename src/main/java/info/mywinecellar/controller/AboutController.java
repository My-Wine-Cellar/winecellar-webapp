/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.nav.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    /**
     * Default constructor
     */
    public AboutController() {
    }

    /**
     * @return View
     */
    @RequestMapping("/about")
    public String about() {
        return Paths.ABOUT;
    }

    /**
     * @return View
     */
    @RequestMapping("/about/credits")
    public String credits() {
        return Paths.ABOUT_CREDITS;
    }

    /**
     * @return View
     */
    @RequestMapping("/about/people")
    public String people() {
        return Paths.ABOUT_PEOPLE;
    }

    /**
     * @return View
     */
    @RequestMapping("/about/license")
    public String license() {
        return Paths.ABOUT_LICENSE;
    }

    /**
     * @return View
     */
    @RequestMapping("/about/contactus")
    public String contactUs() {
        return Paths.ABOUT_CONTACT_US;
    }
}
