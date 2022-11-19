/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Region;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for region
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class RegionDto extends AbstractKeyDto {

    private Long id;
    private String name;

    @Size(max = 8192)
    private String description;

    @Size(max = 50)
    private String weblink;

    private List<Long> areas;

    private Long countryId;

    /**
     * Default constructor
     */
    public RegionDto() {
        this.areas = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param r The region
     */
    public RegionDto(Region r) {
        super(toKey(r.getName()));
        this.id = r.getId();
        this.name = r.getName();
        this.description = r.getDescription();
        this.weblink = r.getWeblink();
        this.countryId = r.getCountry().getId();
        this.areas = new ArrayList<>();

        for (Area a : r.getAreas()) {
            this.areas.add(a.getId());
        }
    }
}
