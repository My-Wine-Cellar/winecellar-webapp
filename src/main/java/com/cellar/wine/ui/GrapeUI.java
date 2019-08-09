package com.cellar.wine.ui;

import com.cellar.wine.models.Grape;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Grape bean
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class GrapeUI extends AbstractKeyUI {
    private Long id;
    private String name;
    private String color;
    private String description;
    private String weblink;
    private String alternativeNames;

    private Byte percentage;
    private Date harvestBegin;
    private Date harvestEnd;
    private Byte macerationDays;
    private Float macerationTemperature;
    private Byte fermentationDays;
    private Float fermentationTemperature;
    private List<BarrelUI> barrels;
    
    public GrapeUI(Byte percentage,
                   String name, Long id,
                   Date harvestBegin, Date harvestEnd,
                   Byte macerationDays, Float macerationTemperature,
                   Byte fermentationDays, Float fermentationTemperature,
                   List<BarrelUI> barrels) {
        super(toKey(name));

        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.harvestBegin = harvestBegin;
        this.harvestEnd = harvestEnd;

        this.macerationDays = macerationDays;
        this.macerationTemperature = macerationTemperature;

        this.fermentationDays = fermentationDays;
        this.fermentationTemperature = fermentationTemperature;

        this.barrels = barrels;
    }

    GrapeUI(Grape g) {
        super(toKey(g.getName()));

        this.id = g.getId();
        this.name = g.getName();
        this.color = g.getColor();
        this.description = g.getDescription();
        this.weblink = g.getWeblink();
    }
}
