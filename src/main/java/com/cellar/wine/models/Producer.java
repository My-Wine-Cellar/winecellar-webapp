package com.cellar.wine.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "producer")
public class Producer extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "appellation")
    private String appellation;

    public Set<Wine> getWines() {
        return wines;
    }

    public void setWines(Set<Wine> wines) {
        this.wines = wines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

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

    @Override
    public String toString() {
        return name;
    }

}
