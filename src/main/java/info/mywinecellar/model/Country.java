/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Country extends BaseEntity implements Comparable<Country> {

    /**
     * Default constructor
     */
    public Country() {
        super();
    }

    /**
     * Country constructor
     *
     * @param name        name
     * @param flag        flag
     * @param description description
     * @param weblink     weblink
     */
    public Country(String name, String flag, String description, String weblink) {
        super();
        this.name = name;
        this.flag = flag;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "flag")
    private String flag;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @Override
    public int compareTo(Country c) {
        return name.compareTo(c.getName());
    }

    @Override
    public String toString() {
        return "Country(" + id + ")";
    }
}
