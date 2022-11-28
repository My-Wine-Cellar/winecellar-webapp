/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.UserService;
import info.mywinecellar.service.WineService;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import jakarta.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * WSET converter
 */
@Component
public class WSETConverter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserService userService;

    @Inject
    private WineService wineService;

    @Inject
    private WSETService wsetService;

    /**
     * Create a list of Dto objects
     * @param wsets The WSETs
     * @return The Dto objects
     */
    public List<WSETDto> toDto(List<WSET> wsets) {
        if (wsets == null) {
            throw new IllegalStateException("WSETs is null");
        }

        List<WSETDto> result = new ArrayList<>();
        List<WineDto> wines = new ArrayList<>();
        for (WSET w : wsets) {
            result.add(toDto(w));
            wines.add(WineConverter.toDto(w.getWine()));
        }
        Collections.sort(result, new WSETDtoSorter(wines));

        return result;
    }

    /**
     * Create a Dto object
     * @param w A WSET
     * @return The Dto
     */
    public WSETDto toDto(WSET w) {
        if (w == null) {
            throw new IllegalStateException("WSET is null");
        }

        return new WSETDto(w);
    }

    /**
     * Create an WSET entity
     * @param w A WSET
     * @param ui The Dto object
     * @return The WSET
     */
    public WSET toEntity(WSET w, WSETDto ui) {
        Date date = new Date(System.currentTimeMillis());

        if (w == null) {
            User user = userService.findById(ui.getUserId());
            Wine wine = wineService.findById(ui.getWineId());

            w = new WSET();
            w.setUser(user);
            w.setWine(wine);
        }

        w.setLevel(Byte.valueOf((byte) 4));
        w.setDate(date);
        w.setShow(ui.getShow());

        processCommon(w, ui);

        processNoseFloral(w, ui);
        processNoseGreenFruit(w, ui);
        processNoseCitrusFruit(w, ui);
        processNoseStoneFruit(w, ui);
        processNoseTropicalFruit(w, ui);
        processNoseRedFruit(w, ui);
        processNoseBlackFruit(w, ui);
        processNoseHerbaceous(w, ui);
        processNoseHerbal(w, ui);
        processNoseSpice(w, ui);
        processNoseFruitRipeness(w, ui);
        processNoseYeast(w, ui);
        processNoseMalo(w, ui);
        processNoseOak(w, ui);
        processNoseTertiary(w, ui);
        processNoseOxidised(w, ui);

        processPalateFloral(w, ui);
        processPalateGreenFruit(w, ui);
        processPalateCitrusFruit(w, ui);
        processPalateStoneFruit(w, ui);
        processPalateTropicalFruit(w, ui);
        processPalateRedFruit(w, ui);
        processPalateBlackFruit(w, ui);
        processPalateHerbaceous(w, ui);
        processPalateHerbal(w, ui);
        processPalateSpice(w, ui);
        processPalateFruitRipeness(w, ui);
        processPalateYeast(w, ui);
        processPalateMalo(w, ui);
        processPalateOak(w, ui);
        processPalateTertiary(w, ui);
        processPalateOxidised(w, ui);

        return w;
    }

    private void processCommon(WSET w, WSETDto u) {
        w.setAppearanceIntensity(wsetService.find(WSETAppearanceIntensity.class, u.getAppearanceIntensity()));
        w.setAppearanceColor(wsetService.find(WSETAppearanceColor.class, u.getAppearanceColor()));
        w.setNoseIntensity(wsetService.find(WSETNoseIntensity.class, u.getNoseIntensity()));
        w.setPalateSweetness(wsetService.find(WSETPalateSweetness.class, u.getPalateSweetness()));
        w.setPalateAcidity(wsetService.find(WSETPalateAcidity.class, u.getPalateAcidity()));
        w.setPalateTanninLevel(wsetService.find(WSETPalateTanninLevel.class, u.getPalateTanninLevel()));
        w.setPalateTanninNature(wsetService.find(WSETPalateTanninNature.class, u.getPalateTanninNature()));
        w.setPalateAlcohol(wsetService.find(WSETPalateAlcohol.class, u.getPalateAlcohol()));
        w.setPalateBody(wsetService.find(WSETPalateBody.class, u.getPalateBody()));
        w.setPalateIntensity(wsetService.find(WSETPalateIntensity.class, u.getPalateIntensity()));
        w.setPalateFinish(wsetService.find(WSETPalateFinish.class, u.getPalateFinish()));
        w.setConclusionQuality(wsetService.find(WSETConclusionQuality.class, u.getConclusionQuality()));
        w.setConclusionQualityExplanation(u.getConclusionQualityExplanation());
        w.setConclusionBottle(wsetService.find(WSETConclusionBottleAgeing.class, u.getConclusionBottle()));
        w.setConclusionBottleExplanation(u.getConclusionBottleExplanation());

        w.setNoseOther(u.getNoseOther());
        w.setPalateOther(u.getPalateOther());
        w.setPalateObservations(u.getPalateObservations());
    }

    private void processNoseFloral(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseFloralBlossom()) {
            ids.add(WSETFloral.BLOSSOM);
        }
        if (u.getNoseFloralElderflower()) {
            ids.add(WSETFloral.ELDERFLOWER);
        }
        if (u.getNoseFloralHoneysuckle()) {
            ids.add(WSETFloral.HONEYSUCKLE);
        }
        if (u.getNoseFloralJasmine()) {
            ids.add(WSETFloral.JASMINE);
        }
        if (u.getNoseFloralRose()) {
            ids.add(WSETFloral.ROSE);
        }
        if (u.getNoseFloralViolet()) {
            ids.add(WSETFloral.VIOLET);
        }

        if (w.getNoseFloral() != null) {
            for (WSETFloral f : w.getNoseFloral()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETFloral> s = w.getNoseFloral();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETFloral.class, id));
        }

        w.setNoseFloral(s);
    }

    private void processNoseGreenFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseGreenFruitApple()) {
            ids.add(WSETGreenFruit.APPLE);
        }
        if (u.getNoseGreenFruitPear()) {
            ids.add(WSETGreenFruit.PEAR);
        }
        if (u.getNoseGreenFruitGooseberry()) {
            ids.add(WSETGreenFruit.GOOSEBERRY);
        }
        if (u.getNoseGreenFruitGrape()) {
            ids.add(WSETGreenFruit.GRAPE);
        }

        if (w.getNoseGreenFruit() != null) {
            for (WSETGreenFruit f : w.getNoseGreenFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETGreenFruit> s = w.getNoseGreenFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETGreenFruit.class, id));
        }

        w.setNoseGreenFruit(s);
    }

    private void processNoseCitrusFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseCitrusFruitGrapefruit()) {
            ids.add(WSETCitrusFruit.GRAPEFRUIT);
        }
        if (u.getNoseCitrusFruitLemon()) {
            ids.add(WSETCitrusFruit.LEMON);
        }
        if (u.getNoseCitrusFruitLime()) {
            ids.add(WSETCitrusFruit.LIME);
        }
        if (u.getNoseCitrusFruitOrange()) {
            ids.add(WSETCitrusFruit.ORANGE);
        }

        if (w.getNoseCitrusFruit() != null) {
            for (WSETCitrusFruit f : w.getNoseCitrusFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETCitrusFruit> s = w.getNoseCitrusFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETCitrusFruit.class, id));
        }

        w.setNoseCitrusFruit(s);
    }

    private void processNoseStoneFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseStoneFruitPeach()) {
            ids.add(WSETStoneFruit.PEACH);
        }
        if (u.getNoseStoneFruitApricot()) {
            ids.add(WSETStoneFruit.APRICOT);
        }
        if (u.getNoseStoneFruitNectarine()) {
            ids.add(WSETStoneFruit.NECTARINE);
        }

        if (w.getNoseStoneFruit() != null) {
            for (WSETStoneFruit f : w.getNoseStoneFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETStoneFruit> s = w.getNoseStoneFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETStoneFruit.class, id));
        }

        w.setNoseStoneFruit(s);
    }

    private void processNoseTropicalFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseTropicalFruitBanana()) {
            ids.add(WSETTropicalFruit.BANANA);
        }
        if (u.getNoseTropicalFruitLychee()) {
            ids.add(WSETTropicalFruit.LYCHEE);
        }
        if (u.getNoseTropicalFruitMango()) {
            ids.add(WSETTropicalFruit.MANGO);
        }
        if (u.getNoseTropicalFruitMelon()) {
            ids.add(WSETTropicalFruit.MELON);
        }
        if (u.getNoseTropicalFruitPassionFruit()) {
            ids.add(WSETTropicalFruit.PASSION_FRUIT);
        }
        if (u.getNoseTropicalFruitPineapple()) {
            ids.add(WSETTropicalFruit.PINEAPPLE);
        }

        if (w.getNoseTropicalFruit() != null) {
            for (WSETTropicalFruit f : w.getNoseTropicalFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETTropicalFruit> s = w.getNoseTropicalFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETTropicalFruit.class, id));
        }

        w.setNoseTropicalFruit(s);
    }

    private void processNoseRedFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseRedFruitRedcurrant()) {
            ids.add(WSETRedFruit.REDCURRANT);
        }
        if (u.getNoseRedFruitCranberry()) {
            ids.add(WSETRedFruit.CRANBERRY);
        }
        if (u.getNoseRedFruitRaspberry()) {
            ids.add(WSETRedFruit.RASPBERRY);
        }
        if (u.getNoseRedFruitStrawberry()) {
            ids.add(WSETRedFruit.STRAWBERRY);
        }
        if (u.getNoseRedFruitRedCherry()) {
            ids.add(WSETRedFruit.RED_CHERRY);
        }
        if (u.getNoseRedFruitRedPlum()) {
            ids.add(WSETRedFruit.RED_PLUM);
        }

        if (w.getNoseRedFruit() != null) {
            for (WSETRedFruit f : w.getNoseRedFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETRedFruit> s = w.getNoseRedFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETRedFruit.class, id));
        }

        w.setNoseRedFruit(s);
    }

    private void processNoseBlackFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseBlackFruitBlackcurrant()) {
            ids.add(WSETBlackFruit.BLACKCURRANT);
        }
        if (u.getNoseBlackFruitBlackberry()) {
            ids.add(WSETBlackFruit.BLACKBERRY);
        }
        if (u.getNoseBlackFruitBlueberry()) {
            ids.add(WSETBlackFruit.BLUEBERRY);
        }
        if (u.getNoseBlackFruitBlackCherry()) {
            ids.add(WSETBlackFruit.BLACK_CHERRY);
        }
        if (u.getNoseBlackFruitBlackPlum()) {
            ids.add(WSETBlackFruit.BLACK_PLUM);
        }

        if (w.getNoseBlackFruit() != null) {
            for (WSETBlackFruit f : w.getNoseBlackFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETBlackFruit> s = w.getNoseBlackFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETBlackFruit.class, id));
        }

        w.setNoseBlackFruit(s);
    }

    private void processNoseHerbaceous(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseHerbaceousGreenBellPepper()) {
            ids.add(WSETHerbaceous.GREEN_BELL_PEPPER);
        }
        if (u.getNoseHerbaceousGrass()) {
            ids.add(WSETHerbaceous.GRASS);
        }
        if (u.getNoseHerbaceousTomatoLeaf()) {
            ids.add(WSETHerbaceous.TOMATO_LEAF);
        }
        if (u.getNoseHerbaceousAsparagus()) {
            ids.add(WSETHerbaceous.ASPARAGUS);
        }

        if (w.getNoseHerbaceous() != null) {
            for (WSETHerbaceous f : w.getNoseHerbaceous()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETHerbaceous> s = w.getNoseHerbaceous();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETHerbaceous.class, id));
        }

        w.setNoseHerbaceous(s);
    }

    private void processNoseHerbal(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseHerbalEucalyptus()) {
            ids.add(WSETHerbal.EUCALYPTUS);
        }
        if (u.getNoseHerbalMint()) {
            ids.add(WSETHerbal.MINT);
        }
        if (u.getNoseHerbalFennel()) {
            ids.add(WSETHerbal.FENNEL);
        }
        if (u.getNoseHerbalDill()) {
            ids.add(WSETHerbal.DILL);
        }
        if (u.getNoseHerbalDriedHerbs()) {
            ids.add(WSETHerbal.DRIED_HERBS);
        }

        if (w.getNoseHerbal() != null) {
            for (WSETHerbal f : w.getNoseHerbal()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETHerbal> s = w.getNoseHerbal();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETHerbal.class, id));
        }

        w.setNoseHerbal(s);
    }

    private void processNoseSpice(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseSpiceBlackWhitePepper()) {
            ids.add(WSETSpice.BLACK_WHITE_PEPPER);
        }
        if (u.getNoseSpiceLiquorice()) {
            ids.add(WSETSpice.LIQUORICE);
        }
        if (u.getNoseSpiceCinnamon()) {
            ids.add(WSETSpice.CINNAMON);
        }

        if (w.getNoseSpice() != null) {
            for (WSETSpice f : w.getNoseSpice()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETSpice> s = w.getNoseSpice();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETSpice.class, id));
        }

        w.setNoseSpice(s);
    }

    private void processNoseFruitRipeness(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseFruitRipenessUnripeFruit()) {
            ids.add(WSETFruitRipeness.UNRIPE_FRUIT);
        }
        if (u.getNoseFruitRipenessRipeFruit()) {
            ids.add(WSETFruitRipeness.RIPE_FRUIT);
        }
        if (u.getNoseFruitRipenessDriedFruit()) {
            ids.add(WSETFruitRipeness.DRIED_FRUIT);
        }
        if (u.getNoseFruitRipenessCookedFruit()) {
            ids.add(WSETFruitRipeness.COOKED_FRUIT);
        }

        if (w.getNoseFruitRipeness() != null) {
            for (WSETFruitRipeness f : w.getNoseFruitRipeness()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETFruitRipeness> s = w.getNoseFruitRipeness();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETFruitRipeness.class, id));
        }

        w.setNoseFruitRipeness(s);
    }

    private void processNoseYeast(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseYeastBiscuit()) {
            ids.add(WSETYeast.BISCUIT);
        }
        if (u.getNoseYeastPastry()) {
            ids.add(WSETYeast.PASTRY);
        }
        if (u.getNoseYeastBread()) {
            ids.add(WSETYeast.BREAD);
        }
        if (u.getNoseYeastToastedBread()) {
            ids.add(WSETYeast.TOASTED_BREAD);
        }
        if (u.getNoseYeastBreadDough()) {
            ids.add(WSETYeast.BREAD_DOUGH);
        }
        if (u.getNoseYeastBrioche()) {
            ids.add(WSETYeast.BRIOCHE);
        }
        if (u.getNoseYeastCheese()) {
            ids.add(WSETYeast.CHEESE);
        }
        if (u.getNoseYeastYogurt()) {
            ids.add(WSETYeast.YOGURT);
        }
        if (u.getNoseYeastAcetaldehyde()) {
            ids.add(WSETYeast.ACETALDEHYDE);
        }

        if (w.getNoseYeast() != null) {
            for (WSETYeast f : w.getNoseYeast()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETYeast> s = w.getNoseYeast();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETYeast.class, id));
        }

        w.setNoseYeast(s);
    }

    private void processNoseMalo(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseMaloButter()) {
            ids.add(WSETMalo.BUTTER);
        }
        if (u.getNoseMaloCream()) {
            ids.add(WSETMalo.CREAM);
        }
        if (u.getNoseMaloCheese()) {
            ids.add(WSETMalo.CHEESE);
        }

        if (w.getNoseMalo() != null) {
            for (WSETMalo f : w.getNoseMalo()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETMalo> s = w.getNoseMalo();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETMalo.class, id));
        }

        w.setNoseMalo(s);
    }

    private void processNoseOak(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseOakVanilla()) {
            ids.add(WSETOak.VANILLA);
        }
        if (u.getNoseOakCloves()) {
            ids.add(WSETOak.CLOVES);
        }
        if (u.getNoseOakCoconut()) {
            ids.add(WSETOak.COCONUT);
        }
        if (u.getNoseOakCedar()) {
            ids.add(WSETOak.CEDAR);
        }
        if (u.getNoseOakCharredWood()) {
            ids.add(WSETOak.CHARRED_WOOD);
        }
        if (u.getNoseOakSmoke()) {
            ids.add(WSETOak.SMOKE);
        }
        if (u.getNoseOakChocolate()) {
            ids.add(WSETOak.CHOCOLATE);
        }
        if (u.getNoseOakCoffee()) {
            ids.add(WSETOak.COFFEE);
        }

        if (w.getNoseOak() != null) {
            for (WSETOak f : w.getNoseOak()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETOak> s = w.getNoseOak();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETOak.class, id));
        }

        w.setNoseOak(s);
    }

    private void processNoseTertiary(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseTertiaryDriedFruit()) {
            ids.add(WSETTertiary.DRIED_FRUIT);
        }
        if (u.getNoseTertiaryCookedFruit()) {
            ids.add(WSETTertiary.COOKED_FRUIT);
        }
        if (u.getNoseTertiaryLeather()) {
            ids.add(WSETTertiary.LEATHER);
        }
        if (u.getNoseTertiaryEarth()) {
            ids.add(WSETTertiary.EARTH);
        }
        if (u.getNoseTertiaryMushroom()) {
            ids.add(WSETTertiary.MUSHROOM);
        }
        if (u.getNoseTertiaryMeat()) {
            ids.add(WSETTertiary.MEAT);
        }
        if (u.getNoseTertiaryTobacco()) {
            ids.add(WSETTertiary.TOBACCO);
        }
        if (u.getNoseTertiaryWetLeaves()) {
            ids.add(WSETTertiary.WET_LEAVES);
        }
        if (u.getNoseTertiaryForestFloor()) {
            ids.add(WSETTertiary.FOREST_FLOOR);
        }
        if (u.getNoseTertiaryCaramel()) {
            ids.add(WSETTertiary.CARAMEL);
        }
        if (u.getNoseTertiaryOrangeMarmalade()) {
            ids.add(WSETTertiary.ORANGE_MARMALADE);
        }
        if (u.getNoseTertiaryPetrol()) {
            ids.add(WSETTertiary.PETROL);
        }
        if (u.getNoseTertiaryCinnamon()) {
            ids.add(WSETTertiary.CINNAMON);
        }
        if (u.getNoseTertiaryGinger()) {
            ids.add(WSETTertiary.GINGER);
        }
        if (u.getNoseTertiaryNutmeg()) {
            ids.add(WSETTertiary.NUTMEG);
        }
        if (u.getNoseTertiaryAlmond()) {
            ids.add(WSETTertiary.ALMOND);
        }
        if (u.getNoseTertiaryHazelnut()) {
            ids.add(WSETTertiary.HAZELNUT);
        }
        if (u.getNoseTertiaryHoney()) {
            ids.add(WSETTertiary.HONEY);
        }

        if (w.getNoseTertiary() != null) {
            for (WSETTertiary f : w.getNoseTertiary()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETTertiary> s = w.getNoseTertiary();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETTertiary.class, id));
        }

        w.setNoseTertiary(s);
    }

    private void processNoseOxidised(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getNoseOxidisedAlmond()) {
            ids.add(WSETOxidised.ALMOND);
        }
        if (u.getNoseOxidisedHazelnut()) {
            ids.add(WSETOxidised.HAZELNUT);
        }
        if (u.getNoseOxidisedWalnut()) {
            ids.add(WSETOxidised.WALNUT);
        }
        if (u.getNoseOxidisedChocolate()) {
            ids.add(WSETOxidised.CHOCOLATE);
        }
        if (u.getNoseOxidisedCoffee()) {
            ids.add(WSETOxidised.COFFEE);
        }
        if (u.getNoseOxidisedCaramel()) {
            ids.add(WSETOxidised.CARAMEL);
        }

        if (w.getNoseOxidised() != null) {
            for (WSETOxidised f : w.getNoseOxidised()) {
                if (!ids.contains(f.getId())) {
                    f.getNose().remove(w);
                }
            }
        }

        Set<WSETOxidised> s = w.getNoseOxidised();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETOxidised.class, id));
        }

        w.setNoseOxidised(s);
    }

    // PALATE
    private void processPalateFloral(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateFloralBlossom()) {
            ids.add(WSETFloral.BLOSSOM);
        }
        if (u.getPalateFloralElderflower()) {
            ids.add(WSETFloral.ELDERFLOWER);
        }
        if (u.getPalateFloralHoneysuckle()) {
            ids.add(WSETFloral.HONEYSUCKLE);
        }
        if (u.getPalateFloralJasmine()) {
            ids.add(WSETFloral.JASMINE);
        }
        if (u.getPalateFloralRose()) {
            ids.add(WSETFloral.ROSE);
        }
        if (u.getPalateFloralViolet()) {
            ids.add(WSETFloral.VIOLET);
        }

        if (w.getPalateFloral() != null) {
            for (WSETFloral f : w.getPalateFloral()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETFloral> s = w.getPalateFloral();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETFloral.class, id));
        }

        w.setPalateFloral(s);
    }

    private void processPalateGreenFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateGreenFruitApple()) {
            ids.add(WSETGreenFruit.APPLE);
        }
        if (u.getPalateGreenFruitPear()) {
            ids.add(WSETGreenFruit.PEAR);
        }
        if (u.getPalateGreenFruitGooseberry()) {
            ids.add(WSETGreenFruit.GOOSEBERRY);
        }
        if (u.getPalateGreenFruitGrape()) {
            ids.add(WSETGreenFruit.GRAPE);
        }

        if (w.getPalateGreenFruit() != null) {
            for (WSETGreenFruit f : w.getPalateGreenFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETGreenFruit> s = w.getPalateGreenFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETGreenFruit.class, id));
        }

        w.setPalateGreenFruit(s);
    }

    private void processPalateCitrusFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateCitrusFruitGrapefruit()) {
            ids.add(WSETCitrusFruit.GRAPEFRUIT);
        }
        if (u.getPalateCitrusFruitLemon()) {
            ids.add(WSETCitrusFruit.LEMON);
        }
        if (u.getPalateCitrusFruitLime()) {
            ids.add(WSETCitrusFruit.LIME);
        }
        if (u.getPalateCitrusFruitOrange()) {
            ids.add(WSETCitrusFruit.ORANGE);
        }

        if (w.getPalateCitrusFruit() != null) {
            for (WSETCitrusFruit f : w.getPalateCitrusFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETCitrusFruit> s = w.getPalateCitrusFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETCitrusFruit.class, id));
        }

        w.setPalateCitrusFruit(s);
    }

    private void processPalateStoneFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateStoneFruitPeach()) {
            ids.add(WSETStoneFruit.PEACH);
        }
        if (u.getPalateStoneFruitApricot()) {
            ids.add(WSETStoneFruit.APRICOT);
        }
        if (u.getPalateStoneFruitNectarine()) {
            ids.add(WSETStoneFruit.NECTARINE);
        }

        if (w.getPalateStoneFruit() != null) {
            for (WSETStoneFruit f : w.getPalateStoneFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETStoneFruit> s = w.getPalateStoneFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETStoneFruit.class, id));
        }

        w.setPalateStoneFruit(s);
    }

    private void processPalateTropicalFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateTropicalFruitBanana()) {
            ids.add(WSETTropicalFruit.BANANA);
        }
        if (u.getPalateTropicalFruitLychee()) {
            ids.add(WSETTropicalFruit.LYCHEE);
        }
        if (u.getPalateTropicalFruitMango()) {
            ids.add(WSETTropicalFruit.MANGO);
        }
        if (u.getPalateTropicalFruitMelon()) {
            ids.add(WSETTropicalFruit.MELON);
        }
        if (u.getPalateTropicalFruitPassionFruit()) {
            ids.add(WSETTropicalFruit.PASSION_FRUIT);
        }
        if (u.getPalateTropicalFruitPineapple()) {
            ids.add(WSETTropicalFruit.PINEAPPLE);
        }

        if (w.getPalateTropicalFruit() != null) {
            for (WSETTropicalFruit f : w.getPalateTropicalFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETTropicalFruit> s = w.getPalateTropicalFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETTropicalFruit.class, id));
        }

        w.setPalateTropicalFruit(s);
    }

    private void processPalateRedFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateRedFruitRedcurrant()) {
            ids.add(WSETRedFruit.REDCURRANT);
        }
        if (u.getPalateRedFruitCranberry()) {
            ids.add(WSETRedFruit.CRANBERRY);
        }
        if (u.getPalateRedFruitRaspberry()) {
            ids.add(WSETRedFruit.RASPBERRY);
        }
        if (u.getPalateRedFruitStrawberry()) {
            ids.add(WSETRedFruit.STRAWBERRY);
        }
        if (u.getPalateRedFruitRedCherry()) {
            ids.add(WSETRedFruit.RED_CHERRY);
        }
        if (u.getPalateRedFruitRedPlum()) {
            ids.add(WSETRedFruit.RED_PLUM);
        }

        if (w.getPalateRedFruit() != null) {
            for (WSETRedFruit f : w.getPalateRedFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETRedFruit> s = w.getPalateRedFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETRedFruit.class, id));
        }

        w.setPalateRedFruit(s);
    }

    private void processPalateBlackFruit(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateBlackFruitBlackcurrant()) {
            ids.add(WSETBlackFruit.BLACKCURRANT);
        }
        if (u.getPalateBlackFruitBlackberry()) {
            ids.add(WSETBlackFruit.BLACKBERRY);
        }
        if (u.getPalateBlackFruitBlueberry()) {
            ids.add(WSETBlackFruit.BLUEBERRY);
        }
        if (u.getPalateBlackFruitBlackCherry()) {
            ids.add(WSETBlackFruit.BLACK_CHERRY);
        }
        if (u.getPalateBlackFruitBlackPlum()) {
            ids.add(WSETBlackFruit.BLACK_PLUM);
        }

        if (w.getPalateBlackFruit() != null) {
            for (WSETBlackFruit f : w.getPalateBlackFruit()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETBlackFruit> s = w.getPalateBlackFruit();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETBlackFruit.class, id));
        }

        w.setPalateBlackFruit(s);
    }

    private void processPalateHerbaceous(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateHerbaceousGreenBellPepper()) {
            ids.add(WSETHerbaceous.GREEN_BELL_PEPPER);
        }
        if (u.getPalateHerbaceousGrass()) {
            ids.add(WSETHerbaceous.GRASS);
        }
        if (u.getPalateHerbaceousTomatoLeaf()) {
            ids.add(WSETHerbaceous.TOMATO_LEAF);
        }
        if (u.getPalateHerbaceousAsparagus()) {
            ids.add(WSETHerbaceous.ASPARAGUS);
        }

        if (w.getPalateHerbaceous() != null) {
            for (WSETHerbaceous f : w.getPalateHerbaceous()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETHerbaceous> s = w.getPalateHerbaceous();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETHerbaceous.class, id));
        }

        w.setPalateHerbaceous(s);
    }

    private void processPalateHerbal(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateHerbalEucalyptus()) {
            ids.add(WSETHerbal.EUCALYPTUS);
        }
        if (u.getPalateHerbalMint()) {
            ids.add(WSETHerbal.MINT);
        }
        if (u.getPalateHerbalFennel()) {
            ids.add(WSETHerbal.FENNEL);
        }
        if (u.getPalateHerbalDill()) {
            ids.add(WSETHerbal.DILL);
        }
        if (u.getPalateHerbalDriedHerbs()) {
            ids.add(WSETHerbal.DRIED_HERBS);
        }

        if (w.getPalateHerbal() != null) {
            for (WSETHerbal f : w.getPalateHerbal()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETHerbal> s = w.getPalateHerbal();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETHerbal.class, id));
        }

        w.setPalateHerbal(s);
    }

    private void processPalateSpice(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateSpiceBlackWhitePepper()) {
            ids.add(WSETSpice.BLACK_WHITE_PEPPER);
        }
        if (u.getPalateSpiceLiquorice()) {
            ids.add(WSETSpice.LIQUORICE);
        }
        if (u.getPalateSpiceCinnamon()) {
            ids.add(WSETSpice.CINNAMON);
        }

        if (w.getPalateSpice() != null) {
            for (WSETSpice f : w.getPalateSpice()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETSpice> s = w.getPalateSpice();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETSpice.class, id));
        }

        w.setPalateSpice(s);
    }

    private void processPalateFruitRipeness(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateFruitRipenessUnripeFruit()) {
            ids.add(WSETFruitRipeness.UNRIPE_FRUIT);
        }
        if (u.getPalateFruitRipenessRipeFruit()) {
            ids.add(WSETFruitRipeness.RIPE_FRUIT);
        }
        if (u.getPalateFruitRipenessDriedFruit()) {
            ids.add(WSETFruitRipeness.DRIED_FRUIT);
        }
        if (u.getPalateFruitRipenessCookedFruit()) {
            ids.add(WSETFruitRipeness.COOKED_FRUIT);
        }

        if (w.getPalateFruitRipeness() != null) {
            for (WSETFruitRipeness f : w.getPalateFruitRipeness()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETFruitRipeness> s = w.getPalateFruitRipeness();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETFruitRipeness.class, id));
        }

        w.setPalateFruitRipeness(s);
    }

    private void processPalateYeast(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateYeastBiscuit()) {
            ids.add(WSETYeast.BISCUIT);
        }
        if (u.getPalateYeastPastry()) {
            ids.add(WSETYeast.PASTRY);
        }
        if (u.getPalateYeastBread()) {
            ids.add(WSETYeast.BREAD);
        }
        if (u.getPalateYeastToastedBread()) {
            ids.add(WSETYeast.TOASTED_BREAD);
        }
        if (u.getPalateYeastBreadDough()) {
            ids.add(WSETYeast.BREAD_DOUGH);
        }
        if (u.getPalateYeastBrioche()) {
            ids.add(WSETYeast.BRIOCHE);
        }
        if (u.getPalateYeastCheese()) {
            ids.add(WSETYeast.CHEESE);
        }
        if (u.getPalateYeastYogurt()) {
            ids.add(WSETYeast.YOGURT);
        }
        if (u.getPalateYeastAcetaldehyde()) {
            ids.add(WSETYeast.ACETALDEHYDE);
        }

        if (w.getPalateYeast() != null) {
            for (WSETYeast f : w.getPalateYeast()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETYeast> s = w.getPalateYeast();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETYeast.class, id));
        }

        w.setPalateYeast(s);
    }

    private void processPalateMalo(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateMaloButter()) {
            ids.add(WSETMalo.BUTTER);
        }
        if (u.getPalateMaloCream()) {
            ids.add(WSETMalo.CREAM);
        }
        if (u.getPalateMaloCheese()) {
            ids.add(WSETMalo.CHEESE);
        }

        if (w.getPalateMalo() != null) {
            for (WSETMalo f : w.getPalateMalo()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETMalo> s = w.getPalateMalo();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETMalo.class, id));
        }

        w.setPalateMalo(s);
    }

    private void processPalateOak(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateOakVanilla()) {
            ids.add(WSETOak.VANILLA);
        }
        if (u.getPalateOakCloves()) {
            ids.add(WSETOak.CLOVES);
        }
        if (u.getPalateOakCoconut()) {
            ids.add(WSETOak.COCONUT);
        }
        if (u.getPalateOakCedar()) {
            ids.add(WSETOak.CEDAR);
        }
        if (u.getPalateOakCharredWood()) {
            ids.add(WSETOak.CHARRED_WOOD);
        }
        if (u.getPalateOakSmoke()) {
            ids.add(WSETOak.SMOKE);
        }
        if (u.getPalateOakChocolate()) {
            ids.add(WSETOak.CHOCOLATE);
        }
        if (u.getPalateOakCoffee()) {
            ids.add(WSETOak.COFFEE);
        }

        if (w.getPalateOak() != null) {
            for (WSETOak f : w.getPalateOak()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETOak> s = w.getPalateOak();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETOak.class, id));
        }

        w.setPalateOak(s);
    }

    private void processPalateTertiary(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateTertiaryDriedFruit()) {
            ids.add(WSETTertiary.DRIED_FRUIT);
        }
        if (u.getPalateTertiaryCookedFruit()) {
            ids.add(WSETTertiary.COOKED_FRUIT);
        }
        if (u.getPalateTertiaryLeather()) {
            ids.add(WSETTertiary.LEATHER);
        }
        if (u.getPalateTertiaryEarth()) {
            ids.add(WSETTertiary.EARTH);
        }
        if (u.getPalateTertiaryMushroom()) {
            ids.add(WSETTertiary.MUSHROOM);
        }
        if (u.getPalateTertiaryMeat()) {
            ids.add(WSETTertiary.MEAT);
        }
        if (u.getPalateTertiaryTobacco()) {
            ids.add(WSETTertiary.TOBACCO);
        }
        if (u.getPalateTertiaryWetLeaves()) {
            ids.add(WSETTertiary.WET_LEAVES);
        }
        if (u.getPalateTertiaryForestFloor()) {
            ids.add(WSETTertiary.FOREST_FLOOR);
        }
        if (u.getPalateTertiaryCaramel()) {
            ids.add(WSETTertiary.CARAMEL);
        }
        if (u.getPalateTertiaryOrangeMarmalade()) {
            ids.add(WSETTertiary.ORANGE_MARMALADE);
        }
        if (u.getPalateTertiaryPetrol()) {
            ids.add(WSETTertiary.PETROL);
        }
        if (u.getPalateTertiaryCinnamon()) {
            ids.add(WSETTertiary.CINNAMON);
        }
        if (u.getPalateTertiaryGinger()) {
            ids.add(WSETTertiary.GINGER);
        }
        if (u.getPalateTertiaryNutmeg()) {
            ids.add(WSETTertiary.NUTMEG);
        }
        if (u.getPalateTertiaryAlmond()) {
            ids.add(WSETTertiary.ALMOND);
        }
        if (u.getPalateTertiaryHazelnut()) {
            ids.add(WSETTertiary.HAZELNUT);
        }
        if (u.getPalateTertiaryHoney()) {
            ids.add(WSETTertiary.HONEY);
        }

        if (w.getPalateTertiary() != null) {
            for (WSETTertiary f : w.getPalateTertiary()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETTertiary> s = w.getPalateTertiary();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETTertiary.class, id));
        }

        w.setPalateTertiary(s);
    }

    private void processPalateOxidised(WSET w, WSETDto u) {
        Set<Long> ids = new HashSet<>();

        if (u.getPalateOxidisedAlmond()) {
            ids.add(WSETOxidised.ALMOND);
        }
        if (u.getPalateOxidisedHazelnut()) {
            ids.add(WSETOxidised.HAZELNUT);
        }
        if (u.getPalateOxidisedWalnut()) {
            ids.add(WSETOxidised.WALNUT);
        }
        if (u.getPalateOxidisedChocolate()) {
            ids.add(WSETOxidised.CHOCOLATE);
        }
        if (u.getPalateOxidisedCoffee()) {
            ids.add(WSETOxidised.COFFEE);
        }
        if (u.getPalateOxidisedCaramel()) {
            ids.add(WSETOxidised.CARAMEL);
        }

        if (w.getPalateOxidised() != null) {
            for (WSETOxidised f : w.getPalateOxidised()) {
                if (!ids.contains(f.getId())) {
                    f.getPalate().remove(w);
                }
            }
        }

        Set<WSETOxidised> s = w.getPalateOxidised();

        if (s == null) {
            s = new HashSet<>();
        }

        for (Long id : ids) {
            s.add(wsetService.find(WSETOxidised.class, id));
        }

        w.setPalateOxidised(s);
    }
}
