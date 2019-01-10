package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wine")
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String name, String appellation, String country, String vintage, String varietal) {
        super(id, country, appellation);
        this.name = name;
        this.vintage = vintage;
        this.varietal = varietal;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "vintage")
    private String vintage;

    @Column(name = "varietal")
    private String varietal;

    @Override
    public String toString() {
        return name;
    }
}
