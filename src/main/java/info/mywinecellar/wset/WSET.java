/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;
import info.mywinecellar.model.User;
import info.mywinecellar.model.Wine;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset")
public class WSET extends BaseEntity implements Comparable<WSET> {

    /**
     * NOSE
     */
    public static final String NOSE = "nose";

    /**
     * PALATE
     */
    public static final String PALATE = "palate";

    /**
     * OBSERVATIONS
     */
    public static final String OBSERVATIONS = "observations";

    /**
     * Default constructor
     */
    public WSET() {
        super();
    }

    @NotNull
    @Column(name = "level")
    private Byte level;

    @NotNull
    @Column(name = "show")
    private Boolean show;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_appearance_intensity_id", referencedColumnName = "id")
    private WSETAppearanceIntensity appearanceIntensity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_appearance_color_id", referencedColumnName = "id")
    private WSETAppearanceColor appearanceColor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_nose_intensity_id", referencedColumnName = "id")
    private WSETNoseIntensity noseIntensity;

    /* NOSE: 1st, 2nd, 3rd -- expanded ManyToMany */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_floral",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_floral_id", referencedColumnName = "id")
    )
    private Set<WSETFloral> noseFloral;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_green_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_green_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETGreenFruit> noseGreenFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_citrus_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_citrus_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETCitrusFruit> noseCitrusFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_stone_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_stone_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETStoneFruit> noseStoneFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_tropical_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_tropical_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETTropicalFruit> noseTropicalFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_red_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_red_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETRedFruit> noseRedFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_black_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_black_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETBlackFruit> noseBlackFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_herbaceous",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_herbaceous_id", referencedColumnName = "id")
    )
    private Set<WSETHerbaceous> noseHerbaceous;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_herbal",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_herbal_id", referencedColumnName = "id")
    )
    private Set<WSETHerbal> noseHerbal;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_spice",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_spice_id", referencedColumnName = "id")
    )
    private Set<WSETSpice> noseSpice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_fruit_ripeness",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_fruit_ripeness_id", referencedColumnName = "id")
    )
    private Set<WSETFruitRipeness> noseFruitRipeness;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_yeast",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_yeast_id", referencedColumnName = "id")
    )
    private Set<WSETYeast> noseYeast;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_malo",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_malo_id", referencedColumnName = "id")
    )
    private Set<WSETMalo> noseMalo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_oak",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_oak_id", referencedColumnName = "id")
    )
    private Set<WSETOak> noseOak;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_tertiary",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_tertiary_id", referencedColumnName = "id")
    )
    private Set<WSETTertiary> noseTertiary;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_nose_oxidised",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_oxidised_id", referencedColumnName = "id")
    )
    private Set<WSETOxidised> noseOxidised;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_sweetness_id", referencedColumnName = "id")
    private WSETPalateSweetness palateSweetness;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_acidity_id", referencedColumnName = "id")
    private WSETPalateAcidity palateAcidity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_tannin_level_id", referencedColumnName = "id")
    private WSETPalateTanninLevel palateTanninLevel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_tannin_nature_id", referencedColumnName = "id")
    private WSETPalateTanninNature palateTanninNature;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_alcohol_id", referencedColumnName = "id")
    private WSETPalateAlcohol palateAlcohol;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_body_id", referencedColumnName = "id")
    private WSETPalateBody palateBody;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_intensity_id", referencedColumnName = "id")
    private WSETPalateIntensity palateIntensity;

    /* PALATE: 1st, 2nd, 3rd -- expanded ManyToMany */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_floral",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_floral_id", referencedColumnName = "id")
    )
    private Set<WSETFloral> palateFloral;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_green_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_green_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETGreenFruit> palateGreenFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_citrus_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_citrus_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETCitrusFruit> palateCitrusFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_stone_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_stone_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETStoneFruit> palateStoneFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_tropical_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_tropical_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETTropicalFruit> palateTropicalFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_red_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_red_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETRedFruit> palateRedFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_black_fruit",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_black_fruit_id", referencedColumnName = "id")
    )
    private Set<WSETBlackFruit> palateBlackFruit;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_herbaceous",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_herbaceous_id", referencedColumnName = "id")
    )
    private Set<WSETHerbaceous> palateHerbaceous;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_herbal",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_herbal_id", referencedColumnName = "id")
    )
    private Set<WSETHerbal> palateHerbal;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_spice",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_spice_id", referencedColumnName = "id")
    )
    private Set<WSETSpice> palateSpice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_fruit_ripeness",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_fruit_ripeness_id", referencedColumnName = "id")
    )
    private Set<WSETFruitRipeness> palateFruitRipeness;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_yeast",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_yeast_id", referencedColumnName = "id")
    )
    private Set<WSETYeast> palateYeast;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_malo",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_malo_id", referencedColumnName = "id")
    )
    private Set<WSETMalo> palateMalo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_oak",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_oak_id", referencedColumnName = "id")
    )
    private Set<WSETOak> palateOak;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_tertiary",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_tertiary_id", referencedColumnName = "id")
    )
    private Set<WSETTertiary> palateTertiary;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "wset_wset_palate_oxidised",
            joinColumns =
            @JoinColumn(name = "wset_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "wset_oxidised_id", referencedColumnName = "id")
    )
    private Set<WSETOxidised> palateOxidised;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_palate_finish_id", referencedColumnName = "id")
    private WSETPalateFinish palateFinish;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_conclusion_quality_id", referencedColumnName = "id")
    private WSETConclusionQuality conclusionQuality;

    @Column(name = "conclusion_quality_explanation", length = 8192)
    private String conclusionQualityExplanation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wset_conclusion_bottle_id", referencedColumnName = "id")
    private WSETConclusionBottleAgeing conclusionBottle;

    @Column(name = "conclusion_bottle_explanation", length = 8192)
    private String conclusionBottleExplanation;

    @Column(name = "other")
    @Type(type = "info.mywinecellar.hibernate.JSONB")
    private Map<String, Serializable> other;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(WSET w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSET)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSET(" + id + ")";
    }
}
