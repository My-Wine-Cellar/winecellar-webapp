/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "barrel_component")
public class BarrelComponent extends BaseEntity implements Comparable<BarrelComponent> {

    /**
     * Default constructor
     */
    public BarrelComponent() {
        super();
    }

    /**
     * BarrelComponent constructor
     *
     * @param percentage     percentage
     * @param size           size
     * @param aging          aging
     * @param grapeComponent grapeComponent
     * @param barrel         barrel
     */
    public BarrelComponent(Byte percentage, Integer size, Integer aging,
                           GrapeComponent grapeComponent, Barrel barrel) {
        super();
        this.percentage = percentage;
        this.size = size;
        this.aging = aging;
        this.grapeComponent = grapeComponent;
        this.barrel = barrel;
    }

    @NotNull
    @Column(name = "percentage")
    private Byte percentage;

    @NotNull
    @Column(name = "size")
    private Integer size;

    @NotNull
    @Column(name = "aging")
    private Integer aging;

    @ManyToOne
    @JoinColumn(name = "grape_component_id", referencedColumnName = "id")
    private GrapeComponent grapeComponent;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "barrel_id", referencedColumnName = "id")
    private Barrel barrel;

    @Override
    public int compareTo(BarrelComponent bc) {
        int result = percentage.compareTo(bc.getPercentage());

        if (result == 0) {
            return barrel.getName().compareTo(bc.getBarrel().getName());
        }

        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof BarrelComponent)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "BarrelComponent(" + id + ")";
    }
}
