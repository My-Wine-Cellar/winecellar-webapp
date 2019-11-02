package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Maceration extends BaseEntity implements Comparable<Maceration> {

    public Maceration() {
        super();
    }

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
    private List<GrapeComponent> grapes;

    @Override
    public int compareTo(Maceration m) {
        int result = days.compareTo(m.getDays());

        if (result == 0)
            return temperature.compareTo(m.getTemperature());

        return result;
    }

    @Override
    public String toString() {
        return "Maceration(" + id + ")";
    }
}
