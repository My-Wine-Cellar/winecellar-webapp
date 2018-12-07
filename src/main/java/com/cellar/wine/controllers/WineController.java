package com.cellar.wine.controllers;

import com.cellar.wine.model.Producer;
import com.cellar.wine.model.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/wines")
@Controller
public class WineController {

    private final WineService wineService;
    private final ProducerService producerService;

    public WineController(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }


}