/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.security.model.User;

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

    /**
     * SIGHT
     */
    public static final String SIGHT = "sight";

    /**
     * SIGHT_CLARITY
     */
    public static final String SIGHT_CLARITY = "clarity";

    /**
     * SIGHT_INTENSITY
     */
    public static final String SIGHT_INTENSITY = "intensity";

    /**
     * SIGHT_COLOR
     */
    public static final String SIGHT_COLOR = "color";

    /**
     * SIGHT_RIM
     */
    public static final String SIGHT_RIMVARIATION = "rim";

    /**
     * SIGHT_OTHER
     */
    public static final String SIGHT_OTHER = "other";

    /**
     * NOSE
     */
    public static final String NOSE = "nose";

    /**
     * NOSE_CONDITION
     */
    public static final String NOSE_CONDITION = "condition";

    /**
     * NOSE_INTENSITY
     */
    public static final String NOSE_INTENSITY = "intensity";

    /**
     * NOSE_FRUIT
     */
    public static final String NOSE_FRUIT = "fruit";

    /**
     * NOSE_NON_FRUIT
     */
    public static final String NOSE_NON_FRUIT = "non-fruit";

    /**
     * NOSE_EARTH
     */
    public static final String NOSE_EARTH = "earth";

    /**
     * NOSE_MINERAL
     */
    public static final String NOSE_MINERAL = "mineral";

    /**
     * NOSE_WOOED
     */
    public static final String NOSE_WOOD = "wood";

    /**
     * NOSE_OTHER
     */
    public static final String NOSE_OTHER = "other";


    /**
     * PALATE
     */
    public static final String PALATE = "palate";

    /**
     * PALATE_SWEETNESS
     */
    public static final String PALATE_SWEETNESS = "sweetness";

    /**
     * PALATE_FRUIT
     */
    public static final String PALATE_FRUIT = "fruit";

    /**
     * PALATE_NON_FRUIT
     */
    public static final String PALATE_NON_FRUIT = "non-fruit";

    /**
     * PALATE_EARTH
     */
    public static final String PALATE_EARTH = "earth";

    /**
     * PALATE_MINERAL
     */
    public static final String PALATE_MINERAL = "mineral";

    /**
     * PALATE_WOOD
     */
    public static final String PALATE_WOOD = "wood";

    /**
     * PALATE_TANIN
     */
    public static final String PALATE_TANNIN = "tannin";

    /**
     * PALATE_ACID
     */
    public static final String PALATE_ACID = "acid";

    /**
     * PALATE_ALCOHOL
     */
    public static final String PALATE_ALCOHOL = "alcohol";

    /**
     * PALATE_BODY
     */
    public static final String PALATE_BODY = "body";

    /**
     * PALATE_INTENSITY
     */
    public static final String PALATE_INTENSITY = "intensity";

    /**
     * PALATE_FINISH
     */
    public static final String PALATE_FINISH = "finish";

    /**
     * PALATE_COMPLEXITY
     */
    public static final String PALATE_COMPLEXITY = "complexity";

    /**
     * PALATE_OTHER
     */
    public static final String PALATE_OTHER = "other";


    /**
     * CONCLUSION
     */
    public static final String CONCLUSION = "conclusion";

    /**
     * CONCLUSION_DEVELOPMENT
     */
    public static final String CONCLUSION_DEVELOPMENT = "development";

    /**
     * CONCLUSION_GRAPES
     */
    public static final String CONCLUSION_GRAPES = "grapes";

    /**
     * CONCLUSION_WORLD
     */
    public static final String CONCLUSION_WORLD = "world";

    /**
     * CONCLUSION_CLIMATE
     */
    public static final String CONCLUSION_CLIMATE = "climate";

    /**
     * CONCLUSION_COUNTRIES
     */
    public static final String CONCLUSION_COUNTRIES = "countries";

    /**
     * CONCLUSION_AGE
     */
    public static final String CONCLUSION_AGE = "age";

    /**
     * CONCLUSION_NOTES
     */
    public static final String CONCLUSION_NOTES = "notes";

    /**
     * CONCLUSION_OTHER
     */
    public static final String CONCLUSION_OTHER = "other";

    /**
     * Default constructor
     */
    public GenericTastingNotes() {
        super();
    }

    /**
     * GenericTastingNotes constructor
     *
     * @param sight      sight
     * @param nose       nose
     * @param palate     palate
     * @param conclusion conclusion
     * @param show       show
     * @param date       date
     * @param user       user
     * @param wine       wine
     */
    public GenericTastingNotes(Map<String, List<String>> sight, Map<String, List<String>> nose,
                               Map<String, List<String>> palate, Map<String, List<String>> conclusion,
                               Boolean show, Date date, User user, Wine wine) {
        super();
        this.data = new HashMap<>();

        this.data.put(SIGHT, new JsonObject(sight));
        this.data.put(NOSE, new JsonObject(nose));
        this.data.put(PALATE, new JsonObject(palate));
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
    @Type(type = "info.mywinecellar.hibernate.JSONB")
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
