package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@Entity
public class Wine extends BaseEntity implements Comparable<Wine> {

    public Wine() {
        super();
    }

    public Wine(String name, Integer vintage, Float alcohol, Float size,
                Float acid, Float pH, Integer bottleAging, String description, String weblink,
                Producer producer, Closure closure, Shape shape) {
        super();
        this.name = name;
        this.vintage = vintage;
        this.alcohol = alcohol;
        this.size = size;
        this.acid = acid;
        this.pH = pH;
        this.bottleAging = bottleAging;
        this.description = description;
        this.weblink = weblink;
        this.producer = producer;
        this.closure = closure;
        this.shape = shape;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "alcohol")
    private Float alcohol;

    @Column(name = "size")
    private Float size;

    @Column(name = "acid")
    private Float acid;

    @Column(name = "ph")
    private Float pH;

    @Column(name = "bottle_aging")
    private Integer bottleAging;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @Column(name = "subarea")
    private String subarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<GrapeComponent> grapes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<Bottle> bottles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<GenericTastingNotes> genericTastingNotes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<Tasted> tasted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private List<Wishlist> wishlists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closure_id", referencedColumnName = "id")
    private Closure closure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shape_id", referencedColumnName = "id")
    private Shape shape;

    @Override
    public int compareTo(Wine w) {
        return name.compareTo(w.getName());
    }
}
