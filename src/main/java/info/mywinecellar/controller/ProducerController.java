/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
@RequestMapping("/producer")
public class ProducerController extends AbstractController {

    /**
     * Default constructor
     */
    public ProducerController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param binder binder
     */
    @InitBinder
    public void imageBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    /**
     * @param producerId producerId
     * @param model      model
     * @param principal  principal
     * @return View
     */
    @GetMapping("/{producerId}/edit")
    public String producerEditGet(@PathVariable Long producerId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.AREA, areaConverter.toDto(areaService.findById(Session.getAreaId())));
        model.addAttribute(Attributes.PRODUCER, producerConverter.toDto(producerService.findById(producerId)));

        return Paths.PRODUCER_ADD_EDIT;
    }

    /**
     * @param producerDto producer
     * @param result      result
     * @param principal   principal
     * @param producerId  producerId
     * @param action      action
     * @return View
     */
    @PostMapping("/{producerId}/edit")
    public String producerEditPost(@ModelAttribute(Attributes.PRODUCER) @Valid ProducerDto producerDto,
                                   BindingResult result, Principal principal,
                                   @PathVariable Long producerId,
                                   @RequestParam("action") String action) {
        principalNull(principal);

        if (action.equals("cancel")) {
            return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
        }
        if (result.hasErrors()) {
            return Paths.PRODUCER_ADD_EDIT;
        } else {
            if (action.equals("save")) {
                producerService.editProducer(producerDto, producerId);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
            }
        }
        return Paths.ERROR_PAGE;
    }

    /**
     * @param producerId producerId
     * @param model      model
     * @param principal  principal
     * @return View
     */
    @GetMapping("/{producerId}/image")
    public String producerImageGet(@PathVariable Long producerId, Model model, Principal principal) {
        principalNull(principal);
        model.addAttribute(Attributes.PRODUCER, producerService.findById(producerId));
        return Paths.PRODUCER_IMAGE;
    }

    /**
     * @param producer   producer
     * @param result     result
     * @param principal  principal
     * @param producerId producerId
     * @param action     action
     * @return View
     */
    @PostMapping("/{producerId}/image")
    public String producerImagePost(Producer producer, BindingResult result, Principal principal,
                                    @PathVariable Long producerId,
                                    @RequestParam("action") String action) {
        principalNull(principal);
        Producer saveProducer = producerService.findById(producerId);
        if (action.equals("cancel")) {
            return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
        } else {
            if (action.equals("save")) {
                saveProducer.setImage(producer.getImage());
                if (saveProducer.getImage().length >= 5242880L) {
                    result.rejectValue("image", "error.imageSize");
                    return Paths.PRODUCER_IMAGE;
                }
                producerService.save(saveProducer);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(), producerId);
            }
        }
        return Paths.ERROR_PAGE;
    }
}
