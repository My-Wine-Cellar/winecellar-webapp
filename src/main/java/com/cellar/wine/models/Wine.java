package com.cellar.wine.models;

import lombok.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine extends BaseEntity implements Comparable<Wine> {

    @Builder
    public Wine(Long id, String name, Integer vintage, Double alcohol, Double size, String description, Producer producer) {
        super(id);
        this.name = name;
        this.vintage = vintage;
        this.alcohol = alcohol;
        this.size = size;
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

    @Column(name = "description", length = 8192)
    private String description;

    //@Column(name = "skin_contact")
    //private String skinContact;

    //@Column(name = "aging")
    //private String aging;

    //@Column(name = "barrel_aging")
    //private String barrelAging;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(mappedBy = "wine")
    private List<GrapeComponent> grapes;

    @OneToMany(mappedBy = "wine")
    private List<Bottle> bottles;

    @Override
    public int compareTo(Wine w)
    {
        return name.compareTo(w.getName());
    }
}
