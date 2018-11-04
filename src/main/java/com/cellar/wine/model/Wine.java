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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

}
