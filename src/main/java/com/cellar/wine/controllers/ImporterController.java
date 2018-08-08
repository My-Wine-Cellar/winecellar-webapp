package com.cellar.wine.controllers;

import com.cellar.wine.repositories.ImporterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImporterController {

    private ImporterRepository importerRepository;

    public ImporterController(ImporterRepository importerRepository) {
        this.importerRepository = importerRepository;
    }

    @RequestMapping("importers")
    public String getImporters(Model model) {
        model.addAttribute("importers", importerRepository.findAll());
        return "importers";
    }
}
