package com.cellar.wine.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wine")
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String name, String vintage, String appellation, String varietal, String country, Double price,
                Integer rating, Producer producer) {
        super(id);
        this.name = name;
        this.vintage = vintage;
        this.appellation = appellation;
        this.varietal = varietal;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.producer = producer;
    }

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @Column(name = "name")
    private String name;

    @Column(name = "vintage")
    private String vintage;

    @Column(name = "appellation")
    private String appellation;

    @Column(name = "varietal")
    private String varietal;

    @Column(name = "country")
    private String country;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private int rating;

    @Override
    public String toString() {
        return name;
    }
}
