package com.cellar.wine.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Wine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String vintage;
    private String appellation;
    private String varietal;
    private String country;
    private double price;
    private int rating;

    @ManyToMany
    @JoinTable(name = "producer_wine", joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private Set<Producer> producers = new HashSet<>();

    public Wine() {
    }

    public Wine(String name, String vintage, String appellation, String varietal, String country, int rating, double price) {
        this.name = name;
        this.vintage = vintage;
        this.appellation = appellation;
        this.varietal = varietal;
        this.country = country;
        this.rating = rating;
        this.price = price;
    }

    public Wine(String name, String vintage, String appellation, String varietal, String country, int rating, double price, Set<Producer> producers) {
        this.name = name;
        this.vintage = vintage;
        this.appellation = appellation;
        this.varietal = varietal;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.producers = producers;
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

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Producer> getProducer() {
        return producers;
    }

    public void setProducer(Set<Producer> producers) {
        this.producers = producers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(id, wine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", vintage='" + vintage + '\'' +
                ", appellation='" + appellation + '\'' +
                ", varietal='" + varietal + '\'' +
                ", country='" + country + '\'' +
                ", rating=" + rating + '\'' +
                ", price=" + price +
                '}';
    }
}
