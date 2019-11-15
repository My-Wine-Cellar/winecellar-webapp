/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "barrel_component")
public class BarrelComponent extends BaseEntity implements Comparable<BarrelComponent> {

    private static final String NOT_NULL_MESSAGE = "This field is required";

    public BarrelComponent() {
        super();
    }

    public BarrelComponent(Byte percentage, Integer size, Integer aging,
                           GrapeComponent grapeComponent, Barrel barrel) {
        super();
        this.percentage = percentage;
        this.size = size;
        this.aging = aging;
        this.grapeComponent = grapeComponent;
        this.barrel = barrel;
    }

    @NotNull(message = NOT_NULL_MESSAGE)
    @Column(name = "percentage")
    private Byte percentage;

    @NotNull(message = NOT_NULL_MESSAGE)
    @Column(name = "size")
    private Integer size;

    @NotNull(message = NOT_NULL_MESSAGE)
    @Column(name = "aging")
    private Integer aging;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "grape_component_id", referencedColumnName = "id")
    private GrapeComponent grapeComponent;

    @NotNull(message = "You need to select a barrel")
    @ManyToOne
    @JoinColumn(name = "barrel_id", referencedColumnName = "id")
    private Barrel barrel;

    @Override
    public int compareTo(BarrelComponent bc) {
        int result = percentage.compareTo(bc.getPercentage());

        if (result == 0)
            return barrel.getName().compareTo(bc.getBarrel().getName());

        return result;
    }

    @Override
    public String toString() {
        return "BarrelComponent(" + id + ")";
    }
}
