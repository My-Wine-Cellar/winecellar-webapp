package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;

/**
 * WineGrape bean
 */
@EqualsAndHashCode(callSuper = false)
@Getter
public class WineGrapeUI implements Serializable {
    private Byte percentage;
    private String name;
    private Long id;
    
    public WineGrapeUI(Byte percentage, String name, Long id) {
        this.percentage = percentage;
        this.name = name;
        this.id = id;
    }
}

