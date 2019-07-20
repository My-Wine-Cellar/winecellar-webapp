package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@Entity
public class Shape extends BaseEntity implements Comparable<Shape> {

    public Shape() {
        super();
    }

    public Shape(String name, String description, String weblink) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shape")
    private List<Wine> wines;

    @Override
    public int compareTo(Shape s) {
        return name.compareTo(s.getName());
    }
}
