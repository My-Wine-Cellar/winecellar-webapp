package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine extends BaseEntity implements Comparable<Wine> {

    @Builder
    public Wine(Long id, String name, Integer vintage, Double alcohol, Double size,
                Float acid, Integer bottleAging, String description, Producer producer) {
        super(id);
        this.name = name;
        this.vintage = vintage;
        this.alcohol = alcohol;
        this.size = size;
        this.acid = acid;
        this.bottleAging = bottleAging;
        this.description = description;
        this.producer = producer;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "alcohol")
    private Double alcohol;

    @Column(name = "size")
    private Double size;

    @Column(name = "acid")
    private Float acid;

    @Column(name = "bottle_aging")
    private Integer bottleAging;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "subarea")
    private String subarea;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(mappedBy = "wine")
    private List<GrapeComponent> grapes;

    @OneToMany(mappedBy = "wine")
    private List<Bottle> bottles;

    @Override
    public int compareTo(Wine w) {
        return name.compareTo(w.getName());
    }
}
