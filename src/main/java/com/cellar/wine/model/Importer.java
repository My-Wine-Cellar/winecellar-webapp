package com.cellar.wine.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Importer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Importer(){}

    public Importer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Importer importer = (Importer) o;
        return Objects.equals(id, importer.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Importer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
