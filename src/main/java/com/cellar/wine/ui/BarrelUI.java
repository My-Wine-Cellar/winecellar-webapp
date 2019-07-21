package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;

/**
 * Barrel bean
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class BarrelUI implements Serializable {
    private Byte percentage;
    private String name;
    private Long id;
    private Integer size;
    private AgingUI aging;
    
    public BarrelUI(Byte percentage,
                    String name, Long id,
                    Integer size, AgingUI aging) {
        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.size = size;
        this.aging = aging;
    }
}

