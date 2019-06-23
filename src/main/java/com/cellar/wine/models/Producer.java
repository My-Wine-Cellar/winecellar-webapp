package com.cellar.wine.models;

import com.cellar.wine.utils.WineSorter;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Producer extends BaseEntity implements Comparable<Producer> {

    @Builder
    public Producer(Long id, String name, String description, String phone, String fax, String website) {
        super(id);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "website")
    private String website;

    @ManyToMany(mappedBy = "producers")
    public List<Area> areas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private List<Wine> wines;

    public List<Wine> getWines() {
        Collections.sort(wines, new WineSorter());
        return wines;
    }

    @Override
    public int compareTo(Producer p) {
        return name.compareTo(p.getName());
    }

    public Wine getWine(String label, boolean ignoreNew) {
        label = label.toLowerCase();
        for (Wine wine : wines) {
            if (!ignoreNew || !wine.isNew()) {
                String compName = wine.getName();
                compName = compName.toLowerCase();
                if (compName.equals(label)) {
                    return wine;
                }
            }
        }
        return null;
    }
}
