/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grape_component")
public class GrapeComponent extends BaseEntity implements Comparable<GrapeComponent> {

    /**
     * Default constructor
     */
    public GrapeComponent() {
        super();
    }

    /**
     * GrapeComponent constructor
     *
     * @param percentage   percentage
     * @param harvestBegin harvestBegin
     * @param harvestEnd   harvestEnd
     * @param grape        grape
     * @param wine         wine
     */
    public GrapeComponent(Byte percentage, Date harvestBegin, Date harvestEnd, Grape grape, Wine wine) {
        super();
        this.percentage = percentage;
        this.harvestBegin = harvestBegin;
        this.harvestEnd = harvestEnd;
        this.grape = grape;
        this.wine = wine;
    }

    @NotNull
    @Column(name = "percentage")
    private Byte percentage;

    @Column(name = "harvest_begin")
    private Date harvestBegin;

    @Column(name = "harvest_end")
    private Date harvestEnd;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "grape_id", referencedColumnName = "id")
    private Grape grape;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maceration_id", referencedColumnName = "id")
    private Maceration maceration;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fermentation_id", referencedColumnName = "id")
    private Fermentation fermentation;

    @OneToMany(mappedBy = "grapeComponent", cascade = CascadeType.REMOVE)
    private Set<BarrelComponent> barrelComponents;

    @Override
    public int compareTo(GrapeComponent gc) {
        int result = percentage.compareTo(gc.getPercentage());

        if (result == 0) {
            return grape.getName().compareTo(gc.getGrape().getName());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GrapeComponent)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "GrapeComponent(" + id + ")";
    }
}
