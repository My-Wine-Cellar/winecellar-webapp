package com.cellar.wine.utils;

import lombok.*;

import java.io.Serializable;

/**
 * WineGrape bean
 */
@EqualsAndHashCode(callSuper = false)
@Getter
public class WineGrape implements Serializable {
    private Byte percentage;
    private String name;
    private Long id;
    
    public WineGrape(Byte percentage, String name, Long id) {
        this.percentage = percentage;
        this.name = name;
        this.id = id;
    }
}

