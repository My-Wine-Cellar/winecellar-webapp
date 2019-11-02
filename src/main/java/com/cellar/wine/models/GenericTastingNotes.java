package com.cellar.wine.models;

import com.cellar.wine.security.model.User;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.github.cliftonlabs.json_simple.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    public GenericTastingNotes(Map<String, List<String>> sight, Map<String, List<String>> nose,
                               Map<String, List<String>> palette, Map<String, List<String>> conclusion,
                               Boolean show, Date date, User user, Wine wine) {
        super();
        this.data = new HashMap<String, Serializable>();

        // Do we need com.github.cliftonlabs.json_simple.JsonArray here for the values ?
        this.data.put(SIGHT, new JsonObject(sight));
        this.data.put(NOSE, new JsonObject(nose));
        this.data.put(PALETTE, new JsonObject(palette));
        this.data.put(CONCLUSION, new JsonObject(conclusion));

        this.show = show;
        this.date = date;
        this.user = user;
        this.wine = wine;
    }

    @NotNull
    @Column(name = "show")
    private Boolean show;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @NotNull
    @Column(name = "data")
    @Type(type = "com.cellar.wine.hibernate.JSONB")
    private Map<String, Serializable> data;

    @Override
    public int compareTo(GenericTastingNotes gtn) {
        return getId().compareTo(gtn.getId());
    }

    @Override
    public String toString() {
        return "GenericTastingNotes(" + id + ")";
    }
}
