package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String label, Producer producer, String vintage, String varietal) {
        super(id);
        this.producer = producer;
        this.label = label;
        this.vintage = vintage;
        this.varietal = varietal;
    }

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @Column(name = "vintage")
    private String vintage;

    @Column(name = "varietal")
    private String varietal;

}
