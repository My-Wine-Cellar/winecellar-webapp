package com.cellar.wine.controllers;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.RegionUI;
import com.cellar.wine.ui.WineUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/wine")
public class WineController {

    private static final String MODEL_ATTRIBUTE_WINE = "wine";
    private static final String ADD_OR_EDIT_WINE_TEMPLATE = "wine/addEditWine";

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @InitBinder("wine")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String wineNewGet(Producer producer, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        Wine wine = new Wine();
        wine.setProducer(producer);
        model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/new")
    public String wineNewPost(@Valid Wine wine, BindingResult result, ModelMap model,
                              @RequestParam Long producerId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.put(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Producer producer = producerService.findById(producerId);
            wine.setProducer(producer);
            producer.getWines().add(wine);

            Wine savedWine = wineService.save(wine);

            CountryUI cui = new CountryUI(producer.getAreas().get(0).getRegions().get(0).getCountry());
            RegionUI rui = new RegionUI(producer.getAreas().get(0).getRegions().get(0));
            AreaUI aui = new AreaUI(producer.getAreas().get(0));
            ProducerUI pui = new ProducerUI(producer);
            WineUI wui = new WineUI(savedWine);
            
            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey() + "/" + pui.getKey() +
                "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
        }
    }

    @GetMapping("/{wineId}/edit")
    public String wineEditGet(@PathVariable Long wineId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute(wineService.findById(wineId));
        return ADD_OR_EDIT_WINE_TEMPLATE;
    }

    @PostMapping("/{wineId}/edit")
    public String wineEditPost(@Valid Wine wine, BindingResult result, Model model,
                               @PathVariable Long wineId, @RequestParam Long producerId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_WINE, wine);
            return ADD_OR_EDIT_WINE_TEMPLATE;
        } else {
            Producer producer = producerService.findById(producerId);

            wine.setId(wineId);
            wine.setProducer(producer);

            Wine savedWine = wineService.save(wine);

            CountryUI cui = new CountryUI(producer.getAreas().get(0).getRegions().get(0).getCountry());
            RegionUI rui = new RegionUI(producer.getAreas().get(0).getRegions().get(0));
            AreaUI aui = new AreaUI(producer.getAreas().get(0));
            ProducerUI pui = new ProducerUI(producer);
            WineUI wui = new WineUI(savedWine);
            
            return "redirect:/d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey() + "/" + pui.getKey() +
                "/" + wui.getKey() + "/" + wui.getVintage() + "/" + wui.getSize();
        }
    }
}
