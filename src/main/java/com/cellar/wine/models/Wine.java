package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wine")
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String name, Producer producer, String appellation, String country, String vintage, String varietal) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.vintage = vintage;
        this.varietal = varietal;
    }

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "producer_id", referencedColumnName = "id"),
    @JoinColumn(name = "producer_country", referencedColumnName = "country"),
    @JoinColumn(name = "producer_appellation", referencedColumnName = "appellation")})
    private Producer producer;

    @Column(name = "vintage")
    private String vintage;

    @Column(name = "varietal")
    private String varietal;

    @Override
    public String toString() {
        return name;
    }
}
