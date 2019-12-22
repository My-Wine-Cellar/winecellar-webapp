/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.util.WineSorter;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@DynamicUpdate
public class Producer extends BaseEntity implements Comparable<Producer> {

    /**
     * Default constructor
     */
    public Producer() {
        super();
    }

    /**
     * Producer constructor
     *
     * @param name        name
     * @param description description
     * @param phone       phone
     * @param fax         fax
     * @param email       email
     * @param website     website
     * @param image       image
     */
    public Producer(String name, String description, String phone, String fax,
                    String email, String website, byte[] image) {
        super();
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.image = image;
    }

    @NotNull
    @NotEmpty(message = "You must at least provide the name")
    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @ManyToMany(mappedBy = "producers")
    private List<Area> areas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private List<Wine> wines;

    /**
     * Sorted by WineSorter
     *
     * @return sorted list of wines
     */
    public List<Wine> getWines() {
        Collections.sort(wines, new WineSorter());
        return wines;
    }

    @Override
    public int compareTo(Producer p) {
        return name.compareTo(p.getName());
    }

    /**
     * @param label     label
     * @param ignoreNew ignoreNew
     * @return Wine
     * @deprecated since v1.0.4
     */
    @Deprecated
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

    @Override
    public String toString() {
        return "Producer(" + id + ")";
    }
}
