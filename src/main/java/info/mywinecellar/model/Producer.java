/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import lombok.Getter;
import lombok.Setter;

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
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private byte[] image;

    @ManyToMany(mappedBy = "producers")
    private Set<Area> areas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines;

    @Override
    public int compareTo(Producer p) {
        return name.compareTo(p.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Producer)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Producer(" + id + ")";
    }
}
