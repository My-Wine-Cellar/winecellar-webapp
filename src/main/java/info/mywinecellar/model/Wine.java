/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@DynamicUpdate
public class Wine extends BaseEntity implements Comparable<Wine> {

    /**
     * Default constructor
     */
    public Wine() {
        super();
    }

    /**
     * Wine constructor
     *
     * @param name        name
     * @param vintage     vintage
     * @param alcohol     alcohol
     * @param size        size
     * @param acid        acid
     * @param pH          pH
     * @param bottleAging bottleAging
     * @param description description
     * @param weblink     weblink
     * @param producer    producer
     * @param closure     closure
     * @param shape       shape
     * @param color       color
     * @param type        type
     * @param image       image
     */
    public Wine(String name, Integer vintage, Float alcohol, Float size,
                Float acid, Float pH, Integer bottleAging, String description, String weblink,
                Producer producer, Closure closure, Shape shape, Color color, Type type, byte[] image) {
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
        this.color = color;
        this.type = type;
        this.image = image;
    }

    /**
     * Wine constructor
     *
     * @param name    String name
     * @param vintage Integer vintage
     * @param size    Float size
     */
    public Wine(String name, Integer vintage, Float size) {
        super();
        this.name = name;
        this.vintage = vintage;
        this.size = size;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
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

    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine", cascade = CascadeType.REMOVE)
    private Set<GrapeComponent> grapes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<Bottle> bottles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<GenericTastingNotes> genericTastingNotes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<Tasted> tasted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<Wishlist> wishlists;

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
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;

    @Override
    public int compareTo(Wine w) {
        return name.compareTo(w.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Wine)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Wine(" + id + ")";
    }
}
