/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Closure extends BaseEntity implements Comparable<Closure> {

    public Closure() {
        super();
    }

    public Closure(String name, String description, String weblink) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", length = 8192)
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "weblink")
    private String weblink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "closure")
    private List<Wine> wines;

    @Override
    public int compareTo(Closure c) {
        return name.compareTo(c.getName());
    }

    @Override
    public String toString() {
        return "Closure(" + id + ")";
    }
}
