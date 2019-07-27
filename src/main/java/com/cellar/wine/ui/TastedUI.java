package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;

/**
 * Tasted bean
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class TastedUI implements Serializable {
    private Long id;
    private Long producerId;
    private String producerName;
    private Long wineId;
    private String wineName;
    private Integer wineVintage;
    private Float wineSize;
    private Long reviewId;
    
    public TastedUI(Long id,
                    Long producerId, String producerName,
                    Long wineId, String wineName, Integer wineVintage, Float wineSize,
                    Long reviewId) {
        this.id = id;
        this.producerId = producerId;
        this.producerName = producerName;
        this.wineId = wineId;
        this.wineName = wineName;
        this.wineVintage = wineVintage;
        this.wineSize = wineSize;
        this.reviewId = reviewId;
    }
}

