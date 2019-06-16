package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Closure extends BaseEntity implements Comparable<Closure> {

    @Builder
    public Closure(Long id, String name, String description, String weblink) {
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

    @OneToMany(mappedBy = "closure")
    private List<Wine> wines;

    @Override
    public int compareTo(Closure c) {
        return name.compareTo(c.getName());
    }
}
