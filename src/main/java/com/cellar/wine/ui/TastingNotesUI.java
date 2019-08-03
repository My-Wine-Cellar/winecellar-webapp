package com.cellar.wine.ui;

import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.utils.StringJsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

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

    private String paletteSweetness;
    private String paletteFruit;
    private String paletteNonFruit;
    private String paletteEarth;
    private String paletteMineral;
    private String paletteWood;
    private String paletteTannin;
    private String paletteAcid;
    private String paletteAlcohol;
    private String paletteBody;
    private String paletteIntensity;
    private String paletteFinish;
    private String paletteComplexity;
    private String paletteOther;

    private String conclusionDevelopment;
    private String conclusionGrapes;
    private String conclusionWorld;
    private String conclusionClimate;
    private String conclusionCountries;
    private String conclusionAge;
    private String conclusionNotes;
    private String conclusionOther;


    public TastingNotesUI() {
       // Empty
    }

    public TastingNotesUI(GenericTastingNotes gtn) {
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

        //PALETTE
        JsonObject palette = new JsonObject(json.getMap(new StringJsonKey(GenericTastingNotes.PALETTE)));
        paletteAcid = getData(palette, GenericTastingNotes.PALETTE_ACID);
        paletteAlcohol = getData(palette, GenericTastingNotes.PALETTE_ALCOHOL);
        paletteBody = getData(palette, GenericTastingNotes.PALETTE_BODY);
        paletteComplexity = getData(palette, GenericTastingNotes.PALETTE_COMPLEXITY);
        paletteEarth = getData(palette, GenericTastingNotes.PALETTE_EARTH);
        paletteFinish = getData(palette, GenericTastingNotes.PALETTE_FINISH);
        paletteFruit = getData(palette, GenericTastingNotes.PALETTE_FRUIT);
        paletteIntensity = getData(palette, GenericTastingNotes.PALETTE_INTENSITY);
        paletteMineral = getData(palette, GenericTastingNotes.PALETTE_MINERAL);
        paletteNonFruit = getData(palette, GenericTastingNotes.PALETTE_NON_FRUIT);
        paletteOther = getData(palette, GenericTastingNotes.PALETTE_OTHER);
        paletteSweetness = getData(palette, GenericTastingNotes.PALETTE_SWEETNESS);
        paletteTannin = getData(palette, GenericTastingNotes.PALETTE_TANNIN);
        paletteWood = getData(palette, GenericTastingNotes.PALETTE_WOOD);

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
            if (i < list.size() - 1)
                sb = sb.append(", ");
        }
        return sb.toString();
    }

    public Map<String, List<String>> mapSightNotes() {
        Map<String, List<String>> sightMap = new HashMap<>();
        sightMap.put(GenericTastingNotes.SIGHT_CLARITY, processString(sightClarity));
        sightMap.put(GenericTastingNotes.SIGHT_INTENSITY, processString(sightIntensity));
        sightMap.put(GenericTastingNotes.SIGHT_COLOR, processString(sightColor));
        sightMap.put(GenericTastingNotes.SIGHT_RIMVARIATION, processString(sightRimvariation));
        sightMap.put(GenericTastingNotes.SIGHT_OTHER, processString(sightOther));
        return sightMap;
    }

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

    public Map<String, List<String>> mapPalleteNotes() {
        Map<String, List<String>> paletteMap = new HashMap<>();
        paletteMap.put(GenericTastingNotes.PALETTE_ACID, processString(paletteAcid));
        paletteMap.put(GenericTastingNotes.PALETTE_ALCOHOL, processString(paletteAlcohol));
        paletteMap.put(GenericTastingNotes.PALETTE_BODY, processString(paletteBody));
        paletteMap.put(GenericTastingNotes.PALETTE_COMPLEXITY, processString(paletteComplexity));
        paletteMap.put(GenericTastingNotes.PALETTE_EARTH, processString(paletteEarth));
        paletteMap.put(GenericTastingNotes.PALETTE_FINISH, processString(paletteFinish));
        paletteMap.put(GenericTastingNotes.PALETTE_FRUIT, processString(paletteFruit));
        paletteMap.put(GenericTastingNotes.PALETTE_INTENSITY, processString(paletteIntensity));
        paletteMap.put(GenericTastingNotes.PALETTE_MINERAL, processString(paletteMineral));
        paletteMap.put(GenericTastingNotes.PALETTE_NON_FRUIT, processString(paletteNonFruit));
        paletteMap.put(GenericTastingNotes.PALETTE_OTHER, processString(paletteOther));
        paletteMap.put(GenericTastingNotes.PALETTE_SWEETNESS, processString(paletteSweetness));
        paletteMap.put(GenericTastingNotes.PALETTE_TANNIN, processString(paletteTannin));
        paletteMap.put(GenericTastingNotes.PALETTE_WOOD, processString(paletteWood));
        return paletteMap;
    }

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
