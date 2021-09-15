/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.TastedConverter;
import info.mywinecellar.model.Tasted;
import info.mywinecellar.model.User;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.TastedService;
import info.mywinecellar.service.UserService;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasted")
public class TastedController extends AbstractController {

    @Inject
    TastedService tastedService;

    @Inject
    TastedConverter tastedConverter;

    @Inject
    private UserService userService;

    /**
     * Default constructor
     */
    public TastedController() {
        super();
    }

    /**
     * @param tastedId  tastedId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{tastedId}/delete")
    public String tastedDeleteGet(@PathVariable Long tastedId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        Tasted tasted = tastedService.findByUser(user.getId(), tastedId);

        if (tasted != null) {
            tastedService.delete(tasted);
        }

        model.addAttribute(Attributes.TASTED, tastedConverter.toDto(user, user.getTasted()));
        return Paths.TASTED_LIST;
    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/list")
    public String tastedList(Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        User user = userService.findByUsername(principal.getName());
        model.addAttribute(Attributes.TASTED, tastedConverter.toDto(user, user.getTasted()));
        return Paths.TASTED_LIST;
    }
}
