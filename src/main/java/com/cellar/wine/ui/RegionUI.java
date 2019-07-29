package com.cellar.wine.ui;

import com.cellar.wine.models.Region;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
@Setter
public class RegionUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    public RegionUI(Region r) {
        super(toKey(r.getName()));
        this.id = r.getId();
        this.name = r.getName();
        this.description = r.getDescription();
        this.weblink = r.getWeblink();
    }
}
