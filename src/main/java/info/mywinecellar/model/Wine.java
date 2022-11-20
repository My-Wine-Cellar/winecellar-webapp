/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.wset.WSET;

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

    protected Wine() {
    }

    /**
     * Create a Wine by an id
     *
     * @param id the id
     * @return the Wine
     */
    public static Wine createWineById(Long id) {
        Wine wine = new Wine();
        wine.setId(id);
        return wine;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "vintage")
    private Integer vintage;

    @NotNull
    @Column(name = "size")
    private Float size;

    @Column(name = "alcohol")
    private Float alcohol;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
    private Set<WSET> wset;

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


    /**
     * Builder class for Wine
     */
    public static class Builder {
        private final String name;
        private final Integer vintage;
        private final Float size;

        /**
         * Builder constructor with non-null fields
         *
         * @param name    the name
         * @param vintage the vintage
         * @param size    the size
         */
        public Builder(String name, Integer vintage, Float size) {
            this.name = name;
            this.vintage = vintage;
            this.size = size;
        }

        private Float alcohol;

        /**
         * Build with alcohol
         *
         * @param val the value for alcohol
         * @return the Builder
         */
        public Builder alcohol(Float val) {
            alcohol = val;
            return this;
        }

        private Float acid;

        /**
         * Build with acid
         *
         * @param val the value for acid
         * @return the Builder
         */
        public Builder acid(Float val) {
            acid = val;
            return this;
        }

        private Float pH;

        /**
         * Build with pH
         *
         * @param val the value for pH
         * @return the Builder
         */
        public Builder pH(Float val) {
            pH = val;
            return this;
        }

        private Integer bottleAging;

        /**
         * Build with bottleAging
         *
         * @param val the value for a bottle's age
         * @return the Builder
         */
        public Builder bottleAging(Integer val) {
            bottleAging = val;
            return this;
        }

        private String description;

        /**
         * Build with description
         *
         * @param val the value for the description
         * @return the Builder
         */
        public Builder description(String val) {
            description = val;
            return this;
        }

        private String weblink;

        /**
         * Build with weblink
         *
         * @param val the value for a weblink
         * @return the Builder
         */
        public Builder weblink(String val) {
            weblink = val;
            return this;
        }

        private String subarea;

        /**
         * Build with subarea
         *
         * @param val the value for a subarea
         * @return the Builder
         */
        public Builder subarea(String val) {
            subarea = val;
            return this;
        }

        private byte[] image;

        /**
         * Build with image
         *
         * @param val the value for an image
         * @return the Builder
         */
        public Builder image(byte[] val) {
            image = val;
            return this;
        }

        private Producer producer;

        /**
         * Build with producer
         *
         * @param val the value for the Producer
         * @return the Builder
         */
        public Builder producer(Producer val) {
            producer = val;
            return this;
        }

        private Closure closure;

        /**
         * Build with closure
         *
         * @param val the value for the Closure
         * @return the Builder
         */
        public Builder closure(Closure val) {
            closure = val;
            return this;
        }

        private Color color;

        /**
         * Build with color
         *
         * @param val the value for the Color
         * @return the Builder
         */
        public Builder color(Color val) {
            color = val;
            return this;
        }

        private Shape shape;

        /**
         * Build with shape
         *
         * @param val the value for the Shape
         * @return the Builder
         */
        public Builder shape(Shape val) {
            shape = val;
            return this;
        }

        private Type type;

        /**
         * Build with type
         *
         * @param val the value for the Type
         * @return the Builder
         */
        public Builder type(Type val) {
            type = val;
            return this;
        }

        /**
         * Build our Wine
         *
         * @return the Wine
         */
        public Wine build() {
            return new Wine(this);
        }
    }

    private Wine(Builder builder) {
        name = builder.name;
        vintage = builder.vintage;
        size = builder.size;

        alcohol = builder.alcohol;
        acid = builder.acid;
        pH = builder.pH;
        bottleAging = builder.bottleAging;
        description = builder.description;
        weblink = builder.weblink;
        image = builder.image;
        subarea = builder.subarea;

        producer = builder.producer;
        closure = builder.closure;
        color = builder.color;
        shape = builder.shape;
        type = builder.type;
    }

}
