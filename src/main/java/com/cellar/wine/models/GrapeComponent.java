package com.cellar.wine.models;

import lombok.*;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "grape_component")
public class GrapeComponent extends BaseEntity implements Comparable<GrapeComponent> {

    public GrapeComponent() {
        super();
    }

    public GrapeComponent(Byte percentage, Date harvestBegin, Date harvestEnd, Grape grape, Wine wine) {
        super();
        this.percentage = percentage;
        this.harvestBegin = harvestBegin;
        this.harvestEnd = harvestEnd;
        this.grape = grape;
        this.wine = wine;
    }

    @Column(name = "percentage")
    private Byte percentage;

    @Column(name = "harvest_begin")
    private Date harvestBegin;

    @Column(name = "harvest_end")
    private Date harvestEnd;

    @ManyToOne
    @JoinColumn(name = "grape_id", referencedColumnName = "id")
    private Grape grape;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @ManyToOne
    @JoinColumn(name = "maceration_id", referencedColumnName = "id")
    private Maceration maceration;

    @ManyToOne
    @JoinColumn(name = "fermentation_id", referencedColumnName = "id")
    private Fermentation fermentation;

    @OneToMany(mappedBy = "grapeComponent")
    private List<BarrelComponent> barrelComponents;

    @Override
    public int compareTo(GrapeComponent gc) {
        int result = percentage.compareTo(gc.getPercentage());

        if (result == 0)
            return grape.getName().compareTo(gc.getGrape().getName());

        return result;
    }
}
