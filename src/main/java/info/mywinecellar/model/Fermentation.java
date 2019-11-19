/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Fermentation extends BaseEntity implements Comparable<Fermentation> {

    public Fermentation() {
        super();
    }

    public Fermentation(Byte days, Float temperature) {
        super();
        this.days = days;
        this.temperature = temperature;
    }

    @NotNull
    @Column(name = "days")
    private Byte days;

    @Column(name = "temperature")
    private Float temperature;

    @OneToMany(mappedBy = "fermentation")
    private List<GrapeComponent> grapes;

    @Override
    public int compareTo(Fermentation m) {
        int result = days.compareTo(m.getDays());

        if (result == 0)
            return temperature.compareTo(m.getTemperature());

        return result;
    }

    @Override
    public String toString() {
        return "Fermentation(" + id + ")";
    }
}
