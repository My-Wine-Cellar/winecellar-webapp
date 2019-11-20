/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.GenericTastingNotes;
import info.mywinecellar.util.StringJsonKey;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.github.cliftonlabs.json_simple.JsonObject;
import lombok.Data;

/**
 * UI for generic tasting notes
 */
@Data
public class TastingNotesUI implements Serializable {

    private CountryUI country;
    private RegionUI region;
    private AreaUI area;
    private ProducerUI producer;
    private WineUI wine;
    private UserUI user;

    private Long id;
    private Boolean show;
    private Date date;
    private Long wineId;

    private String sightClarity;
    private String sightIntensity;
    private String sightColor;
    private String sightRimvariation;
    private String sightOther;

    private String noseCondition;
    private String noseIntensity;
    private String noseFruit;
    private String noseNonFruit;
    private String noseEarth;
    private String noseMineral;
    private String noseWood;
    private String noseOther;

    private String palateSweetness;
    private String palateFruit;
    private String palateNonFruit;
    private String palateEarth;
    private String palateMineral;
    private String palateWood;
    private String palateTannin;
    private String palateAcid;
    private String palateAlcohol;
    private String palateBody;
    private String palateIntensity;
    private String palateFinish;
    private String palateComplexity;
    private String palateOther;

    private String conclusionDevelopment;
    private String conclusionGrapes;
    private String conclusionWorld;
    private String conclusionClimate;
    private String conclusionCountries;
    private String conclusionAge;
    private String conclusionNotes;
    private String conclusionOther;


    /**
     * Default constructor
     */
    public TastingNotesUI() {
       // Empty
    }

    /**
     * Constructor
     * @param gtn The generic tasting notes
     */
    TastingNotesUI(GenericTastingNotes gtn) {
        this.country = new CountryUI(gtn.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionUI(gtn.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaUI(gtn.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerUI(gtn.getWine().getProducer());
        this.wine = new WineUI(gtn.getWine());
        this.user = new UserUI(gtn.getUser());

        this.id = gtn.getId();
        this.date = gtn.getDate();
        this.show = gtn.getShow();
        this.wineId = gtn.getWine().getId();

        JsonObject json = new JsonObject(gtn.getData());

        // SIGHT
        JsonObject sight = new JsonObject(json.getMap(new StringJsonKey(GenericTastingNotes.SIGHT)));
        sightClarity = getData(sight, GenericTastingNotes.SIGHT_CLARITY);
        sightColor = getData(sight, GenericTastingNotes.SIGHT_COLOR);
        sightIntensity = getData(sight, GenericTastingNotes.SIGHT_INTENSITY);
        sightRimvariation = getData(sight, GenericTastingNotes.SIGHT_RIMVARIATION);
        sightOther = getData(sight, GenericTastingNotes.SIGHT_OTHER);

        //NOSE
        JsonObject nose = new JsonObject(json.getMap(new StringJsonKey(GenericTastingNotes.NOSE)));
        noseCondition = getData(nose, GenericTastingNotes.NOSE_CONDITION);
        noseEarth = getData(nose, GenericTastingNotes.NOSE_EARTH);
        noseFruit = getData(nose, GenericTastingNotes.NOSE_FRUIT);
        noseIntensity = getData(nose, GenericTastingNotes.NOSE_INTENSITY);
        noseMineral = getData(nose, GenericTastingNotes.NOSE_MINERAL);
        noseNonFruit = getData(nose, GenericTastingNotes.NOSE_NON_FRUIT);
        noseOther = getData(nose, GenericTastingNotes.NOSE_OTHER);
        noseWood = getData(nose, GenericTastingNotes.NOSE_WOOD);

        //PALATE
        JsonObject palate = new JsonObject(json.getMap(new StringJsonKey(GenericTastingNotes.PALATE)));
        palateAcid = getData(palate, GenericTastingNotes.PALATE_ACID);
        palateAlcohol = getData(palate, GenericTastingNotes.PALATE_ALCOHOL);
        palateBody = getData(palate, GenericTastingNotes.PALATE_BODY);
        palateComplexity = getData(palate, GenericTastingNotes.PALATE_COMPLEXITY);
        palateEarth = getData(palate, GenericTastingNotes.PALATE_EARTH);
        palateFinish = getData(palate, GenericTastingNotes.PALATE_FINISH);
        palateFruit = getData(palate, GenericTastingNotes.PALATE_FRUIT);
        palateIntensity = getData(palate, GenericTastingNotes.PALATE_INTENSITY);
        palateMineral = getData(palate, GenericTastingNotes.PALATE_MINERAL);
        palateNonFruit = getData(palate, GenericTastingNotes.PALATE_NON_FRUIT);
        palateOther = getData(palate, GenericTastingNotes.PALATE_OTHER);
        palateSweetness = getData(palate, GenericTastingNotes.PALATE_SWEETNESS);
        palateTannin = getData(palate, GenericTastingNotes.PALATE_TANNIN);
        palateWood = getData(palate, GenericTastingNotes.PALATE_WOOD);

        //CONCLUSION
        JsonObject conclusion = new JsonObject(json.getMap(new StringJsonKey(GenericTastingNotes.CONCLUSION)));
        conclusionAge = getData(conclusion, GenericTastingNotes.CONCLUSION_AGE);
        conclusionClimate = getData(conclusion, GenericTastingNotes.CONCLUSION_CLIMATE);
        conclusionCountries = getData(conclusion, GenericTastingNotes.CONCLUSION_COUNTRIES);
        conclusionDevelopment = getData(conclusion, GenericTastingNotes.CONCLUSION_DEVELOPMENT);
        conclusionGrapes = getData(conclusion, GenericTastingNotes.CONCLUSION_GRAPES);
        conclusionNotes = getData(conclusion, GenericTastingNotes.CONCLUSION_NOTES);
        conclusionOther = getData(conclusion, GenericTastingNotes.CONCLUSION_OTHER);
        conclusionWorld = getData(conclusion, GenericTastingNotes.CONCLUSION_WORLD);
    }

    private List<String> processString(String name) {
        List<String> list = new ArrayList<>();
        if (name != null && !name.equals("")) {
            StringTokenizer st = new StringTokenizer(name, ",");
            while (st.hasMoreTokens()) {
                list.add(st.nextToken().trim());
            }
        }
        return list;
    }

    private String getData(JsonObject jo, String key) {
        StringBuilder sb = new StringBuilder();
        List list = jo.getCollection(new StringJsonKey(key));
        for (int i = 0; i < list.size(); i++) {
            sb = sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb = sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Get the sight notes
     * @return The map
     */
    public Map<String, List<String>> mapSightNotes() {
        Map<String, List<String>> sightMap = new HashMap<>();
        sightMap.put(GenericTastingNotes.SIGHT_CLARITY, processString(sightClarity));
        sightMap.put(GenericTastingNotes.SIGHT_INTENSITY, processString(sightIntensity));
        sightMap.put(GenericTastingNotes.SIGHT_COLOR, processString(sightColor));
        sightMap.put(GenericTastingNotes.SIGHT_RIMVARIATION, processString(sightRimvariation));
        sightMap.put(GenericTastingNotes.SIGHT_OTHER, processString(sightOther));
        return sightMap;
    }

    /**
     * Get the nose notes
     * @return The map
     */
    public Map<String, List<String>> mapNoseNotes() {
        Map<String, List<String>> noseMap = new HashMap<>();
        noseMap.put(GenericTastingNotes.NOSE_CONDITION, processString(noseCondition));
        noseMap.put(GenericTastingNotes.NOSE_EARTH, processString(noseEarth));
        noseMap.put(GenericTastingNotes.NOSE_FRUIT, processString(noseFruit));
        noseMap.put(GenericTastingNotes.NOSE_NON_FRUIT, processString(noseNonFruit));
        noseMap.put(GenericTastingNotes.NOSE_INTENSITY, processString(noseIntensity));
        noseMap.put(GenericTastingNotes.NOSE_MINERAL, processString(noseMineral));
        noseMap.put(GenericTastingNotes.NOSE_OTHER, processString(noseOther));
        noseMap.put(GenericTastingNotes.NOSE_WOOD, processString(noseWood));
        return noseMap;
    }

    /**
     * Get the palate notes
     * @return The map
     */
    public Map<String, List<String>> mapPalateNotes() {
        Map<String, List<String>> palateMap = new HashMap<>();
        palateMap.put(GenericTastingNotes.PALATE_ACID, processString(palateAcid));
        palateMap.put(GenericTastingNotes.PALATE_ALCOHOL, processString(palateAlcohol));
        palateMap.put(GenericTastingNotes.PALATE_BODY, processString(palateBody));
        palateMap.put(GenericTastingNotes.PALATE_COMPLEXITY, processString(palateComplexity));
        palateMap.put(GenericTastingNotes.PALATE_EARTH, processString(palateEarth));
        palateMap.put(GenericTastingNotes.PALATE_FINISH, processString(palateFinish));
        palateMap.put(GenericTastingNotes.PALATE_FRUIT, processString(palateFruit));
        palateMap.put(GenericTastingNotes.PALATE_INTENSITY, processString(palateIntensity));
        palateMap.put(GenericTastingNotes.PALATE_MINERAL, processString(palateMineral));
        palateMap.put(GenericTastingNotes.PALATE_NON_FRUIT, processString(palateNonFruit));
        palateMap.put(GenericTastingNotes.PALATE_OTHER, processString(palateOther));
        palateMap.put(GenericTastingNotes.PALATE_SWEETNESS, processString(palateSweetness));
        palateMap.put(GenericTastingNotes.PALATE_TANNIN, processString(palateTannin));
        palateMap.put(GenericTastingNotes.PALATE_WOOD, processString(palateWood));
        return palateMap;
    }

    /**
     * Get the conclusion notes
     * @return The map
     */
    public Map<String, List<String>> mapConclusionNotes() {
        Map<String, List<String>> conclusionMap = new HashMap<>();
        conclusionMap.put(GenericTastingNotes.CONCLUSION_AGE, processString(conclusionAge));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_CLIMATE, processString(conclusionClimate));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_COUNTRIES, processString(conclusionCountries));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_DEVELOPMENT, processString(conclusionDevelopment));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_GRAPES, processString(conclusionGrapes));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_NOTES, processString(conclusionNotes));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_OTHER, processString(conclusionOther));
        conclusionMap.put(GenericTastingNotes.CONCLUSION_WORLD, processString(conclusionWorld));
        return conclusionMap;
    }
}
