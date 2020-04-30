/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Maceration extends BaseEntity implements Comparable<Maceration> {

    /**
     * Default constructor
     */
    public Maceration() {
        super();
    }

    /**
     * Maceration constructor
     *
     * @param days        days
     * @param temperature temperature
     */
    public Maceration(Byte days, Float temperature) {
        super();
        this.days = days;
        this.temperature = temperature;
    }

    @NotNull
    @Column(name = "days")
    private Byte days;

    @Column(name = "temperature")
    private Float temperature;

    @OneToMany(mappedBy = "maceration")
    private Set<GrapeComponent> grapes;

    @Override
    public int compareTo(Maceration m) {
        int result = days.compareTo(m.getDays());

        if (result == 0) {
            return temperature.compareTo(m.getTemperature());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Maceration)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Maceration(" + id + ")";
    }
}
