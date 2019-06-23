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
    public Wine(Long id, String name, Integer vintage, Float alcohol, Float size,
                Float acid, Float pH, Integer bottleAging, String description, String weblink,
                Producer producer, Closure closure) {
        super(id);
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

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(mappedBy = "wine")
    private List<GrapeComponent> grapes;

    @OneToMany(mappedBy = "wine")
    private List<Bottle> bottles;

    @OneToMany(mappedBy = "wine")
    private List<GenericTastingNotes> genericTastingNotes;

    @OneToMany(mappedBy = "wine")
    private List<Tasted> tasted;

    @OneToMany(mappedBy = "wine")
    private List<Review> reviews;

    @OneToMany(mappedBy = "wine")
    private List<Wishlist> wishlists;

    @ManyToOne
    @JoinColumn(name = "closure_id", referencedColumnName = "id")
    private Closure closure;

    @Override
    public int compareTo(Wine w) {
        return name.compareTo(w.getName());
    }
}
