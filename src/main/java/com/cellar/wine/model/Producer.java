package com.cellar.wine.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Producer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String country;
    private String appellation;

    @ManyToMany(mappedBy = "producers")
    private Set<Wine> wines = new HashSet<>();

    public Producer() {
    }

    public Producer(String name, String country, String appellation) {
        this.name = name;
        this.country = country;
        this.appellation = appellation;
    }

    public Producer(String name, String country, String appellation, Set<Wine> wines) {
        this.name = name;
        this.country = country;
        this.appellation = appellation;
        this.wines = wines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Wine> getWines() {
        return wines;
    }

    public void setWines(Set<Wine> wines) {
        this.wines = wines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", appellation='" + appellation + '\'' +
                ", wines=" + wines +
                '}';
    }
}
