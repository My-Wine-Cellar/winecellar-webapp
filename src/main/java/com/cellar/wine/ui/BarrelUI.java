package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
    private Integer aging;
    
    public BarrelUI(Byte percentage,
                    String name, Long id,
                    Integer size, Integer aging) {
        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.size = size;
        this.aging = aging;
    }
}

