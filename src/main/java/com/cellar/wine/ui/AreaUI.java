package com.cellar.wine.ui;

import com.cellar.wine.models.Area;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
@Setter
public class AreaUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    public AreaUI(Area a) {
        super(toKey(a.getName()));
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.weblink = a.getWeblink();
    }
}
