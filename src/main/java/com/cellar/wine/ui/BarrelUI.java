package com.cellar.wine.ui;

import com.cellar.wine.models.Barrel;

import lombok.*;

/**
 * Barrel bean
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public class BarrelUI extends AbstractKeyUI {
    private Long id;
    private String name;
    private String description;
    private String weblink;
    private String shortname;

    private Byte percentage;
    private Integer size;
    private AgingUI aging;
    
    public BarrelUI() {
        // Empty
    }

    public BarrelUI(Byte percentage,
                    String name, Long id,
                    Integer size, AgingUI aging) {
        super();

        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.size = size;
        this.aging = aging;

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    public BarrelUI(Barrel b) {
        super();

        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.weblink = b.getWeblink();

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    public void setDescription(String s) {
        description = s;
    }

    public void setWeblink(String s) {
        weblink = s;
    }

    private String getShortName(String name) {
        if (name.indexOf("(") != -1) {
            return name.substring(0, name.indexOf("(") - 1);
        }

        return name;
    }
}

