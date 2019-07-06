package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shape extends BaseEntity implements Comparable<Shape> {

    @Builder
    public Shape(Long id, String name, String description, String weblink) {
        super(id);
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

    @OneToMany(mappedBy = "shape")
    private List<Wine> wines;

    @Override
    public int compareTo(Shape s) {
        return name.compareTo(s.getName());
    }
}
