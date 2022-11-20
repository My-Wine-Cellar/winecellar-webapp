/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Producer;
import info.mywinecellar.util.Image;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for wishlist
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ProducerDto extends AbstractKeyDto {

    private Long id;
    private String phone;
    private String fax;
    private String image;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @Email
    private String email;

    @Size(max = 255)
    private String description;

    @Size(max = 50)
    private String website;

    private List<Long> areas;

    /**
     * Default constructor
     */
    public ProducerDto() {
        this.areas = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param p The producer
     */
    public ProducerDto(Producer p) {
        super(toKey(p.getName()));
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.phone = p.getPhone();
        this.fax = p.getFax();
        this.email = p.getEmail();
        this.website = p.getWebsite();
        this.image = Image.encode(p.getImage());
        this.areas = new ArrayList<>();

        for (Area a : p.getAreas()) {
            this.areas.add(a.getId());
        }
    }
}
