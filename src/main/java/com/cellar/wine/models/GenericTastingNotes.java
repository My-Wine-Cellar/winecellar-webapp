package com.cellar.wine.models;

import com.cellar.wine.security.User;

import lombok.*;
import org.hibernate.annotations.Type;
import com.github.cliftonlabs.json_simple.JsonObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class GenericTastingNotes extends BaseEntity implements Comparable<GenericTastingNotes> {

    public static final String SIGHT = "sight";
    public static final String SIGHT_CLARITY = "clarity";
    public static final String SIGHT_INTENSITY = "intensity";
    public static final String SIGHT_COLOR = "color";
    public static final String SIGHT_RIMVARIATION = "rim";
    public static final String SIGHT_OTHER = "other";

    public static final String NOSE = "nose";
    public static final String NOSE_CONDITION = "condition";
    public static final String NOSE_INTENSITY = "intensity";
    public static final String NOSE_FRUIT = "fruit";
    public static final String NOSE_NON_FRUIT = "non-fruit";
    public static final String NOSE_EARTH = "earth";
    public static final String NOSE_MINERAL = "mineral";
    public static final String NOSE_WOOD = "wood";
    public static final String NOSE_OTHER = "other";

    public static final String PALETTE = "palette";
    public static final String PALETTE_SWEETNESS = "sweetness";
    public static final String PALETTE_FRUIT = "fruit";
    public static final String PALETTE_NON_FRUIT = "non-fruit";
    public static final String PALETTE_EARTH = "earth";
    public static final String PALETTE_MINERAL = "mineral";
    public static final String PALETTE_WOOD = "wood";
    public static final String PALETTE_TANNIN = "tannin";
    public static final String PALETTE_ACID = "acid";
    public static final String PALETTE_ALCOHOL = "alcohol";
    public static final String PALETTE_BODY = "body";
    public static final String PALETTE_INTENSITY = "intensity";
    public static final String PALETTE_FINISH = "finish";
    public static final String PALETTE_COMPLEXITY = "complexity";
    public static final String PALETTE_OTHER = "other";

    public static final String CONCLUSION = "conclusion";
    public static final String CONCLUSION_DEVELOPMENT = "development";
    public static final String CONCLUSION_GRAPES = "grapes";
    public static final String CONCLUSION_WORLD = "world";
    public static final String CONCLUSION_CLIMATE = "climate";
    public static final String CONCLUSION_COUNTRIES = "countries";
    public static final String CONCLUSION_AGE = "age";
    public static final String CONCLUSION_NOTES = "notes";
    public static final String CONCLUSION_OTHER = "other";
    
    public GenericTastingNotes() {
        super();
    }

    public GenericTastingNotes(Map<String, Set<String>> sight, Map<String, Set<String>> nose,
                               Map<String, Set<String>> palette, Map<String, Set<String>> conclusion,
                               Boolean show, User user, Wine wine) {
        super();
        this.data = new HashMap<String, Serializable>();

        // Do we need com.github.cliftonlabs.json_simple.JsonArray here for the values ?
        this.data.put(SIGHT, new JsonObject(sight).toJson());
        this.data.put(NOSE, new JsonObject(nose).toJson());
        this.data.put(PALETTE, new JsonObject(palette).toJson());
        this.data.put(CONCLUSION, new JsonObject(conclusion).toJson());

        this.show = show;
        this.user = user;
        this.wine = wine;
    }

    @Column(name = "show")
    private Boolean show;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Column(name = "data")
    @Type(type = "com.cellar.wine.hibernate.JSONB")
    private Map<String, Serializable> data;

    @Override
    public int compareTo(GenericTastingNotes gtn) {
        return getId().compareTo(gtn.getId());
    }
}
