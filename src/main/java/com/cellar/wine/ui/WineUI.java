package com.cellar.wine.ui;

import com.cellar.wine.models.Wine;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
@Setter
public class WineUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private Integer vintage;
    private Float size;

    WineUI(Wine w) {
        super(toKey(w.getName()));
        this.id = w.getId();
        this.name = w.getName();
        this.vintage = w.getVintage();
        this.size = w.getSize();
    }
}
