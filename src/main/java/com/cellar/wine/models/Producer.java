package com.cellar.wine.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "producer")
public class Producer extends BaseEntity {

    @Builder
    public Producer(Long id, String name, String country, String appellation, Set<Wine> wines) {
        super(id);
        this.name = name;
        this.country = country;
        this.appellation = appellation;
        if(wines != null) {
            this.wines = wines;
        }
    }

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "appellation")
    private String appellation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines = new HashSet<>();

    public Wine getWine(String name) {
        return getWine(name, false);
    }

    public Wine getWine(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Wine wine : wines) {
            if (!ignoreNew || !wine.isNew()) {
                String compName = wine.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return wine;
                }
            }
        }
        return null;
    }
}