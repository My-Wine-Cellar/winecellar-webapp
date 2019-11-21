/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
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

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @Digits(integer = 4, fraction = 0)
    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "alcohol")
    private Float alcohol;

    @NotNull
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine", cascade = CascadeType.REMOVE)
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closure_id", referencedColumnName = "id")
    private Closure closure;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shape_id", referencedColumnName = "id")
    private Shape shape;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;

    @Override
    public int compareTo(Wine w) {
        return name.compareTo(w.getName());
    }

    @Override
    public String toString() {
        return "Wine(" + id + ")";
    }
}
