package com.cellar.wine.ui;

import com.cellar.wine.models.Country;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
@Setter
public class CountryUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    public CountryUI(Country c) {
        super(toKey(c.getName()));
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.weblink = c.getWeblink();
    }
}
