/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.BarrelComponentConverter;
import info.mywinecellar.converter.BarrelConverter;
import info.mywinecellar.converter.ClosureConverter;
import info.mywinecellar.converter.ColorConverter;
import info.mywinecellar.converter.GrapeComponentConverter;
import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.converter.ShapeConverter;
import info.mywinecellar.converter.TypeConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.dto.GrapeComponentDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.Fermentation;
import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Maceration;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Wine;
import info.mywinecellar.nav.Actions;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.nav.Session;
import info.mywinecellar.service.BarrelComponentService;
import info.mywinecellar.service.BarrelService;
import info.mywinecellar.service.ClosureService;
import info.mywinecellar.service.ColorService;
import info.mywinecellar.service.FermentationService;
import info.mywinecellar.service.GrapeComponentService;
import info.mywinecellar.service.GrapeService;
import info.mywinecellar.service.MacerationService;
import info.mywinecellar.service.ShapeService;
import info.mywinecellar.service.TypeService;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
@SessionAttributes({"wine", "grapeComponent", "barrelComponent", "grapes", "barrels"})
@RequestMapping("/wine")
public class WineController extends AbstractController {

    @Inject
    GrapeComponentService grapeComponentService;

    @Inject
    GrapeService grapeService;

    @Inject
    BarrelService barrelService;

    @Inject
    BarrelComponentService barrelComponentService;

    @Inject
    ColorService colorService;

    @Inject
    ClosureService closureService;

    @Inject
    ShapeService shapeService;

    @Inject
    TypeService typeService;

    @Inject
    FermentationService fermentationService;

    @Inject
    MacerationService macerationService;

    /**
     * Default constructor
     */
    public WineController() {
        super();
    }

    /**
     * @param dataBinder dataBinder
     */
    @InitBinder("wine")
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
     * @param dataBinder dataBinder
     */
    @InitBinder
    public void allowEmptyDates(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * @param producerDto producerDto
     * @param model       model
     * @param principal   principal
     * @return View
     */
    @GetMapping("/new")
    public String wineAddRequiredGet(ProducerDto producerDto, Model model, Principal principal) {
        principalNull(principal);

        WineDto wineDto = new WineDto();
        wineDto.setProducerId(producerDto.getId());
        model.addAttribute(Attributes.WINE, wineDto);
        addWineAttributes(model);

        return Paths.WINE_ADD_EDIT_DETAILS;
    }

    /**
     * @param requestDto wineDto
     * @param result     result
     * @param principal  principal
     * @param status     status
     * @param producerId producerId
     * @param model      model
     * @param action     action
     * @return View
     */
    @PostMapping("/new")
    public String wineAddRequiredPost(@ModelAttribute(Attributes.WINE) @Valid WineDto requestDto, BindingResult result,
                                      Principal principal, SessionStatus status,
                                      @RequestParam Long producerId, Model model,
                                      @RequestParam(value = "action") String action) {
        principalNull(principal);

        if (action.equals(Actions.CANCEL)) {
            Session.clear(status);
            return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                    Session.getAreaId(), Session.getProducerId());
        }
        if (result.hasErrors()) {
            requestDto.setProducerId(producerId);
            addWineAttributes(model);
            return Paths.WINE_ADD_EDIT_DETAILS;
        }

        Producer producer = producerService.findById(producerId);
        Wine entity = WineConverter.toEntity(null, requestDto);
        producer.getWines().add(entity);
        switch (action) {
            case Actions.SAVE_WINE:
                wineService.save(entity);
                WineDto saveDto = WineConverter.toDto(entity);

                Session.clear(status);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                        Session.getAreaId(), Session.getProducerId()) +
                        "/" + saveDto.getKey() + "/" + saveDto.getVintage() + "/" + saveDto.getSize();
            case Actions.ADD_GRAPE:
                return Paths.REDIRECT_WINE_GRAPE;
            default:
                Session.clear(status);
                return Paths.ERROR_PAGE;
        }

    }

    /**
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/grape")
    public String wineAddGrapeComponentsGet(Model model, Principal principal) {
        principalNull(principal);

        addGrapeAttributes(model);
        return Paths.WINE_ADD_GRAPE;
    }

    /**
     * @param status         status
     * @param principal      principal
     * @param model          model
     * @param grapeComponent grapeComponent
     * @param result         result
     * @param action         action
     * @return View
     */
    @PostMapping("/grape")
    public String wineAddGrapeComponentsPost(SessionStatus status, Principal principal, Model model,
                                             @Valid GrapeComponentDto grapeComponent, BindingResult result,
                                             @RequestParam(value = "action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            addGrapeAttributes(model);
            return Paths.WINE_ADD_GRAPE;
        }

        WineDto session = Session.getWine();

        Session.setGrapeComponent(grapeComponent);
        List<GrapeComponentDto> grapes = Session.getGrapeComponents();
        List<BarrelDto> barrels = Session.getBarrelComponents();

        Wine wine = WineConverter.toEntity(null, session);

        switch (action) {
            case Actions.ADD_ANOTHER_GRAPE:
                return Paths.REDIRECT_WINE_GRAPE;
            case Actions.ADD_BARREL:
                return Paths.REDIRECT_WINE_GRAPE + "/" + grapeComponent.getGrapeId() + "/barrel";
            case Actions.SAVE_WINE:
                wineService.save(wine);
                grapes.forEach(grape -> grape.setWineId(wine.getId()));

                saveOrNullFermentation(grapes);
                saveOrNullMaceration(grapes);

                List<GrapeComponent> grapeList = GrapeComponentConverter.toEntity(grapes);

                grapeComponentService.saveAll(grapeList);

                if (barrels != null) {
                    List<BarrelComponent> barrelList = BarrelComponentConverter.toEntity(barrels);
                    barrelComponentService.saveAll(barrelList);
                }

                WineDto wineDto = WineConverter.toDto(wine);
                Session.clear(status);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                        Session.getAreaId(), Session.getProducerId()) +
                        "/" + wineDto.getKey() + "/" + wineDto.getVintage() + "/" + wineDto.getSize();
            default:
                Session.clear(status);
                return Paths.ERROR_PAGE;
        }
    }

    /**
     * @param grapeId   grapeId
     * @param principal principal
     * @param model     model
     * @return View
     */
    @GetMapping("/grape/{grapeId}/barrel")
    public String wineAddGrapeBarrelGet(@PathVariable Long grapeId,
                                        Principal principal, Model model) {
        principalNull(principal);

        addBarrelAttributes(model, grapeId);
        return Paths.WINE_ADD_GRAPE_BARREL;
    }

    /**
     * @param principal       principal
     * @param status          status
     * @param grapeId         grapeId
     * @param model           model
     * @param barrelComponent barrelComponent
     * @param result          result
     * @param action          action
     * @return View
     */
    @PostMapping("/grape/{grapeId}/barrel")
    public String wineAddGrapeBarrelPost(Principal principal, SessionStatus status,
                                         @PathVariable Long grapeId, Model model,
                                         @Valid BarrelDto barrelComponent, BindingResult result,
                                         @RequestParam(value = "action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            addBarrelAttributes(model, grapeId);
            return Paths.WINE_ADD_GRAPE_BARREL;
        }

        WineDto session = Session.getWine();
        GrapeComponentDto grapeComponent = Session.getGrapeComponent();

        Session.setBarrelComponent(barrelComponent);
        List<BarrelDto> barrels = Session.getBarrelComponents();
        List<GrapeComponentDto> grapes = Session.getGrapeComponents();

        Wine wine = WineConverter.toEntity(null, session);

        switch (action) {
            case Actions.ADD_ANOTHER_GRAPE:
                return Paths.REDIRECT_WINE_GRAPE;
            case Actions.ADD_ANOTHER_BARREL:
                return Paths.REDIRECT_WINE_GRAPE + "/" + grapeComponent.getGrapeId() + "/barrel";
            case Actions.SAVE_WINE:
                wineService.save(wine);
                grapes.forEach(grape -> grape.setWineId(wine.getId()));

                saveOrNullFermentation(grapes);
                saveOrNullMaceration(grapes);

                List<GrapeComponent> grapeList = GrapeComponentConverter.toEntity(grapes);
                grapeComponentService.saveAll(grapeList);
                grapeList.forEach(grape -> barrels.forEach(barrel -> barrel.setGrapeComponentId(grape.getId())));

                List<BarrelComponent> barrelList = BarrelComponentConverter.toEntity(barrels);
                barrelComponentService.saveAll(barrelList);

                WineDto wineDto = WineConverter.toDto(wine);

                Session.clear(status);
                return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                        Session.getAreaId(), Session.getProducerId()) +
                        "/" + wineDto.getKey() + "/" + wineDto.getVintage() + "/" + wineDto.getSize();
            default:
                Session.clear(status);
                return Paths.ERROR_PAGE;
        }

    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{wineId}/edit")
    public String wineEditGet(@PathVariable Long wineId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.WINE, WineConverter.toDto(wineService.findById(wineId)));
        addWineAttributes(model);

        return Paths.WINE_ADD_EDIT_DETAILS;
    }

    /**
     * @param requestDto wine
     * @param result     result
     * @param model      model
     * @param principal  principal
     * @param status     status
     * @param action     action
     * @param wineId     wineId
     * @param producerId producerId
     * @return View
     */
    @PostMapping("/{wineId}/edit")
    public String wineEditPost(@ModelAttribute(Attributes.WINE) @Valid WineDto requestDto, BindingResult result,
                               Model model, Principal principal, SessionStatus status,
                               @RequestParam(value = "action") String action,
                               @PathVariable Long wineId, @RequestParam Long producerId) {
        principalNull(principal);

        if (result.hasErrors()) {
            requestDto.setProducerId(producerId);
            addWineAttributes(model);
            return Paths.WINE_ADD_EDIT_DETAILS;
        } else {
            Wine entity = WineConverter.toEntity(wineService.findById(wineId), requestDto);
            entity.setProducer(producerService.findById(producerId));
            switch (action) {
                case Actions.SAVE_WINE:
                    wineService.update(entity);
                    WineDto saveDto = WineConverter.toDto(entity);

                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + saveDto.getKey() + "/" + saveDto.getVintage() + "/" + saveDto.getSize();
                case Actions.ADD_GRAPE:
                    return Paths.REDIRECT_WINE_GRAPE;
                case Actions.CANCEL:
                    WineDto cancelDto = WineConverter.toDto(entity);
                    Session.clear(status);
                    return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                            Session.getAreaId(), Session.getProducerId()) +
                            "/" + cancelDto.getKey() + "/" + cancelDto.getVintage() + "/" + cancelDto.getSize();
                default:
                    Session.clear(status);
                    return Paths.ERROR_PAGE;
            }
        }
    }

    /**
     * @param wineId    wineId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{wineId}/image")
    public String wineImageGet(@PathVariable Long wineId, Model model, Principal principal) {
        principalNull(principal);
        model.addAttribute(Attributes.WINE, wineService.findById(wineId));
        return Paths.WINE_IMAGE;
    }

    /**
     * @param requestDto wine
     * @param result     result
     * @param principal  principal
     * @param status     status
     * @param model      model
     * @param action     action
     * @param wineId     wineId
     * @return View
     */
    @PostMapping("/{wineId}/image")
    public String wineImagePost(@ModelAttribute(Attributes.WINE) @Valid WineDto requestDto, BindingResult result,
                                Principal principal, SessionStatus status, Model model,
                                @RequestParam(value = "action") String action, @PathVariable Long wineId) {
        principalNull(principal);

        if (result.hasErrors()) {
            model.addAttribute(Attributes.WINE, wineService.findById(wineId));
            return Paths.WINE_IMAGE;
        }

        Wine entity = WineConverter.toEntity(null, requestDto);

        if (action.equals("save")) {
            Wine saveWine = wineService.findById(wineId);
            saveWine.setImage(entity.getImage());
            if (saveWine.getImage().length >= 5242880L) {
                result.rejectValue("image", "error.imageSize");
                return Paths.WINE_IMAGE;
            }
            wineService.save(saveWine);
            WineDto wineDto = WineConverter.toDto(entity);
            Session.clear(status);
            return redirectProducer(Session.getCountryId(), Session.getRegionId(), Session.getAreaId(),
                    Session.getProducerId()) + "/" + wineDto.getKey() + "/" +
                    wineDto.getVintage() + "/" + wineDto.getSize();
        } else if (action.equals("cancel")) {
            WineDto cancelDto = WineConverter.toDto(entity);
            Session.clear(status);
            return redirectProducer(Session.getCountryId(), Session.getRegionId(),
                    Session.getAreaId(), Session.getProducerId()) +
                    "/" + cancelDto.getKey() + "/" + cancelDto.getVintage() + "/" + cancelDto.getSize();

        }
        return Paths.ERROR_PAGE;
    }

    private void addWineAttributes(Model model) {
        model.addAttribute(Attributes.COLOR, ColorConverter.toDto(colorService.findAll()));
        model.addAttribute(Attributes.TYPE, TypeConverter.toDto(typeService.findAll()));
        model.addAttribute(Attributes.SHAPE, ShapeConverter.toDto(shapeService.findAll()));
        model.addAttribute(Attributes.CLOSURE, ClosureConverter.toDto(closureService.findAll()));
    }

    private void addGrapeAttributes(Model model) {
        model.addAttribute(Attributes.WINE, Session.getWine());
        model.addAttribute(Attributes.GRAPE_COMPONENT, new GrapeComponentDto());
        model.addAttribute(Attributes.WINEGRAPES, GrapeConverter.toDto(grapeService.findAll()));
    }

    private void addBarrelAttributes(Model model, Long grapeId) {
        model.addAttribute(Attributes.WINE, Session.getWine());
        model.addAttribute(Attributes.GRAPE, GrapeConverter.toDto(grapeService.findById(grapeId)));
        model.addAttribute(Attributes.BARREL_COMPONENT, new BarrelDto());
        model.addAttribute(Attributes.BARRELS, BarrelConverter.toDto(barrelService.findAll()));
    }

    private void saveOrNullMaceration(List<GrapeComponentDto> grapes) {

        grapes.forEach(grape -> {
            if (grape.getMacerationDays() != null) {
                Maceration maceration = new Maceration();
                maceration.setDays(grape.getMacerationDays());
                maceration.setTemperature(grape.getMacerationTemperature());
                macerationService.save(maceration);
                grape.setMacerationId(maceration.getId());
            } else {
                grape.setMacerationId(null);
            }
        });
    }

    private void saveOrNullFermentation(List<GrapeComponentDto> grapes) {
        grapes.forEach(grape -> {
            if (grape.getFermentationDays() != null) {
                Fermentation fermentation = new Fermentation();
                fermentation.setDays(grape.getFermentationDays());
                fermentation.setTemperature(grape.getFermentationTemperature());
                fermentationService.save(fermentation);
                grape.setFermentationId(fermentation.getId());
            } else {
                grape.setFermentationId(null);
            }
        });
    }
}
