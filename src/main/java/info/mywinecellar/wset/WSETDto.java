/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import com.github.cliftonlabs.json_simple.JsonArray;
import lombok.Data;

/**
 * DTO for WSET
 */
@Data
public class WSETDto implements Serializable {

    private Long id;
    private Boolean show;
    private Date date;

    private Long wineId;
    private Integer userId;

    private Long appearanceIntensity = 1L;
    private Long appearanceColor = 1L;
    private Long noseIntensity = 1L;
    private Long palateSweetness = 1L;
    private Long palateAcidity = 1L;
    private Long palateTanninLevel = 1L;
    private Long palateTanninNature = 1L;
    private Long palateAlcohol = 1L;
    private Long palateBody = 1L;
    private Long palateIntensity = 1L;
    private Long palateFinish = 1L;
    private Long conclusionQuality = 1L;
    private String conclusionQualityExplanation = "";
    private Long conclusionBottle = 1L;
    private String conclusionBottleExplanation = "";

    /* Nose */
    private Boolean noseFloralBlossom = Boolean.FALSE;
    private Boolean noseFloralElderflower = Boolean.FALSE;
    private Boolean noseFloralHoneysuckle = Boolean.FALSE;
    private Boolean noseFloralJasmine = Boolean.FALSE;
    private Boolean noseFloralRose = Boolean.FALSE;
    private Boolean noseFloralViolet = Boolean.FALSE;
    private Boolean noseGreenFruitApple = Boolean.FALSE;
    private Boolean noseGreenFruitPear = Boolean.FALSE;
    private Boolean noseGreenFruitGooseberry = Boolean.FALSE;
    private Boolean noseGreenFruitGrape = Boolean.FALSE;
    private Boolean noseCitrusFruitGrapefruit = Boolean.FALSE;
    private Boolean noseCitrusFruitLemon = Boolean.FALSE;
    private Boolean noseCitrusFruitLime = Boolean.FALSE;
    private Boolean noseCitrusFruitOrange = Boolean.FALSE;
    private Boolean noseStoneFruitPeach = Boolean.FALSE;
    private Boolean noseStoneFruitApricot = Boolean.FALSE;
    private Boolean noseStoneFruitNectarine = Boolean.FALSE;
    private Boolean noseTropicalFruitBanana = Boolean.FALSE;
    private Boolean noseTropicalFruitLychee = Boolean.FALSE;
    private Boolean noseTropicalFruitMango = Boolean.FALSE;
    private Boolean noseTropicalFruitMelon = Boolean.FALSE;
    private Boolean noseTropicalFruitPassionFruit = Boolean.FALSE;
    private Boolean noseTropicalFruitPineapple = Boolean.FALSE;
    private Boolean noseRedFruitRedcurrant = Boolean.FALSE;
    private Boolean noseRedFruitCranberry = Boolean.FALSE;
    private Boolean noseRedFruitRaspberry = Boolean.FALSE;
    private Boolean noseRedFruitStrawberry = Boolean.FALSE;
    private Boolean noseRedFruitRedCherry = Boolean.FALSE;
    private Boolean noseRedFruitRedPlum = Boolean.FALSE;
    private Boolean noseBlackFruitBlackcurrant = Boolean.FALSE;
    private Boolean noseBlackFruitBlackberry = Boolean.FALSE;
    private Boolean noseBlackFruitBlueberry = Boolean.FALSE;
    private Boolean noseBlackFruitBlackCherry = Boolean.FALSE;
    private Boolean noseBlackFruitBlackPlum = Boolean.FALSE;
    private Boolean noseHerbaceousGreenBellPepper = Boolean.FALSE;
    private Boolean noseHerbaceousGrass = Boolean.FALSE;
    private Boolean noseHerbaceousTomatoLeaf = Boolean.FALSE;
    private Boolean noseHerbaceousAsparagus = Boolean.FALSE;
    private Boolean noseHerbalEucalyptus = Boolean.FALSE;
    private Boolean noseHerbalMint = Boolean.FALSE;
    private Boolean noseHerbalFennel = Boolean.FALSE;
    private Boolean noseHerbalDill = Boolean.FALSE;
    private Boolean noseHerbalDriedHerbs = Boolean.FALSE;
    private Boolean noseSpiceBlackWhitePepper = Boolean.FALSE;
    private Boolean noseSpiceLiquorice = Boolean.FALSE;
    private Boolean noseSpiceCinnamon = Boolean.FALSE;
    private Boolean noseFruitRipenessUnripeFruit = Boolean.FALSE;
    private Boolean noseFruitRipenessRipeFruit = Boolean.FALSE;
    private Boolean noseFruitRipenessDriedFruit = Boolean.FALSE;
    private Boolean noseFruitRipenessCookedFruit = Boolean.FALSE;
    private Boolean noseYeastBiscuit = Boolean.FALSE;
    private Boolean noseYeastPastry = Boolean.FALSE;
    private Boolean noseYeastBread = Boolean.FALSE;
    private Boolean noseYeastToastedBread = Boolean.FALSE;
    private Boolean noseYeastBreadDough = Boolean.FALSE;
    private Boolean noseYeastCheese = Boolean.FALSE;
    private Boolean noseYeastYogurt = Boolean.FALSE;
    private Boolean noseYeastAcetaldehyde = Boolean.FALSE;
    private Boolean noseMaloButter = Boolean.FALSE;
    private Boolean noseMaloCream = Boolean.FALSE;
    private Boolean noseMaloCheese = Boolean.FALSE;
    private Boolean noseOakVanilla = Boolean.FALSE;
    private Boolean noseOakCloves = Boolean.FALSE;
    private Boolean noseOakCoconut = Boolean.FALSE;
    private Boolean noseOakCedar = Boolean.FALSE;
    private Boolean noseOakCharredWood = Boolean.FALSE;
    private Boolean noseOakSmoke = Boolean.FALSE;
    private Boolean noseOakChocolate = Boolean.FALSE;
    private Boolean noseOakCoffee = Boolean.FALSE;
    private Boolean noseTertiaryDriedFruit = Boolean.FALSE;
    private Boolean noseTertiaryCookedFruit = Boolean.FALSE;
    private Boolean noseTertiaryLeather = Boolean.FALSE;
    private Boolean noseTertiaryEarth = Boolean.FALSE;
    private Boolean noseTertiaryMushroom = Boolean.FALSE;
    private Boolean noseTertiaryMeat = Boolean.FALSE;
    private Boolean noseTertiaryTobacco = Boolean.FALSE;
    private Boolean noseTertiaryWetLeaves = Boolean.FALSE;
    private Boolean noseTertiaryForestFloor = Boolean.FALSE;
    private Boolean noseTertiaryCaramel = Boolean.FALSE;
    private Boolean noseTertiaryOrangeMarmalade = Boolean.FALSE;
    private Boolean noseTertiaryPetrol = Boolean.FALSE;
    private Boolean noseTertiaryCinnamon = Boolean.FALSE;
    private Boolean noseTertiaryGinger = Boolean.FALSE;
    private Boolean noseTertiaryNutmeg = Boolean.FALSE;
    private Boolean noseTertiaryAlmond = Boolean.FALSE;
    private Boolean noseTertiaryHazelnut = Boolean.FALSE;
    private Boolean noseTertiaryHoney = Boolean.FALSE;
    private Boolean noseOxidisedAlmond = Boolean.FALSE;
    private Boolean noseOxidisedHazelnut = Boolean.FALSE;
    private Boolean noseOxidisedWalnut = Boolean.FALSE;
    private Boolean noseOxidisedChocolate = Boolean.FALSE;
    private Boolean noseOxidisedCoffee = Boolean.FALSE;
    private Boolean noseOxidisedCaramel = Boolean.FALSE;

    private String noseOther = "";

    /* Palate */
    private Boolean palateFloralBlossom = Boolean.FALSE;
    private Boolean palateFloralElderflower = Boolean.FALSE;
    private Boolean palateFloralHoneysuckle = Boolean.FALSE;
    private Boolean palateFloralJasmine = Boolean.FALSE;
    private Boolean palateFloralRose = Boolean.FALSE;
    private Boolean palateFloralViolet = Boolean.FALSE;
    private Boolean palateGreenFruitApple = Boolean.FALSE;
    private Boolean palateGreenFruitPear = Boolean.FALSE;
    private Boolean palateGreenFruitGooseberry = Boolean.FALSE;
    private Boolean palateGreenFruitGrape = Boolean.FALSE;
    private Boolean palateCitrusFruitGrapefruit = Boolean.FALSE;
    private Boolean palateCitrusFruitLemon = Boolean.FALSE;
    private Boolean palateCitrusFruitLime = Boolean.FALSE;
    private Boolean palateCitrusFruitOrange = Boolean.FALSE;
    private Boolean palateStoneFruitPeach = Boolean.FALSE;
    private Boolean palateStoneFruitApricot = Boolean.FALSE;
    private Boolean palateStoneFruitNectarine = Boolean.FALSE;
    private Boolean palateTropicalFruitBanana = Boolean.FALSE;
    private Boolean palateTropicalFruitLychee = Boolean.FALSE;
    private Boolean palateTropicalFruitMango = Boolean.FALSE;
    private Boolean palateTropicalFruitMelon = Boolean.FALSE;
    private Boolean palateTropicalFruitPassionFruit = Boolean.FALSE;
    private Boolean palateTropicalFruitPineapple = Boolean.FALSE;
    private Boolean palateRedFruitRedcurrant = Boolean.FALSE;
    private Boolean palateRedFruitCranberry = Boolean.FALSE;
    private Boolean palateRedFruitRaspberry = Boolean.FALSE;
    private Boolean palateRedFruitStrawberry = Boolean.FALSE;
    private Boolean palateRedFruitRedCherry = Boolean.FALSE;
    private Boolean palateRedFruitRedPlum = Boolean.FALSE;
    private Boolean palateBlackFruitBlackcurrant = Boolean.FALSE;
    private Boolean palateBlackFruitBlackberry = Boolean.FALSE;
    private Boolean palateBlackFruitBlueberry = Boolean.FALSE;
    private Boolean palateBlackFruitBlackCherry = Boolean.FALSE;
    private Boolean palateBlackFruitBlackPlum = Boolean.FALSE;
    private Boolean palateHerbaceousGreenBellPepper = Boolean.FALSE;
    private Boolean palateHerbaceousGrass = Boolean.FALSE;
    private Boolean palateHerbaceousTomatoLeaf = Boolean.FALSE;
    private Boolean palateHerbaceousAsparagus = Boolean.FALSE;
    private Boolean palateHerbalEucalyptus = Boolean.FALSE;
    private Boolean palateHerbalMint = Boolean.FALSE;
    private Boolean palateHerbalFennel = Boolean.FALSE;
    private Boolean palateHerbalDill = Boolean.FALSE;
    private Boolean palateHerbalDriedHerbs = Boolean.FALSE;
    private Boolean palateSpiceBlackWhitePepper = Boolean.FALSE;
    private Boolean palateSpiceLiquorice = Boolean.FALSE;
    private Boolean palateSpiceCinnamon = Boolean.FALSE;
    private Boolean palateFruitRipenessUnripeFruit = Boolean.FALSE;
    private Boolean palateFruitRipenessRipeFruit = Boolean.FALSE;
    private Boolean palateFruitRipenessDriedFruit = Boolean.FALSE;
    private Boolean palateFruitRipenessCookedFruit = Boolean.FALSE;
    private Boolean palateYeastBiscuit = Boolean.FALSE;
    private Boolean palateYeastPastry = Boolean.FALSE;
    private Boolean palateYeastBread = Boolean.FALSE;
    private Boolean palateYeastToastedBread = Boolean.FALSE;
    private Boolean palateYeastBreadDough = Boolean.FALSE;
    private Boolean palateYeastCheese = Boolean.FALSE;
    private Boolean palateYeastYogurt = Boolean.FALSE;
    private Boolean palateYeastAcetaldehyde = Boolean.FALSE;
    private Boolean palateMaloButter = Boolean.FALSE;
    private Boolean palateMaloCream = Boolean.FALSE;
    private Boolean palateMaloCheese = Boolean.FALSE;
    private Boolean palateOakVanilla = Boolean.FALSE;
    private Boolean palateOakCloves = Boolean.FALSE;
    private Boolean palateOakCoconut = Boolean.FALSE;
    private Boolean palateOakCedar = Boolean.FALSE;
    private Boolean palateOakCharredWood = Boolean.FALSE;
    private Boolean palateOakSmoke = Boolean.FALSE;
    private Boolean palateOakChocolate = Boolean.FALSE;
    private Boolean palateOakCoffee = Boolean.FALSE;
    private Boolean palateTertiaryDriedFruit = Boolean.FALSE;
    private Boolean palateTertiaryCookedFruit = Boolean.FALSE;
    private Boolean palateTertiaryLeather = Boolean.FALSE;
    private Boolean palateTertiaryEarth = Boolean.FALSE;
    private Boolean palateTertiaryMushroom = Boolean.FALSE;
    private Boolean palateTertiaryMeat = Boolean.FALSE;
    private Boolean palateTertiaryTobacco = Boolean.FALSE;
    private Boolean palateTertiaryWetLeaves = Boolean.FALSE;
    private Boolean palateTertiaryForestFloor = Boolean.FALSE;
    private Boolean palateTertiaryCaramel = Boolean.FALSE;
    private Boolean palateTertiaryOrangeMarmalade = Boolean.FALSE;
    private Boolean palateTertiaryPetrol = Boolean.FALSE;
    private Boolean palateTertiaryCinnamon = Boolean.FALSE;
    private Boolean palateTertiaryGinger = Boolean.FALSE;
    private Boolean palateTertiaryNutmeg = Boolean.FALSE;
    private Boolean palateTertiaryAlmond = Boolean.FALSE;
    private Boolean palateTertiaryHazelnut = Boolean.FALSE;
    private Boolean palateTertiaryHoney = Boolean.FALSE;
    private Boolean palateOxidisedAlmond = Boolean.FALSE;
    private Boolean palateOxidisedHazelnut = Boolean.FALSE;
    private Boolean palateOxidisedWalnut = Boolean.FALSE;
    private Boolean palateOxidisedChocolate = Boolean.FALSE;
    private Boolean palateOxidisedCoffee = Boolean.FALSE;
    private Boolean palateOxidisedCaramel = Boolean.FALSE;

    private String palateOther = "";
    private String palateObservations = "";

    /**
     * Constructor
     */
    public WSETDto() {
    }

    /**
     * Constructor
     * @param w The Wine instance
     * @param u The User instance
     */
    public WSETDto(Wine w, User u) {
        setInfo(w, u);
    }

    /**
     * Constructor
     * @param w The WSET instance
     */
    public WSETDto(WSET w) {
        this.id = w.getId();
        this.date = w.getDate();
        this.show = w.getShow();

        setInfo(w.getWine(), w.getUser());

        processCommon(w);

        processNoseFloral(w);
        processNoseGreenFruit(w);
        processNoseCitrusFruit(w);
        processNoseStoneFruit(w);
        processNoseTropicalFruit(w);
        processNoseRedFruit(w);
        processNoseBlackFruit(w);
        processNoseHerbaceous(w);
        processNoseHerbal(w);
        processNoseSpice(w);
        processNoseFruitRipeness(w);
        processNoseYeast(w);
        processNoseMalo(w);
        processNoseOak(w);
        processNoseTertiary(w);
        processNoseOxidised(w);

        if (w.getOther() != null) {
            StringBuilder sb = new StringBuilder();
            JsonArray value = (JsonArray) w.getOther().get(WSET.NOSE);
            for (int i = 0; i < value.size(); i++) {
                sb.append(value.getString(i));
                if (i < value.size() - 1) {
                    sb.append(", ");
                }
            }
            noseOther = sb.toString();
        } else {
            noseOther = "";
        }

        processPalateFloral(w);
        processPalateGreenFruit(w);
        processPalateCitrusFruit(w);
        processPalateStoneFruit(w);
        processPalateTropicalFruit(w);
        processPalateRedFruit(w);
        processPalateBlackFruit(w);
        processPalateHerbaceous(w);
        processPalateHerbal(w);
        processPalateSpice(w);
        processPalateFruitRipeness(w);
        processPalateYeast(w);
        processPalateMalo(w);
        processPalateOak(w);
        processPalateTertiary(w);
        processPalateOxidised(w);

        if (w.getOther() != null) {
            StringBuilder sb = new StringBuilder();
            JsonArray value = (JsonArray) w.getOther().get(WSET.PALATE);
            for (int i = 0; i < value.size(); i++) {
                sb.append(value.getString(i));
                if (i < value.size() - 1) {
                    sb.append(", ");
                }
            }
            palateOther = sb.toString();

            sb = new StringBuilder();
            value = (JsonArray) w.getOther().get(WSET.OBSERVATIONS);
            for (int i = 0; i < value.size(); i++) {
                sb.append(value.getString(i));
                if (i < value.size() - 1) {
                    sb.append(", ");
                }
            }
            palateObservations = sb.toString();
        } else {
            palateOther = "";
            palateObservations = "";
        }
    }

    private void setInfo(Wine w, User u) {
        this.wineId = w.getId();
        this.userId = u.getId();
    }

    private void processCommon(WSET w) {
        appearanceIntensity = w.getAppearanceIntensity().getId();
        appearanceColor = w.getAppearanceColor().getId();
        noseIntensity = w.getNoseIntensity().getId();
        palateSweetness = w.getPalateSweetness().getId();
        palateAcidity = w.getPalateAcidity().getId();
        palateTanninLevel = w.getPalateTanninLevel().getId();
        palateTanninNature = w.getPalateTanninNature().getId();
        palateAlcohol = w.getPalateAlcohol().getId();
        palateBody = w.getPalateBody().getId();
        palateIntensity = w.getPalateIntensity().getId();
        palateFinish = w.getPalateFinish().getId();
        conclusionQuality = w.getConclusionQuality().getId();
        conclusionQualityExplanation = w.getConclusionQualityExplanation();
        conclusionBottle = w.getConclusionBottle().getId();
        conclusionBottleExplanation = w.getConclusionBottleExplanation();
    }

    private void processNoseFloral(WSET w) {
        Set<WSETFloral> l = w.getNoseFloral();
        if (l != null) {
            for (WSETFloral f : l) {
                if (WSETFloral.BLOSSOM.equals(f.getId())) {
                    noseFloralBlossom = Boolean.TRUE;
                } else if (WSETFloral.ELDERFLOWER.equals(f.getId())) {
                    noseFloralElderflower = Boolean.TRUE;
                } else if (WSETFloral.HONEYSUCKLE.equals(f.getId())) {
                    noseFloralHoneysuckle = Boolean.TRUE;
                } else if (WSETFloral.JASMINE.equals(f.getId())) {
                    noseFloralJasmine = Boolean.TRUE;
                } else if (WSETFloral.ROSE.equals(f.getId())) {
                    noseFloralRose = Boolean.TRUE;
                } else if (WSETFloral.VIOLET.equals(f.getId())) {
                    noseFloralViolet = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseGreenFruit(WSET w) {
        Set<WSETGreenFruit> l = w.getNoseGreenFruit();
        if (l != null) {
            for (WSETGreenFruit f : l) {
                if (WSETGreenFruit.APPLE.equals(f.getId())) {
                    noseGreenFruitApple = Boolean.TRUE;
                } else if (WSETGreenFruit.PEAR.equals(f.getId())) {
                    noseGreenFruitPear = Boolean.TRUE;
                } else if (WSETGreenFruit.GOOSEBERRY.equals(f.getId())) {
                    noseGreenFruitGooseberry = Boolean.TRUE;
                } else if (WSETGreenFruit.GRAPE.equals(f.getId())) {
                    noseGreenFruitGrape = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseCitrusFruit(WSET w) {
        Set<WSETCitrusFruit> l = w.getNoseCitrusFruit();
        if (l != null) {
            for (WSETCitrusFruit f : l) {
                if (WSETCitrusFruit.GRAPEFRUIT.equals(f.getId())) {
                    noseCitrusFruitGrapefruit = Boolean.TRUE;
                } else if (WSETCitrusFruit.LEMON.equals(f.getId())) {
                    noseCitrusFruitLemon = Boolean.TRUE;
                } else if (WSETCitrusFruit.LIME.equals(f.getId())) {
                    noseCitrusFruitLime = Boolean.TRUE;
                } else if (WSETCitrusFruit.ORANGE.equals(f.getId())) {
                    noseCitrusFruitOrange = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseStoneFruit(WSET w) {
        Set<WSETStoneFruit> l = w.getNoseStoneFruit();
        if (l != null) {
            for (WSETStoneFruit f : l) {
                if (WSETStoneFruit.PEACH.equals(f.getId())) {
                    noseStoneFruitPeach = Boolean.TRUE;
                } else if (WSETStoneFruit.APRICOT.equals(f.getId())) {
                    noseStoneFruitApricot = Boolean.TRUE;
                } else if (WSETStoneFruit.NECTARINE.equals(f.getId())) {
                    noseStoneFruitNectarine = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseTropicalFruit(WSET w) {
        Set<WSETTropicalFruit> l = w.getNoseTropicalFruit();
        if (l != null) {
            for (WSETTropicalFruit f : l) {
                if (WSETTropicalFruit.BANANA.equals(f.getId())) {
                    noseTropicalFruitBanana = Boolean.TRUE;
                } else if (WSETTropicalFruit.LYCHEE.equals(f.getId())) {
                    noseTropicalFruitLychee = Boolean.TRUE;
                } else if (WSETTropicalFruit.MANGO.equals(f.getId())) {
                    noseTropicalFruitMango = Boolean.TRUE;
                } else if (WSETTropicalFruit.MELON.equals(f.getId())) {
                    noseTropicalFruitMelon = Boolean.TRUE;
                } else if (WSETTropicalFruit.PASSION_FRUIT.equals(f.getId())) {
                    noseTropicalFruitPassionFruit = Boolean.TRUE;
                } else if (WSETTropicalFruit.PINEAPPLE.equals(f.getId())) {
                    noseTropicalFruitPineapple = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseRedFruit(WSET w) {
        Set<WSETRedFruit> l = w.getNoseRedFruit();
        if (l != null) {
            for (WSETRedFruit f : l) {
                if (WSETRedFruit.REDCURRANT.equals(f.getId())) {
                    noseRedFruitRedcurrant = Boolean.TRUE;
                } else if (WSETRedFruit.CRANBERRY.equals(f.getId())) {
                    noseRedFruitCranberry = Boolean.TRUE;
                } else if (WSETRedFruit.RASPBERRY.equals(f.getId())) {
                    noseRedFruitRaspberry = Boolean.TRUE;
                } else if (WSETRedFruit.STRAWBERRY.equals(f.getId())) {
                    noseRedFruitStrawberry = Boolean.TRUE;
                } else if (WSETRedFruit.RED_CHERRY.equals(f.getId())) {
                    noseRedFruitRedCherry = Boolean.TRUE;
                } else if (WSETRedFruit.RED_PLUM.equals(f.getId())) {
                    noseRedFruitRedPlum = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseBlackFruit(WSET w) {
        Set<WSETBlackFruit> l = w.getNoseBlackFruit();
        if (l != null) {
            for (WSETBlackFruit f : l) {
                if (WSETBlackFruit.BLACKCURRANT.equals(f.getId())) {
                    noseBlackFruitBlackcurrant = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACKBERRY.equals(f.getId())) {
                    noseBlackFruitBlackberry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLUEBERRY.equals(f.getId())) {
                    noseBlackFruitBlueberry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACK_CHERRY.equals(f.getId())) {
                    noseBlackFruitBlackCherry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACK_PLUM.equals(f.getId())) {
                    noseBlackFruitBlackPlum = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseHerbaceous(WSET w) {
        Set<WSETHerbaceous> l = w.getNoseHerbaceous();
        if (l != null) {
            for (WSETHerbaceous f : l) {
                if (WSETHerbaceous.GREEN_BELL_PEPPER.equals(f.getId())) {
                    noseHerbaceousGreenBellPepper = Boolean.TRUE;
                } else if (WSETHerbaceous.GRASS.equals(f.getId())) {
                    noseHerbaceousGrass = Boolean.TRUE;
                } else if (WSETHerbaceous.TOMATO_LEAF.equals(f.getId())) {
                    noseHerbaceousTomatoLeaf = Boolean.TRUE;
                } else if (WSETHerbaceous.ASPARAGUS.equals(f.getId())) {
                    noseHerbaceousAsparagus = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseHerbal(WSET w) {
        Set<WSETHerbal> l = w.getNoseHerbal();
        if (l != null) {
            for (WSETHerbal f : l) {
                if (WSETHerbal.EUCALYPTUS.equals(f.getId())) {
                    noseHerbalEucalyptus = Boolean.TRUE;
                } else if (WSETHerbal.MINT.equals(f.getId())) {
                    noseHerbalMint = Boolean.TRUE;
                } else if (WSETHerbal.FENNEL.equals(f.getId())) {
                    noseHerbalFennel = Boolean.TRUE;
                } else if (WSETHerbal.DILL.equals(f.getId())) {
                    noseHerbalDill = Boolean.TRUE;
                } else if (WSETHerbal.DRIED_HERBS.equals(f.getId())) {
                    noseHerbalDriedHerbs = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseSpice(WSET w) {
        Set<WSETSpice> l = w.getNoseSpice();
        if (l != null) {
            for (WSETSpice f : l) {
                if (WSETSpice.BLACK_WHITE_PEPPER.equals(f.getId())) {
                    noseSpiceBlackWhitePepper = Boolean.TRUE;
                } else if (WSETSpice.LIQUORICE.equals(f.getId())) {
                    noseSpiceLiquorice = Boolean.TRUE;
                } else if (WSETSpice.CINNAMON.equals(f.getId())) {
                    noseSpiceCinnamon = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseFruitRipeness(WSET w) {
        Set<WSETFruitRipeness> l = w.getNoseFruitRipeness();
        if (l != null) {
            for (WSETFruitRipeness f : l) {
                if (WSETFruitRipeness.UNRIPE_FRUIT.equals(f.getId())) {
                    noseFruitRipenessUnripeFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.RIPE_FRUIT.equals(f.getId())) {
                    noseFruitRipenessRipeFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.DRIED_FRUIT.equals(f.getId())) {
                    noseFruitRipenessDriedFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.COOKED_FRUIT.equals(f.getId())) {
                    noseFruitRipenessCookedFruit = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseYeast(WSET w) {
        Set<WSETYeast> l = w.getNoseYeast();
        if (l != null) {
            for (WSETYeast f : l) {
                if (WSETYeast.BISCUIT.equals(f.getId())) {
                    noseYeastBiscuit = Boolean.TRUE;
                } else if (WSETYeast.PASTRY.equals(f.getId())) {
                    noseYeastPastry = Boolean.TRUE;
                } else if (WSETYeast.BREAD.equals(f.getId())) {
                    noseYeastBread = Boolean.TRUE;
                } else if (WSETYeast.TOASTED_BREAD.equals(f.getId())) {
                    noseYeastToastedBread = Boolean.TRUE;
                } else if (WSETYeast.BREAD_DOUGH.equals(f.getId())) {
                    noseYeastBreadDough = Boolean.TRUE;
                } else if (WSETYeast.CHEESE.equals(f.getId())) {
                    noseYeastCheese = Boolean.TRUE;
                } else if (WSETYeast.YOGURT.equals(f.getId())) {
                    noseYeastYogurt = Boolean.TRUE;
                } else if (WSETYeast.ACETALDEHYDE.equals(f.getId())) {
                    noseYeastAcetaldehyde = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseMalo(WSET w) {
        Set<WSETMalo> l = w.getNoseMalo();
        if (l != null) {
            for (WSETMalo f : l) {
                if (WSETMalo.BUTTER.equals(f.getId())) {
                    noseMaloButter = Boolean.TRUE;
                } else if (WSETMalo.CREAM.equals(f.getId())) {
                    noseMaloCream = Boolean.TRUE;
                } else if (WSETMalo.CHEESE.equals(f.getId())) {
                    noseMaloCheese = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseOak(WSET w) {
        Set<WSETOak> l = w.getNoseOak();
        if (l != null) {
            for (WSETOak f : l) {
                if (WSETOak.VANILLA.equals(f.getId())) {
                    noseOakVanilla = Boolean.TRUE;
                } else if (WSETOak.CLOVES.equals(f.getId())) {
                    noseOakCloves = Boolean.TRUE;
                } else if (WSETOak.COCONUT.equals(f.getId())) {
                    noseOakCoconut = Boolean.TRUE;
                } else if (WSETOak.CEDAR.equals(f.getId())) {
                    noseOakCedar = Boolean.TRUE;
                } else if (WSETOak.CHARRED_WOOD.equals(f.getId())) {
                    noseOakCharredWood = Boolean.TRUE;
                } else if (WSETOak.SMOKE.equals(f.getId())) {
                    noseOakSmoke = Boolean.TRUE;
                } else if (WSETOak.CHOCOLATE.equals(f.getId())) {
                    noseOakChocolate = Boolean.TRUE;
                } else if (WSETOak.COFFEE.equals(f.getId())) {
                    noseOakCoffee = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseTertiary(WSET w) {
        Set<WSETTertiary> l = w.getNoseTertiary();
        if (l != null) {
            for (WSETTertiary f : l) {
                if (WSETTertiary.DRIED_FRUIT.equals(f.getId())) {
                    noseTertiaryDriedFruit = Boolean.TRUE;
                } else if (WSETTertiary.COOKED_FRUIT.equals(f.getId())) {
                    noseTertiaryCookedFruit = Boolean.TRUE;
                } else if (WSETTertiary.LEATHER.equals(f.getId())) {
                    noseTertiaryLeather = Boolean.TRUE;
                } else if (WSETTertiary.EARTH.equals(f.getId())) {
                    noseTertiaryEarth = Boolean.TRUE;
                } else if (WSETTertiary.MUSHROOM.equals(f.getId())) {
                    noseTertiaryMushroom = Boolean.TRUE;
                } else if (WSETTertiary.MEAT.equals(f.getId())) {
                    noseTertiaryMeat = Boolean.TRUE;
                } else if (WSETTertiary.TOBACCO.equals(f.getId())) {
                    noseTertiaryTobacco = Boolean.TRUE;
                } else if (WSETTertiary.WET_LEAVES.equals(f.getId())) {
                    noseTertiaryWetLeaves = Boolean.TRUE;
                } else if (WSETTertiary.FOREST_FLOOR.equals(f.getId())) {
                    noseTertiaryForestFloor = Boolean.TRUE;
                } else if (WSETTertiary.CARAMEL.equals(f.getId())) {
                    noseTertiaryCaramel = Boolean.TRUE;
                } else if (WSETTertiary.ORANGE_MARMALADE.equals(f.getId())) {
                    noseTertiaryOrangeMarmalade = Boolean.TRUE;
                } else if (WSETTertiary.PETROL.equals(f.getId())) {
                    noseTertiaryPetrol = Boolean.TRUE;
                } else if (WSETTertiary.CINNAMON.equals(f.getId())) {
                    noseTertiaryCinnamon = Boolean.TRUE;
                } else if (WSETTertiary.GINGER.equals(f.getId())) {
                    noseTertiaryGinger = Boolean.TRUE;
                } else if (WSETTertiary.NUTMEG.equals(f.getId())) {
                    noseTertiaryNutmeg = Boolean.TRUE;
                } else if (WSETTertiary.ALMOND.equals(f.getId())) {
                    noseTertiaryAlmond = Boolean.TRUE;
                } else if (WSETTertiary.HAZELNUT.equals(f.getId())) {
                    noseTertiaryHazelnut = Boolean.TRUE;
                } else if (WSETTertiary.HONEY.equals(f.getId())) {
                    noseTertiaryHoney = Boolean.TRUE;
                }
            }
        }
    }

    private void processNoseOxidised(WSET w) {
        Set<WSETOxidised> l = w.getNoseOxidised();
        if (l != null) {
            for (WSETOxidised f : l) {
                if (WSETOxidised.ALMOND.equals(f.getId())) {
                    noseOxidisedAlmond = Boolean.TRUE;
                } else if (WSETOxidised.HAZELNUT.equals(f.getId())) {
                    noseOxidisedHazelnut = Boolean.TRUE;
                } else if (WSETOxidised.WALNUT.equals(f.getId())) {
                    noseOxidisedWalnut = Boolean.TRUE;
                } else if (WSETOxidised.CHOCOLATE.equals(f.getId())) {
                    noseOxidisedChocolate = Boolean.TRUE;
                } else if (WSETOxidised.COFFEE.equals(f.getId())) {
                    noseOxidisedCoffee = Boolean.TRUE;
                } else if (WSETOxidised.CARAMEL.equals(f.getId())) {
                    noseOxidisedCaramel = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateFloral(WSET w) {
        Set<WSETFloral> l = w.getPalateFloral();
        if (l != null) {
            for (WSETFloral f : l) {
                if (WSETFloral.BLOSSOM.equals(f.getId())) {
                    palateFloralBlossom = Boolean.TRUE;
                } else if (WSETFloral.ELDERFLOWER.equals(f.getId())) {
                    palateFloralElderflower = Boolean.TRUE;
                } else if (WSETFloral.HONEYSUCKLE.equals(f.getId())) {
                    palateFloralHoneysuckle = Boolean.TRUE;
                } else if (WSETFloral.JASMINE.equals(f.getId())) {
                    palateFloralJasmine = Boolean.TRUE;
                } else if (WSETFloral.ROSE.equals(f.getId())) {
                    palateFloralRose = Boolean.TRUE;
                } else if (WSETFloral.VIOLET.equals(f.getId())) {
                    palateFloralViolet = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateGreenFruit(WSET w) {
        Set<WSETGreenFruit> l = w.getPalateGreenFruit();
        if (l != null) {
            for (WSETGreenFruit f : l) {
                if (WSETGreenFruit.APPLE.equals(f.getId())) {
                    palateGreenFruitApple = Boolean.TRUE;
                } else if (WSETGreenFruit.PEAR.equals(f.getId())) {
                    palateGreenFruitPear = Boolean.TRUE;
                } else if (WSETGreenFruit.GOOSEBERRY.equals(f.getId())) {
                    palateGreenFruitGooseberry = Boolean.TRUE;
                } else if (WSETGreenFruit.GRAPE.equals(f.getId())) {
                    palateGreenFruitGrape = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateCitrusFruit(WSET w) {
        Set<WSETCitrusFruit> l = w.getPalateCitrusFruit();
        if (l != null) {
            for (WSETCitrusFruit f : l) {
                if (WSETCitrusFruit.GRAPEFRUIT.equals(f.getId())) {
                    palateCitrusFruitGrapefruit = Boolean.TRUE;
                } else if (WSETCitrusFruit.LEMON.equals(f.getId())) {
                    palateCitrusFruitLemon = Boolean.TRUE;
                } else if (WSETCitrusFruit.LIME.equals(f.getId())) {
                    palateCitrusFruitLime = Boolean.TRUE;
                } else if (WSETCitrusFruit.ORANGE.equals(f.getId())) {
                    palateCitrusFruitOrange = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateStoneFruit(WSET w) {
        Set<WSETStoneFruit> l = w.getPalateStoneFruit();
        if (l != null) {
            for (WSETStoneFruit f : l) {
                if (WSETStoneFruit.PEACH.equals(f.getId())) {
                    palateStoneFruitPeach = Boolean.TRUE;
                } else if (WSETStoneFruit.APRICOT.equals(f.getId())) {
                    palateStoneFruitApricot = Boolean.TRUE;
                } else if (WSETStoneFruit.NECTARINE.equals(f.getId())) {
                    palateStoneFruitNectarine = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateTropicalFruit(WSET w) {
        Set<WSETTropicalFruit> l = w.getPalateTropicalFruit();
        if (l != null) {
            for (WSETTropicalFruit f : l) {
                if (WSETTropicalFruit.BANANA.equals(f.getId())) {
                    palateTropicalFruitBanana = Boolean.TRUE;
                } else if (WSETTropicalFruit.LYCHEE.equals(f.getId())) {
                    palateTropicalFruitLychee = Boolean.TRUE;
                } else if (WSETTropicalFruit.MANGO.equals(f.getId())) {
                    palateTropicalFruitMango = Boolean.TRUE;
                } else if (WSETTropicalFruit.MELON.equals(f.getId())) {
                    palateTropicalFruitMelon = Boolean.TRUE;
                } else if (WSETTropicalFruit.PASSION_FRUIT.equals(f.getId())) {
                    palateTropicalFruitPassionFruit = Boolean.TRUE;
                } else if (WSETTropicalFruit.PINEAPPLE.equals(f.getId())) {
                    palateTropicalFruitPineapple = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateRedFruit(WSET w) {
        Set<WSETRedFruit> l = w.getPalateRedFruit();
        if (l != null) {
            for (WSETRedFruit f : l) {
                if (WSETRedFruit.REDCURRANT.equals(f.getId())) {
                    palateRedFruitRedcurrant = Boolean.TRUE;
                } else if (WSETRedFruit.CRANBERRY.equals(f.getId())) {
                    palateRedFruitCranberry = Boolean.TRUE;
                } else if (WSETRedFruit.RASPBERRY.equals(f.getId())) {
                    palateRedFruitRaspberry = Boolean.TRUE;
                } else if (WSETRedFruit.STRAWBERRY.equals(f.getId())) {
                    palateRedFruitStrawberry = Boolean.TRUE;
                } else if (WSETRedFruit.RED_CHERRY.equals(f.getId())) {
                    palateRedFruitRedCherry = Boolean.TRUE;
                } else if (WSETRedFruit.RED_PLUM.equals(f.getId())) {
                    palateRedFruitRedPlum = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateBlackFruit(WSET w) {
        Set<WSETBlackFruit> l = w.getPalateBlackFruit();
        if (l != null) {
            for (WSETBlackFruit f : l) {
                if (WSETBlackFruit.BLACKCURRANT.equals(f.getId())) {
                    palateBlackFruitBlackcurrant = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACKBERRY.equals(f.getId())) {
                    palateBlackFruitBlackberry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLUEBERRY.equals(f.getId())) {
                    palateBlackFruitBlueberry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACK_CHERRY.equals(f.getId())) {
                    palateBlackFruitBlackCherry = Boolean.TRUE;
                } else if (WSETBlackFruit.BLACK_PLUM.equals(f.getId())) {
                    palateBlackFruitBlackPlum = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateHerbaceous(WSET w) {
        Set<WSETHerbaceous> l = w.getPalateHerbaceous();
        if (l != null) {
            for (WSETHerbaceous f : l) {
                if (WSETHerbaceous.GREEN_BELL_PEPPER.equals(f.getId())) {
                    palateHerbaceousGreenBellPepper = Boolean.TRUE;
                } else if (WSETHerbaceous.GRASS.equals(f.getId())) {
                    palateHerbaceousGrass = Boolean.TRUE;
                } else if (WSETHerbaceous.TOMATO_LEAF.equals(f.getId())) {
                    palateHerbaceousTomatoLeaf = Boolean.TRUE;
                } else if (WSETHerbaceous.ASPARAGUS.equals(f.getId())) {
                    palateHerbaceousAsparagus = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateHerbal(WSET w) {
        Set<WSETHerbal> l = w.getPalateHerbal();
        if (l != null) {
            for (WSETHerbal f : l) {
                if (WSETHerbal.EUCALYPTUS.equals(f.getId())) {
                    palateHerbalEucalyptus = Boolean.TRUE;
                } else if (WSETHerbal.MINT.equals(f.getId())) {
                    palateHerbalMint = Boolean.TRUE;
                } else if (WSETHerbal.FENNEL.equals(f.getId())) {
                    palateHerbalFennel = Boolean.TRUE;
                } else if (WSETHerbal.DILL.equals(f.getId())) {
                    palateHerbalDill = Boolean.TRUE;
                } else if (WSETHerbal.DRIED_HERBS.equals(f.getId())) {
                    palateHerbalDriedHerbs = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateSpice(WSET w) {
        Set<WSETSpice> l = w.getPalateSpice();
        if (l != null) {
            for (WSETSpice f : l) {
                if (WSETSpice.BLACK_WHITE_PEPPER.equals(f.getId())) {
                    palateSpiceBlackWhitePepper = Boolean.TRUE;
                } else if (WSETSpice.LIQUORICE.equals(f.getId())) {
                    palateSpiceLiquorice = Boolean.TRUE;
                } else if (WSETSpice.CINNAMON.equals(f.getId())) {
                    palateSpiceCinnamon = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateFruitRipeness(WSET w) {
        Set<WSETFruitRipeness> l = w.getPalateFruitRipeness();
        if (l != null) {
            for (WSETFruitRipeness f : l) {
                if (WSETFruitRipeness.UNRIPE_FRUIT.equals(f.getId())) {
                    palateFruitRipenessUnripeFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.RIPE_FRUIT.equals(f.getId())) {
                    palateFruitRipenessRipeFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.DRIED_FRUIT.equals(f.getId())) {
                    palateFruitRipenessDriedFruit = Boolean.TRUE;
                } else if (WSETFruitRipeness.COOKED_FRUIT.equals(f.getId())) {
                    palateFruitRipenessCookedFruit = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateYeast(WSET w) {
        Set<WSETYeast> l = w.getPalateYeast();
        if (l != null) {
            for (WSETYeast f : l) {
                if (WSETYeast.BISCUIT.equals(f.getId())) {
                    palateYeastBiscuit = Boolean.TRUE;
                } else if (WSETYeast.PASTRY.equals(f.getId())) {
                    palateYeastPastry = Boolean.TRUE;
                } else if (WSETYeast.BREAD.equals(f.getId())) {
                    palateYeastBread = Boolean.TRUE;
                } else if (WSETYeast.TOASTED_BREAD.equals(f.getId())) {
                    palateYeastToastedBread = Boolean.TRUE;
                } else if (WSETYeast.BREAD_DOUGH.equals(f.getId())) {
                    palateYeastBreadDough = Boolean.TRUE;
                } else if (WSETYeast.CHEESE.equals(f.getId())) {
                    palateYeastCheese = Boolean.TRUE;
                } else if (WSETYeast.YOGURT.equals(f.getId())) {
                    palateYeastYogurt = Boolean.TRUE;
                } else if (WSETYeast.ACETALDEHYDE.equals(f.getId())) {
                    palateYeastAcetaldehyde = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateMalo(WSET w) {
        Set<WSETMalo> l = w.getPalateMalo();
        if (l != null) {
            for (WSETMalo f : l) {
                if (WSETMalo.BUTTER.equals(f.getId())) {
                    palateMaloButter = Boolean.TRUE;
                } else if (WSETMalo.CREAM.equals(f.getId())) {
                    palateMaloCream = Boolean.TRUE;
                } else if (WSETMalo.CHEESE.equals(f.getId())) {
                    palateMaloCheese = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateOak(WSET w) {
        Set<WSETOak> l = w.getPalateOak();
        if (l != null) {
            for (WSETOak f : l) {
                if (WSETOak.VANILLA.equals(f.getId())) {
                    palateOakVanilla = Boolean.TRUE;
                } else if (WSETOak.CLOVES.equals(f.getId())) {
                    palateOakCloves = Boolean.TRUE;
                } else if (WSETOak.COCONUT.equals(f.getId())) {
                    palateOakCoconut = Boolean.TRUE;
                } else if (WSETOak.CEDAR.equals(f.getId())) {
                    palateOakCedar = Boolean.TRUE;
                } else if (WSETOak.CHARRED_WOOD.equals(f.getId())) {
                    palateOakCharredWood = Boolean.TRUE;
                } else if (WSETOak.SMOKE.equals(f.getId())) {
                    palateOakSmoke = Boolean.TRUE;
                } else if (WSETOak.CHOCOLATE.equals(f.getId())) {
                    palateOakChocolate = Boolean.TRUE;
                } else if (WSETOak.COFFEE.equals(f.getId())) {
                    palateOakCoffee = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateTertiary(WSET w) {
        Set<WSETTertiary> l = w.getPalateTertiary();
        if (l != null) {
            for (WSETTertiary f : l) {
                if (WSETTertiary.DRIED_FRUIT.equals(f.getId())) {
                    palateTertiaryDriedFruit = Boolean.TRUE;
                } else if (WSETTertiary.COOKED_FRUIT.equals(f.getId())) {
                    palateTertiaryCookedFruit = Boolean.TRUE;
                } else if (WSETTertiary.LEATHER.equals(f.getId())) {
                    palateTertiaryLeather = Boolean.TRUE;
                } else if (WSETTertiary.EARTH.equals(f.getId())) {
                    palateTertiaryEarth = Boolean.TRUE;
                } else if (WSETTertiary.MUSHROOM.equals(f.getId())) {
                    palateTertiaryMushroom = Boolean.TRUE;
                } else if (WSETTertiary.MEAT.equals(f.getId())) {
                    palateTertiaryMeat = Boolean.TRUE;
                } else if (WSETTertiary.TOBACCO.equals(f.getId())) {
                    palateTertiaryTobacco = Boolean.TRUE;
                } else if (WSETTertiary.WET_LEAVES.equals(f.getId())) {
                    palateTertiaryWetLeaves = Boolean.TRUE;
                } else if (WSETTertiary.FOREST_FLOOR.equals(f.getId())) {
                    palateTertiaryForestFloor = Boolean.TRUE;
                } else if (WSETTertiary.CARAMEL.equals(f.getId())) {
                    palateTertiaryCaramel = Boolean.TRUE;
                } else if (WSETTertiary.ORANGE_MARMALADE.equals(f.getId())) {
                    palateTertiaryOrangeMarmalade = Boolean.TRUE;
                } else if (WSETTertiary.PETROL.equals(f.getId())) {
                    palateTertiaryPetrol = Boolean.TRUE;
                } else if (WSETTertiary.CINNAMON.equals(f.getId())) {
                    palateTertiaryCinnamon = Boolean.TRUE;
                } else if (WSETTertiary.GINGER.equals(f.getId())) {
                    palateTertiaryGinger = Boolean.TRUE;
                } else if (WSETTertiary.NUTMEG.equals(f.getId())) {
                    palateTertiaryNutmeg = Boolean.TRUE;
                } else if (WSETTertiary.ALMOND.equals(f.getId())) {
                    palateTertiaryAlmond = Boolean.TRUE;
                } else if (WSETTertiary.HAZELNUT.equals(f.getId())) {
                    palateTertiaryHazelnut = Boolean.TRUE;
                } else if (WSETTertiary.HONEY.equals(f.getId())) {
                    palateTertiaryHoney = Boolean.TRUE;
                }
            }
        }
    }

    private void processPalateOxidised(WSET w) {
        Set<WSETOxidised> l = w.getPalateOxidised();
        if (l != null) {
            for (WSETOxidised f : l) {
                if (WSETOxidised.ALMOND.equals(f.getId())) {
                    palateOxidisedAlmond = Boolean.TRUE;
                } else if (WSETOxidised.HAZELNUT.equals(f.getId())) {
                    palateOxidisedHazelnut = Boolean.TRUE;
                } else if (WSETOxidised.WALNUT.equals(f.getId())) {
                    palateOxidisedWalnut = Boolean.TRUE;
                } else if (WSETOxidised.CHOCOLATE.equals(f.getId())) {
                    palateOxidisedChocolate = Boolean.TRUE;
                } else if (WSETOxidised.COFFEE.equals(f.getId())) {
                    palateOxidisedCoffee = Boolean.TRUE;
                } else if (WSETOxidised.CARAMEL.equals(f.getId())) {
                    palateOxidisedCaramel = Boolean.TRUE;
                }
            }
        }
    }
}
